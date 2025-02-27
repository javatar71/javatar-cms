package ru.javatar;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Props {

    public static String get_prop(String key) {
        try (InputStream input = Props.class.getResourceAsStream("app.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
