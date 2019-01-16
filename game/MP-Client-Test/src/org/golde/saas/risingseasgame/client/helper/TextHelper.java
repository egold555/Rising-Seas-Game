package org.golde.saas.risingseasgame.client.helper;

import java.util.LinkedList;
import java.util.List;

public class TextHelper {

	public static String stringArrayToNewLineChar(String[] lines) {
		String toReturn = "";
		
		for(String s : lines) {
			toReturn += s + "\n";
		}
		
		return toReturn;
	}
	
	/**
	 * 
	 * MODIFIED from: https://www.spigotmc.org/threads/lore-separator-linebreak-add.287964/
	 * 
	 * Breaks a raw string up into a series of lines. Words are wrapped using
	 * spaces as decimeters and the newline character is respected.
	 *
	 * @param rawString The raw string to break.
	 * @param lineLength The length of a line of text.
	 * @return An array of word-wrapped lines.
	 */
	public static String[] wordWrap(String rawString, int lineLength) {
		// A null string is a single line
		if (rawString == null) {
			return new String[] {""};
		}

		// A string shorter than the lineWidth is a single line
		if (rawString.length() <= lineLength && !rawString.contains("\n")) {
			return new String[] {rawString};
		}

		char[] rawChars = (rawString + ' ').toCharArray(); // add a trailing space to trigger pagination
		StringBuilder word = new StringBuilder();
		StringBuilder line = new StringBuilder();
		List<String> lines = new LinkedList<String>();
		int lineColorChars = 0;

		for (int i = 0; i < rawChars.length; i++) {
			char c = rawChars[i];

			// skip chat color modifiers
			/*if (c == ChatColor.COLOR_CHAR) {
                word.append(ChatColor.getByChar(rawChars[i + 1]));
                lineColorChars += 2;
                i++; // Eat the next character as we have already processed it
                continue;
            }*/

			if (c == ' ' || c == '\n') {
				if (line.length() == 0 && word.length() > lineLength) { // special case: extremely long word begins a line
					for (String partialWord : word.toString().split("(?<=\\G.{" + lineLength + "})")) {
						lines.add(partialWord);
					}
				} else if (line.length() + 1 + word.length() - lineColorChars == lineLength) { // Line exactly the correct length...newline
					if (line.length() > 0) {
						line.append(' ');
					}
					line.append(word);
					lines.add(line.toString());
					line = new StringBuilder();
					lineColorChars = 0;
				} else if (line.length() + 1 + word.length() - lineColorChars > lineLength) { // Line too long...break the line
					for (String partialWord : word.toString().split("(?<=\\G.{" + lineLength + "})")) {
						lines.add(line.toString());
						line = new StringBuilder(partialWord);
					}
					lineColorChars = 0;
				} else {
					if (line.length() > 0) {
						line.append(' ');
					}
					line.append(word);
				}
				word = new StringBuilder();

				if (c == '\n') { // Newline forces the line to flush
					lines.add(line.toString());
					line = new StringBuilder();
				}
			} else {
				word.append(c);
			}
		}

		if (line.length() > 0) { // Only add the last line if there is anything to add
			lines.add(line.toString());
		}

		// Iterate over the wrapped lines, applying the last color from one line to the beginning of the next
		/*if (lines.get(0).length() == 0 || lines.get(0).charAt(0) != ChatColor.COLOR_CHAR) {
			lines.set(0, ChatColor.WHITE + lines.get(0));
		}
		for (int i = 1; i < lines.size(); i++) {
			final String pLine = lines.get(i - 1);
			final String subLine = lines.get(i);

			char color = pLine.charAt(pLine.lastIndexOf(ChatColor.COLOR_CHAR) + 1);
			if (subLine.length() == 0 || subLine.charAt(0) != ChatColor.COLOR_CHAR) {
				lines.set(i, ChatColor.getByChar(color) + subLine);
			}
		}
		 */
		return lines.toArray(new String[lines.size()]);
	}

}
