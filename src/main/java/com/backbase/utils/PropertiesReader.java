package com.backbase.utils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private String SEP = File.separator;
    private final String propertyFileName;

    public PropertiesReader(String propertiesFilePath) {
        this.propertyFileName = propertiesFilePath;
    }

    /**
     * Returns property value for chosen key
     *
     * @param propertyKey name of the property
     * @return property value
     */
    public String readProperty(String propertyKey) {
        if (propertyKey != null) {
            return loadPropertiesFile().getProperty(propertyKey);
        }
        return null;
    }

    /**
     * Loads all properties from propertyFileName
     *
     * @return properties from property File
     */
    private Properties loadPropertiesFile() {
        Properties properties = new Properties();
        try {
            properties.load(PropertiesReader.class.getResourceAsStream("/" + propertyFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
