package control;

import model.AppConfig;
import model.DatabaseConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseLayer {

    private static Connection connection = null;

    public static Connection getConnection() {

        try {
            if (connection == null || connection.isClosed()) {

                AppConfig config = ConfigLoader.loadConfig();
                DatabaseConfig db = config.getDatabase();

                Class.forName(db.getDriver());

                String url = "jdbc:mysql://" + db.getHost() + ":" + db.getPort()
                        + "/" + db.getName()
                        + "?useSSL=false&useUnicode=true&characterEncoding=UTF-8";

                connection = DriverManager.getConnection(url, db.getUsername(), db.getPassword());
                System.out.println("Database connected successfully");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
