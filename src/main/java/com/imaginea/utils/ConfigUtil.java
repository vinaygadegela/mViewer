package com.imaginea.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class ConfigUtil {

	private final Properties configProp = new Properties();

	// Constructor of class
	private ConfigUtil() {		
		InputStream in = null;
		try {
			in = new FileInputStream(new File("src/main/resources/config.properties"));
			configProp.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Singleton pattern
	private static class LazyHolder {
		private static final ConfigUtil INSTANCE = new ConfigUtil();
	}

	public static ConfigUtil getInstance() {
		return LazyHolder.INSTANCE;
	}

	public String getProperty(String key) {
		return configProp.getProperty(key);
	}

	public Set<String> getAllPropertyNames() {
		return configProp.stringPropertyNames();
	}

	public boolean containsKey(String key) {
		return configProp.containsKey(key);
	}
}
