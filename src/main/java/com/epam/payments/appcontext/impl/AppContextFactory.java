package com.epam.payments.appcontext.impl;

import com.epam.payments.appcontext.exeption.creation.AppContextCreationException;
import com.epam.payments.core.database.config.DatabaseConfig;

public interface AppContextFactory {
    AppContext createAppContext(DatabaseConfig databaseConfig) throws AppContextCreationException;
}