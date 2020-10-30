package dev.floffah.orm.connection;

import dev.floffah.orm.connection.type.ConnectionType;

import java.sql.SQLException;

public class Connection {
    ConnectionType type;

    java.sql.Connection conn;

    public Connection(ConnectionType type) {
        this.type = type;
    }

    /**
     * Create the JDBC connection
     * @throws SQLException An SQLException thrown when connecting with JDBC
     */
    public void connect() throws SQLException {
        conn = type.getConnection();
    }

    /**
     * Get the JDBC connection
     * @return The JDBC connection
     */
    public java.sql.Connection getJDBCConnection() {
        return conn;
    }
}
