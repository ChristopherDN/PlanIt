package now.planit.Data.Utility;

import now.planit.Exceptions.DBConnFailedException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static String user;
    private static String password;
    private static String url;
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) return connection;
            url = System.getenv("url");//properties.getProperty("url");
            user = System.getenv("user");//properties.getProperty("user");
            password = System.getenv("password");//properties.getProperty("password");
        try {
            connection = DriverManager.getConnection(url,user, password);
        } catch (SQLException e) {
            System.out.println("Database unavaiable DBmanager class.");
            e.printStackTrace();
        }
        return connection;
    }
}