package com.epam.payments.core.database.pool.exeption.creation;

import com.epam.payments.core.database.pool.exeption.constant.ConnectionPoolErrorMessages;

public class ConnectionPoolCreationException extends Exception implements ConnectionPoolErrorMessages {
    public ConnectionPoolCreationException(String message) {
        super(CONNECTION_POOL_CREATION_ERROR + message);
    }

    public ConnectionPoolCreationException(String message, Throwable cause) {
        super(CONNECTION_POOL_CREATION_ERROR + message, cause);
    }
}
