package fr.dut.info;

import java.util.ArrayList;
import java.util.List;


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
	
	public List<String> logDisplay(int numberOfLogs) {
		int logSize = logs.size();
		if (logSize <= numberOfLogs) {
			return logs;
		}
		return logs.subList(logSize - numberOfLogs, logSize);
	}
	
	public void addLog(String string) {
		logs.add(string);
	}
}
