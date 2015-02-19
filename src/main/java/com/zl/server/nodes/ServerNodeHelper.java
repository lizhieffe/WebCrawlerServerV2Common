package com.zl.server.nodes;

public class ServerNodeHelper {
	
	public static boolean isValidIp(String ip) {
		if (ip == null || ip.length() < 7 || ip.length() > 15)
			return false;
		String[] parts = ip.split("\\.");
		if (parts.length != 4)
			return false;
		for (int i = 0; i < parts.length; ++i) {
			try {
				int part = Integer.parseInt(parts[i]);
				if (part < 0 || part > 255)
					return false;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	public static boolean isValidPort(String port) {
		if (port == null)
			return false;
		try {
			int val = Integer.parseInt(port);
			if (val < 1 || val > 65535)
				return false;
		} catch(NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static String constructHost(String ip, int port) {
		StringBuilder builder = new StringBuilder();
		builder.append("http://");
		builder.append(ip);
		builder.append(":");
		builder.append(port);
		return builder.toString();
	}
}
