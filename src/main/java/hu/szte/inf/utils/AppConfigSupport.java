package hu.szte.inf.utils;

import java.io.IOException;
import java.util.Properties;

public class AppConfigSupport {

    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("application.properties"));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
