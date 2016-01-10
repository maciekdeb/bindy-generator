package pl.lodz.p.bindy_generator.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by maciek on 06/01/16.
 */
public class Config {

    public static Config instance;
    public static final Properties PROPERTIES = new Properties();

    private Config() {
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
            try {
                InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("project.properties");
                PROPERTIES.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return PROPERTIES.getProperty(key, defaultValue);
    }

    public String generationMark() {
        return "Class automatically generated. Any change can be overwritten.\n"
                + "Model representation for Apache Camel Bindy.\n"
                + String.format("\n%s %s\n", PROPERTIES.getProperty("artifactId"), PROPERTIES.getProperty("version"));
    }

}
