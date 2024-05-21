package com.connect.DB.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private final Properties properties = new Properties();

    public ConfigLoader(String configFilePath){
        try(InputStream input = getClass().getClassLoader().getResourceAsStream(configFilePath)){
            if(input == null){
                System.out.println("Unable to find " + configFilePath);
                return;
            }
            properties.load(input);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
