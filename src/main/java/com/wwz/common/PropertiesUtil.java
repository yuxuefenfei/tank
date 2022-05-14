package com.wwz.common;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

public class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();
    private static final Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");

    static {
        ClassLoader loader = PropertiesUtil.class.getClassLoader();
        try {
            PROPERTIES.load(loader.getResourceAsStream("application.properties"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PropertiesUtil() {
        throw new RuntimeException("forbid getting instances!");
    }

    public static String getString(String key) {
        return (String) PROPERTIES.get(key);
    }

    public static Integer getInt(String key) {
        return Integer.parseInt(getString(key));
    }
}
