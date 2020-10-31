package dev.floffah.orm.errors;

import java.sql.SQLException;
import java.util.UUID;

public class TransactionException extends SQLException {
    UUID transaction;

    public TransactionException(String message, UUID transaction) {
        super(message);
        this.transaction = transaction;
    }

    public TransactionException(UUID transaction) {
        this.transaction = transaction;
    }

    public UUID getTransactionID() {
        return transaction;
    }
}
