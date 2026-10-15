package org.example.services;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigService {

    private Properties prop = new Properties();

    public ConfigService() {
        try {
            prop.load( new FileReader("app.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }
}
