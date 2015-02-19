package com.zl.utils;

import java.util.Collections;
import java.util.TreeMap;

public class ConfigUtil {

	public static String getMasterIp() {
		return "";
//		return Play.application().configuration().getString("masterNode.ip");
	}

	public static int getMasterPort() {
		return 1;
	}
	
//	public static void get() {
//		try {
//			Resource resource = new ClassPathResource("/my.properties");
//			Properties props = PropertiesLoaderUtils.loadProperties(resource);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public static String getLocalIp() {
//		InetAddress ip;
//		try {
//			ip = InetAddress.getLocalHost();
//			System.out.println("Current IP address : " + ip.getHostAddress());
//			return ip.toString();
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//			return "";
//		}
		
		
		
		
		try {
			final StringBuilder address = new StringBuilder(15);
			final TreeMap<Integer, java.net.NetworkInterface> sortedInterfaces = new TreeMap<Integer, java.net.NetworkInterface>();
			for (java.net.NetworkInterface networkInterface : Collections.list(java.net.NetworkInterface.getNetworkInterfaces())) {
			    sortedInterfaces.put(networkInterface.getIndex(), networkInterface);
			}
			for (final java.net.NetworkInterface networkInterface : sortedInterfaces.values()) {
			    if (networkInterface.isLoopback()) continue;
			    for (final java.net.InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
			        final java.net.InetAddress inetAddress = interfaceAddress.getAddress();
			        if (inetAddress instanceof java.net.Inet4Address) {
			            final java.net.Inet4Address inet4Address = (java.net.Inet4Address) inetAddress;
			            final byte[] ipAddress = inet4Address.getAddress().clone();
			            if (ipAddress.length != 4) continue;
			            for (final byte ipAddressElement : ipAddress) {
			                if (address.length() > 0) address.append('.');
			        address.append(String.format("%03d", ipAddressElement & 0xff));
			            }
			            break;
			        }
			    }
			    if (address.length() != 0)
			    	break;
			}
			if (address.length() != 15)
				address.delete(0, address.length());
			return address.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
