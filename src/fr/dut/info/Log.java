package fr.dut.info;

import java.util.ArrayList;


public class Log {
	private static Log instance = null;
	private final ArrayList<String> logs;
	
	private Log() {
		logs = new ArrayList<String>();
	}
	
	public static Log getLog() {
		if (instance == null) {
			instance = new Log();
		}
		return instance;
	}
	
	public ArrayList<String> getLogs() {
		return logs;
	}
	
	public void addLog(String string) {
		logs.add(string);
	}
}
