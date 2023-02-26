package com.epam.payments.core.database.pool.exeption.creation.concrete;

import com.epam.payments.core.database.pool.exeption.creation.ConnectionPoolCreationException;

public class DataSourceCreationException extends ConnectionPoolCreationException {
    public DataSourceCreationException(String message) {
        super(DATA_SOURCE_CREATION_ERROR + message);
    }

    public DataSourceCreationException(String message, Throwable cause) {
        super(DATA_SOURCE_CREATION_ERROR + message, cause);
    }
}
