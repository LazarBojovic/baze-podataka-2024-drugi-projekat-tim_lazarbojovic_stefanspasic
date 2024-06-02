package udruzenje.model.utility;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
@Getter
public class JDBCUtils {
    public static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null || isConnectionClosed()) {
            connect();
        }
        return connection;
    }
    public static void connect(){
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "lakimunze");
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/udruzenjezus", properties);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean isConnectionClosed() {
        try {
            return connection == null || connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
