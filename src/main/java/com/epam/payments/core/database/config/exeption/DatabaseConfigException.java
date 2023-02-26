package com.epam.payments.core.database.config.exeption;

import com.epam.payments.core.database.config.exeption.constant.DatabaseConfigErrorMessages;

public class DatabaseConfigException extends Exception implements DatabaseConfigErrorMessages {
    public DatabaseConfigException(String message) {
        super(DATABASE_CONFIG_ERROR + message);
    }

    public DatabaseConfigException(String message, Throwable cause) {
        super(DATABASE_CONFIG_ERROR + message, cause);
    }
}