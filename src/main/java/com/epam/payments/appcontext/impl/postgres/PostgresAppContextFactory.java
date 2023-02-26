package com.epam.payments.appcontext.impl.postgres;

import com.epam.payments.appcontext.exeption.creation.AppContextCreationException;
import com.epam.payments.appcontext.impl.AppContext;
import com.epam.payments.appcontext.impl.AppContextFactory;
import com.epam.payments.core.database.config.DatabaseConfig;

public class PostgresAppContextFactory implements AppContextFactory {
    @Override
    public AppContext createAppContext(DatabaseConfig databaseConfig) throws AppContextCreationException {
        return new PostgresAppContext(databaseConfig);
    }
}