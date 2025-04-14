package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    public static String getPropertyString(String fileName) {
        Properties props = new Properties();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        return url + "?user=" + username + "&password=" + password;
    }
}
