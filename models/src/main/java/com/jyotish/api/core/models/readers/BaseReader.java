package com.jyotish.api.core.models.readers;

import java.io.IOException;
import java.util.Properties;

public abstract class BaseReader {

    public static final String APPLICATION_PROPERTIES = "application.properties";

    public static Properties readPropertiesFile(String fileName) {
        try {
            Properties properties = new Properties();
            properties.load(ClassLoader.getSystemResourceAsStream(fileName));
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getValue(String property) {
        try {
            Properties properties = new Properties();
            properties.load(ClassLoader.getSystemResourceAsStream(APPLICATION_PROPERTIES));
            return properties.getOrDefault(property, "").toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
