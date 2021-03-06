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
            url = System.getenv("url");
            user = System.getenv("user");
            password = System.getenv("password");
        try {
            connection = DriverManager.getConnection(url,user, password);
        }
        catch (SQLException e) {
            System.out.println("Developer info: There is no connection to the database! INSERT PANIC HERE _______!");
            e.printStackTrace();
            throw new DBConnFailedException("Please contact support on the  \"Mail us\" on the bottom of this page ");

        }
        return connection;
    }
}