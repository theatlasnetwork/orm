package dev.floffah.orm.connection;

import dev.floffah.orm.connection.type.ConnectionType;
import dev.floffah.orm.errors.TransactionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ORMConnection {
    ConnectionType type;

    Connection conn;

    Map<UUID, List<String>> transactions = new HashMap<>();

    ConnectionManager manager;

    public ORMConnection(ConnectionType type, ConnectionManager manager) {
        this.type = type;
        this.manager = manager;
    }

    /**
     * Create the JDBC connection
     *
     * @throws SQLException An SQLException thrown when connecting with JDBC
     */
    public void connect() throws SQLException {
        conn = type.getConnection();
        conn.setAutoCommit(false);
    }

    public ResultSet query(String query) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }

    public void instantExecute(String query) throws SQLException {
        manager.logTransaction("INSTANT");
        Statement stmt = conn.createStatement();
        manager.logQuery(query);
        stmt.addBatch(query);
        stmt.executeBatch();
        manager.logCommit("INSTANT");
        conn.commit();
    }

    public void instantExecute(List<String> queries) throws SQLException {
        manager.logTransaction("INSTANT");
        Statement stmt = conn.createStatement();
        for (String query : queries) {
            manager.logQuery(query);
            stmt.addBatch(query);
        }
        stmt.executeBatch();
        manager.logCommit("INSTANT");
        conn.commit();
    }

    public void addExecute(UUID transaction, String query) throws TransactionException {
        if (transactions.containsKey(transaction)) {
            transactions.get(transaction).add(query);
        } else {
            throw new TransactionException("Could not find a transaction with id '" + transaction + "'", transaction);
        }
    }

    public void transact(UUID transaction) throws SQLException {
        if (transactions.containsKey(transaction)) {
            manager.logTransaction(transaction);
            List<String> queries = transactions.get(transaction);
            Statement stmt = conn.createStatement();
            for (String query : queries) {
                manager.logQuery(query);
                stmt.addBatch(query);
            }
            stmt.executeBatch();
            manager.logCommit(transaction);
            conn.commit();
        } else {
            throw new TransactionException("Could not find a transaction with id '" + transaction + "'", transaction);
        }
    }

    /**
     * Get the JDBC connection
     *
     * @return The JDBC connection
     */
    public Connection getJDBCConnection() {
        return conn;
    }
}
