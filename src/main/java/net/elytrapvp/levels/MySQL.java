package net.elytrapvp.levels;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private static Connection connection;
    private static String host = "localhost";
    private static String database = "network";
    private static String username = "root";
    private static String password = "wsxqaz";
    private static int port = 3306;

    /**
     * Close a connection.
     */
    public static void closeConnection() {
        if(isConnected()) {
            try {
                connection.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the connection.
     * @return Connection
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * Get if plugin is connected to the database.
     * @return Connected
     */
    private static boolean isConnected() {
        return (connection != null);
    }

    /**
     * Open a MySQL Connection
     */
    public static void openConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return;
            }

            synchronized(Levels.class) {
                if (connection != null && !connection.isClosed()) {
                    return;
                }
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&useSSL=false", username, password);
            }
        }
        catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
