package ru.levelp.at.homework4;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetProperty {

    static FileInputStream fileInputStream;
    static  Properties properties;

    static {
        try {
            properties = new Properties();
            fileInputStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(fileInputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getProperty(String nameProperty) {
        return properties.getProperty(nameProperty);
    }
}
