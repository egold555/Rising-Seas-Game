package org.golde.saas.risingseasgame.shared;

public class Logger {

	private static final boolean ENABLED = true;

	public static void info(String msg) {
		if(!ENABLED) {return;}
		System.out.println("[INFO]" + format(new Exception()) + msg);
	}
	
	public static void warning(String msg) {
		if(!ENABLED) {return;}
		System.out.println("[WARNING]" + format(new Exception()) + msg);
	}
	
	public static void error(String msg) {
		if(!ENABLED) {return;}
		System.err.println("[ERROR]" + format(new Exception()) + msg);
	}
	
	public static void error(Exception e, String msg) {
		if(!ENABLED) {return;}
		error("[ERROR]" + format(new Exception()) + msg);
		e.printStackTrace();
	}
	
	private static String format(Exception e) {
		StackTraceElement ste = e.getStackTrace()[1];
		String s = "[";
		
		s = s + ste.getFileName().replace(".java", "");
		s = s + ":" + ste.getLineNumber();
		
		return s + "] ";
	}
	
}
