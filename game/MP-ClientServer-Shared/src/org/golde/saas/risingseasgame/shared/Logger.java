package org.golde.saas.risingseasgame.shared;

public class Logger {

	public static void info(String msg) {
		System.out.println("[INFO]" + format(new Exception()) + msg + Color.RESET);
	}
	
	public static void warning(String msg) {
		System.out.println(Color.YELLOW + "[WARNING]" + format(new Exception()) + msg + Color.RESET);
	}
	
	public static void error(String msg) {
		System.err.println(Color.RED + "[ERROR]" + format(new Exception()) + msg + Color.RESET);
	}
	
	public static void error(Exception e, String msg) {
		error(format(new Exception()) + msg);
		e.printStackTrace();
	}
	
	public static void packetSent(String msg) {
		System.out.println(Color.CYAN + "[INFO] [Packet Sent]" + format(new Exception()) + msg + Color.RESET);
	}
	
	public static void packetRecieved(String msg) {
		System.out.println(Color.PURPLE + "[INFO] [Packet Recieved]" + format(new Exception()) + msg + Color.RESET);
	}
	
	private static String format(Exception e) {
		StackTraceElement ste = e.getStackTrace()[1];
		String s = "[";
		
		s = s + ste.getFileName().replace(".java", "");
		s = s + ":" + ste.getLineNumber();
		
		return s + "] ";
	}
	
	private static class Color {
		public static final String RESET = "\u001B[0m";
		public static final String RED = "\u001B[31m";
		public static final String GREEN = "\u001B[32m";
		public static final String YELLOW = "\u001B[33m";
		public static final String PURPLE = "\u001B[35m";
		public static final String CYAN = "\u001B[36m";
		public static final String WHITE = "\u001B[37m";
	}
	
}
