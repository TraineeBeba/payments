package com.epam.payments.core.database.config.exeption.creation;

import com.epam.payments.core.database.config.exeption.DatabaseConfigException;

public class DatabaseConfigCreationException extends DatabaseConfigException {
    public DatabaseConfigCreationException(String message) {
        super(DATABASE_CONFIG_CREATION_ERROR + message);
    }

    public DatabaseConfigCreationException(String message, Throwable cause) {
        super(DATABASE_CONFIG_CREATION_ERROR + message, cause);
    }
}