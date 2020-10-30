package dev.floffah.orm.connection;

import java.util.ArrayList;
import java.util.List;

public class ConnectionManager {
    List<ConnectionFactory> uninit = new ArrayList<>();

    public ConnectionManager() {

    }

    /**
     * Create a connection factory. You do not need to manually add this to the manager. That is automatic.
     *
     * @return an editable connection factory
     */
    public ConnectionFactory createConnection() {
        ConnectionFactory factory = new ConnectionFactory();

        uninit.add(factory);
        return factory;
    }

    /**
     * Add an existing connection factory.
     *
     * @param factory A connection factory instance to add
     */
    public void addFactory(ConnectionFactory factory) {
        uninit.add(factory);
    }
}
