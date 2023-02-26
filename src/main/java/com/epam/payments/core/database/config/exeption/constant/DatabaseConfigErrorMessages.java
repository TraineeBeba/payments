package com.epam.payments.core.database.config.exeption.constant;

public interface DatabaseConfigErrorMessages {
    String DATABASE_CONFIG_ERROR = "Error in DatabaseConfig: ";
    String DATABASE_CONFIG_CREATION_ERROR = "Error creating database configuration: ";
    String UNSUPPORTED_DATABASE_TYPE = "Unsupported database type: ";
    String LOADING_CONFIGURATION_FILE_ERROR = "Error loading database configuration file: ";
    String DATABASE_TYPE_NOT_SPECIFIED = "Database type not specified in configuration file.";
}