package dev.floffah.orm.connection.type;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL extends ConnectionType {
    public MySQL() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ignored) {

        }
    }

    String username;
    String password;
    String url;

    /**
     *
     * @param username Username to authenticate with
     * @param password Password to authenticate with
     */
    public void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Set the url to connect to
     * @param url Url to create a connection with
     */
    public void setURL(String url) {
        this.url = url;
    }

    /**
     *
     * @return The JDBC Connection
     * @throws SQLException The SQLException thrown when creating the connection
     */
    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
