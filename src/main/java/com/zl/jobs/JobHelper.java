package com.zl.jobs;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class JobHelper {
	
	public static boolean isValidTypeNameForWebCrawlingJob(String type) {
		return type.equals("webcrawling");
	}
	
	public static boolean isValidUrl(String url) {
		if (url == null || url.length() == 0)
			return false;
		
		/*
		 * from stackoverflow:
		 * http://stackoverflow.com/questions/1600291/validating-url-in-java
		 */
		URL u = null;
		
	    try {  
	        u = new URL(url);  
	    } catch (MalformedURLException e) {  
	        return false;  
	    }

	    try {  
	        u.toURI();  
	    } catch (URISyntaxException e) {  
	        return false;  
	    }  

	    return true;  
	}
	
	public static boolean isValidDepth(String depth) {
		if (depth == null || depth.length() == 0)
			return false;
		
		try {
			Integer.parseInt(depth);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static int getDepthInteger(String depth) {
		/**
		 * return -1 when the depth is of invalid format
		 */
		if (!isValidDepth(depth))
			return -1;
		
		/**
		 * return 0 for infinite depth
		 */
		int val = Integer.parseInt(depth);
		if (val <= 0)
			val = 0;
		return val;
	}
	
	public static String getDomainFromUrl(String url) {
		if (url == null || !isValidUrl(url))
			return "";
		return url.split("//")[1].split("/")[0];
	}
}
