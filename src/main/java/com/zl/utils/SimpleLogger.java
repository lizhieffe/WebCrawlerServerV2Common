package com.zl.utils;

import org.apache.log4j.Logger;

import com.zl.interfaces.IDaemon;

public class SimpleLogger {
	
	public static void info(String message) {
		Logger.getLogger("").info("[" + TimeUtil.getUnixTime() + "] " + message);
	}
	
	public static void info(Class<?> cla, String message) {
		Logger.getLogger(cla).info("[" + TimeUtil.getUnixTime() + "] " + message);
	}
	
	public static void error(String message) {
		Logger.getLogger("").error("[" + TimeUtil.getUnixTime() + "] " + message);
	}
	
	public static void error(Class<?> cla, String message) {
		Logger.getLogger(cla).error("[" + TimeUtil.getUnixTime() + "] " + message);
	}
	
	public static void logServiceStartSucceed(String service) {
        info(service + " starts successfully");
	}
	
	public static void logServiceStartSucceed(IDaemon service) {
		logServiceStartSucceed(service.getClass().getName());
	}
	
	public static void logServiceStartFail(String service) {
        info(service + " starts failed");
	}
	
	public static void logServiceStartFail(IDaemon service) {
		logServiceStartFail(service.getClass().getName());
	}
	
	public static void logServiceAlreadyStarted(String service) {
        info(service + " has already started");
	}
	
	public static void logServiceAlreadyStarted(IDaemon service) {
		logServiceAlreadyStarted(service.getClass().getName());
	}
	
	public static void logServiceStopSucceed(String service) {
        info(service + " stops successfully");
	}
	
	public static void logServiceStopSucceed(IDaemon service) {
		logServiceStopSucceed(service.getClass().getName());
	}
	
	public static void logServiceStopFail(String service) {
        info(service + " stops failed");
	}
	
	public static void logServiceStopFail(IDaemon service) {
		logServiceStopFail(service.getClass().getName());
	}
}
