package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConnection {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);

    private static DatabaseConnection instance;
    private Connection connection;

    private static String url = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : "";
    private static String user = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "";
    private static String password = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "";

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, user, password.equals("\"\"") ? "" : password);
        } catch (ClassNotFoundException e) {
            logger.error("MySQL JDBC Driver not found: " + e.getMessage());

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public static DatabaseConnection getInstance() {
        try {
            if (instance == null || instance.connection.isClosed() || instance.connection == null) {
                instance = new DatabaseConnection();
            }

        } catch (SQLException e) {
            logger.error("MySQL JDBC Driver not found.");
        }

        return instance;
    }

    public static Connection getConnection() {
        return getInstance().connection;
    }
}
