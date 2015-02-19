package com.zl.utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.json.JSONObject;

public class StringUtil {
	public static JSONObject strToJson(String s) {
		if (s == null)
			return null;
		return new JSONObject(s);
	}
	
	public static String objToString(Object obj) {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			String result = ow.writeValueAsString(obj).replace("\n", "").replace("\r", "").replace("\t", "").replace(" ", "");
			return result;
		} catch (Exception ex) {
			return "Exception when parsing object to JSON";
		}
	}
}
