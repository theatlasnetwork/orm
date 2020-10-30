package dev.floffah.orm.connection.type;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionType {
    /**
     * Materialising a JDBC connection
     * @return a JDBC connection
     * @throws SQLException An SQLException thrown when connecting with JDBC
     */
    public Connection getConnection() throws SQLException {
        return null;
    }
}
