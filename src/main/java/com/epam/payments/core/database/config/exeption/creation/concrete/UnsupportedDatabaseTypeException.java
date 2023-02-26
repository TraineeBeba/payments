package com.epam.payments.core.database.config.exeption.creation.concrete;

import com.epam.payments.core.database.config.exeption.creation.DatabaseConfigCreationException;

public class UnsupportedDatabaseTypeException extends DatabaseConfigCreationException {
    public UnsupportedDatabaseTypeException(String databaseType) {
        super(UNSUPPORTED_DATABASE_TYPE + databaseType);
    }

    public UnsupportedDatabaseTypeException(String databaseType, Throwable cause) {
        super(UNSUPPORTED_DATABASE_TYPE + databaseType, cause);
    }
}