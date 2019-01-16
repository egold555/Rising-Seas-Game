package org.golde.saas.risingseasgame.client.helper;

import java.awt.Font;
import java.util.HashMap;

public class FontManager {

	private static HashMap<Integer, BetterTrueTypeFont> CREATED_FONTS = new HashMap<Integer, BetterTrueTypeFont>();
	
	public static final BetterTrueTypeFont getOrCreateFont(int size) {
		for(int key : CREATED_FONTS.keySet()) {
			if(key == size) {
				return CREATED_FONTS.get(key);
			}
		}
		System.out.println("Created font with size: " + size);
		BetterTrueTypeFont created = createFont(size);
		CREATED_FONTS.put(size, created);
		
		return created;
	}
	
	private static BetterTrueTypeFont createFont(int size) {
		Font awtFont = new Font("Arial", Font.PLAIN, size);
		return new BetterTrueTypeFont(awtFont, false);
	}
	
}
