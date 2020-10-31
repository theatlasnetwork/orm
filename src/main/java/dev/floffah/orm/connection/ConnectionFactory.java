package dev.floffah.orm.connection;

import dev.floffah.orm.connection.type.MySQL;

import java.util.Arrays;

public class ConnectionFactory {
    ConnectionManager manager = null;

    /**
     * Creates a blank connection factory
     */
    public ConnectionFactory() {

    }

    /**
     * Creates a blank connection factory with a parent ConnectionManager to allow details
     *
     * @param manager parent manager
     * @see dev.floffah.orm.connection.ConnectionManager
     */
    public ConnectionFactory(ConnectionManager manager) {
        this.manager = manager;
    }

    ORMConnection build;

    String dbtype;

    //connection details
    String url = null;
    String host;
    Integer port;
    String username;
    String password;
    String database;

    /**
     * Creates a connection
     *
     * @return the created connection
     */
    public ORMConnection build() {
        if(dbtype.equals("mysql")) {
            MySQL type = new MySQL();
            type.setCredentials(username, password);
            type.setURL("jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + username + "&password=" + password);
            build = new ORMConnection(type, manager);
        }
        return build;
    }

    /**
     * Set the database type. Currently supported types: mysql
     *
     * @param type database type
     * @return current connection factory
     */
    public ConnectionFactory setType(String type) {
        if(!Arrays.asList("mysql").contains(type)) throw new IllegalArgumentException(type + " is not a supported database type");
        this.dbtype = type;
        return this;
    }

    /**
     * Overrides the url and prevents a generated url being passed to the created connection.
     *
     * @param url url that will override the generated one
     * @return current connection factory
     */
    public ConnectionFactory setURL(String url) {
        this.url = url;
        return this;
    }

    /**
     * Sets the hostname to connect with.
     *
     * @param host hostname
     * @return current connection factory
     */
    public ConnectionFactory setHost(String host) {
        this.host = host;
        return this;
    }

    /**
     * Sets the port to connect with.
     *
     * @param port database server port
     * @return current connection factory
     */
    public ConnectionFactory setPort(Integer port) {
        this.port = port;
        return this;
    }

    /**
     * Sets the username to authenticate with.
     *
     * @param username Username to authenticate with.
     * @return current connection factory
     */
    public ConnectionFactory setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * Sets the password to authenticate with.
     *
     * @param password Password to authenticate with
     * @return current connection factory
     */
    public ConnectionFactory setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Sets database.
     *
     * @param database Database to connect to.
     * @return current connection factory
     */
    public ConnectionFactory setDatabase(String database) {
        this.database = database;
        return this;
    }

    /**
     * Sets the current connection factory's parent.
     *
     * @param manager a manager to set as a parent
     * @return current connection factory
     */
    public ConnectionFactory setParent(ConnectionManager manager) {
        this.manager = manager;
        return this;
    }
}
