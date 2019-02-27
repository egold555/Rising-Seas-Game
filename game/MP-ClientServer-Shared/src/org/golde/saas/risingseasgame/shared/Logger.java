package org.golde.saas.risingseasgame.shared;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
	
	public static void info(String msg) {
		String real = "[INFO]" + format(new Exception()) + msg;
		System.out.println(real + Color.RESET);
		logFile(real);
	}

	public static void warning(String msg) {
		String real = "[WARNING]" + format(new Exception()) + msg;
		System.out.println(Color.YELLOW + real + Color.RESET);
		logFile(real);
	}

	public static void error(String msg) {
		String real = "[ERROR]" + format(new Exception()) + msg;
		System.err.println(Color.RED + real + Color.RESET);
		logFile(real);
	}

	public static void error(Exception e, String msg) {
		error(format(new Exception()) + msg);
		e.printStackTrace();
		logFile(e);
	}

	public static void packetSent(String msg) {
		String real = "[INFO] [Packet Sent]" + format(new Exception()) + msg;
		System.out.println(Color.CYAN + real + Color.RESET);
		logFile(real);
	}

	public static void packetRecieved(String msg) {
		String real = "[INFO] [Packet Recieved]" + format(new Exception()) + msg;
		System.out.println(Color.PURPLE + real + Color.RESET);
		logFile(real);
	}

	private static String format(Exception e) {
		StackTraceElement ste = e.getStackTrace()[1];
		String s = "[";

		s = s + ste.getFileName().replace(".java", "");
		s = s + ":" + ste.getLineNumber();

		return s + "] ";
	}

	@SuppressWarnings("unused")
	private static class Color {
		public static final String RESET = "\u001B[0m";
		public static final String RED = "\u001B[31m";
		public static final String GREEN = "\u001B[32m";
		public static final String YELLOW = "\u001B[33m";
		public static final String PURPLE = "\u001B[35m";
		public static final String CYAN = "\u001B[36m";
		public static final String WHITE = "\u001B[37m";
	}

	private static PrintStream fileLog = null;
	static {
		try {
			String side = isClient() ? "Client" : "Server";
			File logsDir = new File("logs");
			logsDir.mkdirs();
			File logFile = new File(logsDir, dateFormat.format(new Date()) + "-" + side + ".txt");
			fileLog = new PrintStream(logFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}

	private static void logFile(String msg) {
		if(fileLog != null) {
			fileLog.println(msg);
		}
	}
	
	private static void logFile(Exception e) {
		if(fileLog != null) {
			e.printStackTrace(fileLog);
		}
	}

	private static boolean isClient() {
		try {
			Class.forName("org.golde.saas.risingseasgame.client.MainClient");
			return true;
		} catch( ClassNotFoundException e ) {
			return false;
		}
	}

}
