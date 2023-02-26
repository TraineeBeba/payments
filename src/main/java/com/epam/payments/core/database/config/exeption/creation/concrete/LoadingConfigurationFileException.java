package com.epam.payments.core.database.config.exeption.creation.concrete;

import com.epam.payments.core.database.config.exeption.creation.DatabaseConfigCreationException;

public class LoadingConfigurationFileException extends DatabaseConfigCreationException {
    public LoadingConfigurationFileException(String message) {
        super(LOADING_CONFIGURATION_FILE_ERROR + message);
    }

    public LoadingConfigurationFileException(String message, Throwable cause) {
        super(LOADING_CONFIGURATION_FILE_ERROR + message, cause);
    }
}