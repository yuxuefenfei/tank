package com.w.tank;

import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    public static Properties properties = new Properties();

    static {
        try {
            properties.load(ConfigManager.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return (String) properties.get(key);
    }
}
