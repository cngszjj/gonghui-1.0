package com.gh.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FilePath {
	private static Properties properties = new Properties();
	
	public static Log log = LogFactory.getLog(FilePath.class);
	static{
		try {
			properties.load(FilePath.class.getClassLoader().getResourceAsStream("filepath.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			log.error("", e);
		}
	}

	public static String getKey(String key){		
		return (String)properties.get(key);
	}
}
