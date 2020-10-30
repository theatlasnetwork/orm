package dev.floffah.orm.connection;

import dev.floffah.orm.connection.type.ConnectionType;

public class Connection {
    ConnectionType type;

    public Connection(ConnectionType type) {
        this.type = type;
    }
}
