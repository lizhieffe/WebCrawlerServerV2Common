package com.zl.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * read the src/main/resources/application.properties file
 */
public class AppProperties {
	
	private static AppProperties instance;
	private Map<String, String> map = new HashMap<String, String>();
	
	private AppProperties() {
		init();
	}
	
	synchronized public static AppProperties getInstance() {
		if (instance == null)
			instance = new AppProperties();
		return instance;
	}
	
	private void init() {
		String fileName = "application.properties";
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split("=");
				if (parts.length != 2)
					continue;
				map.put(parts[0], parts[1]);
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String get(String key) {
		return map.get(key);
	}
}
