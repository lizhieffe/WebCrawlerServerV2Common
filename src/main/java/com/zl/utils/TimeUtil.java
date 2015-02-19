package com.zl.utils;

public class TimeUtil {
	public static int getUnixTime() {
		return (int)(System.currentTimeMillis() / 1000L);
	}
}
