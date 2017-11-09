package com.epam.adok.core.propmanager;

import com.epam.adok.core.exception.PropertyManagerException;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

public class PropertiesManager {

    private Properties properties;

    public PropertiesManager(String propertyFileName) throws PropertyManagerException {
        try (InputStream inputStream = PropertiesManager.class.getClassLoader().getResourceAsStream(propertyFileName)) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new PropertyManagerException(MessageFormat.format(
                    "Error: Failed to load properties file {0}. Check the file name.: {1}", propertyFileName, e));
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
