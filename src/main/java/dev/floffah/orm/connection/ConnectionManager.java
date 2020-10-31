package dev.floffah.orm.connection;

import com.sun.istack.internal.Nullable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ConnectionManager {
    List<ConnectionFactory> uninit = new ArrayList<>();
    List<Connection> connections = new ArrayList<>();

    Logger logger = null;

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

    String reset = "\033[0m";

    String fgBlack = "\033[30m";
    String fgBrightBlack = "\033[30";
    String bgBlack = "\033[40m";
    String bgBrightBlack = "\033[40";

    String fgRed = "\033[31m";
    String fgBrightRed = "\033[31";
    String bgRed = "\033[41m";
    String bgBrightRed = "\033[41";

    String fgGreen = "\033[32m";
    String fgBrightGreen = "\033[32";
    String bgGreen = "\033[42m";
    String bgBrightGreen = "\033[42";

    String fgYellow = "\033[33m";
    String fgBrightYellow = "\033[33";
    String bgYellow = "\033[43m";
    String bgBrightYellow = "\033[43";

    String fgBlue = "\033[34m";
    String fgBrightBlue = "\033[34";
    String bgBlue = "\033[44m";
    String bgBrightBlue = "\033[44";

    String fgMagenta = "\033[35m";
    String fgBrightMagenta = "\033[35";
    String bgMagenta = "\033[45m";
    String bgBrightMagenta = "\033[45";

    String fgCyan = "\033[36m";
    String fgBrightCyan = "\033[36";
    String bgCyan = "\033[46m";
    String bgBrightCyan = "\033[46";

    String fgWhite = "\033[37m";
    String fgBrightWhite = "\033[37";
    String bgWhite = "\033[47m";
    String bgBrightWhite = "\033[47";

    public void log(String message) {
        String fmsg = "&8ORM: " + message;
        String msg = fmsg
                .replaceAll("&r", reset)
                .replaceAll("&0", fgBlack)
                .replaceAll("&0b", bgBlack)
                .replaceAll("&1", fgBlue)
                .replaceAll("&1b", bgBlue)
                .replaceAll("&2", fgGreen)
                .replaceAll("&2b", bgGreen)
                .replaceAll("&3", fgCyan)
                .replaceAll("&3b", bgCyan)
                .replaceAll("&4", fgRed)
                .replaceAll("&4b", bgRed)
                .replaceAll("&5", fgMagenta)
                .replaceAll("&5b", bgMagenta)
                .replaceAll("&6", fgYellow)
                .replaceAll("&6b", bgYellow)
                .replaceAll("&7", fgWhite)
                .replaceAll("&7b", bgWhite)
                .replaceAll("&8", fgBrightBlack)
                .replaceAll("&8b", bgBrightBlack)
                .replaceAll("&9", fgBrightBlue)
                .replaceAll("&9b", bgBrightBlue)
                .replaceAll("&a", fgBrightGreen)
                .replaceAll("&ab", bgBrightGreen)
                .replaceAll("&b", fgBrightCyan)
                .replaceAll("&bb", bgBrightCyan)
                .replaceAll("&c", fgBrightRed)
                .replaceAll("&cb", bgBrightRed)
                .replaceAll("&d", fgBrightMagenta)
                .replaceAll("&db", bgBrightMagenta)
                .replaceAll("&e", fgBrightYellow)
                .replaceAll("&eb", bgBrightYellow)
                .replaceAll("&f", fgBrightWhite)
                .replaceAll("&fb", bgBrightWhite);

        logger.info(msg);
    }

    public void logQuery(String message) {
        log("&bQUERY " + message);
    }

    public void logTransaction() {
        log("&bTRANSACTION");
    }

    public void logCommit() {
        log("&bCOMMIT");
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Nullable
    public Logger getLogger() {
        return logger;
    }

    /**
     * Add an existing connection factory.
     *
     * @param factory A connection factory instance to add
     */
    public void addFactory(ConnectionFactory factory) {
        uninit.add(factory);
    }

    /**
     * Connect all created ConnectionFactorys
     *
     * @throws SQLException The SQLException thrown when creating a connection
     */
    public void connectAll() throws SQLException {
        uninit.forEach(factory -> {
            connections.add(factory.build());
        });
    }
}
