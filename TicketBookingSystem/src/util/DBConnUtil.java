package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {
    private static Connection connection;

    public static Connection getConnection(String propertyFileName) {
        try {
            String connectionString = DBPropertyUtil.getPropertyString(propertyFileName);
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
