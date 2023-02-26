package com.epam.payments.core.database.pool.exeption.creation.concrete;

import com.epam.payments.core.database.pool.exeption.creation.ConnectionPoolCreationException;

public class InitialContextCreationException extends ConnectionPoolCreationException {
    public InitialContextCreationException(String message) {
        super(INITIAL_CONTEXT_CREATION_ERROR + message);
    }

    public InitialContextCreationException(String message, Throwable cause) {
        super(INITIAL_CONTEXT_CREATION_ERROR + message, cause);
    }
}
