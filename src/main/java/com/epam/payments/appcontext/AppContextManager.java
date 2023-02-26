package com.epam.payments.appcontext;

import com.epam.payments.appcontext.exeption.creation.AppContextCreationException;
import com.epam.payments.appcontext.exeption.GetAppContextException;
import com.epam.payments.appcontext.exeption.creation.concrete.UnsupportedContextFactoryException;
import com.epam.payments.appcontext.impl.AppContext;
import com.epam.payments.appcontext.impl.AppContextFactory;
import com.epam.payments.appcontext.impl.mysql.MySqlAppContextFactory;
import com.epam.payments.appcontext.impl.postgres.PostgresAppContextFactory;
import com.epam.payments.core.database.config.DatabaseConfig;
import com.epam.payments.core.database.config.exeption.creation.DatabaseConfigCreationException;
import com.epam.payments.core.database.constant.DatabaseConstants;

public class AppContextManager implements DatabaseConstants {
    private static volatile AppContext currentAppContext;

    public static AppContext getAppContext(String configFile) throws GetAppContextException {
        if (currentAppContext == null) {
            if (currentAppContext == null) {
                try {
                    currentAppContext = createAppContext(configFile);
                } catch (AppContextCreationException e) {
                    throw new GetAppContextException(e.getMessage(), e);
                }
            }
        }
        return currentAppContext;
    }

    private static AppContext createAppContext(String configFile) throws AppContextCreationException {
        try {
            DatabaseConfig databaseConfig = new DatabaseConfig(configFile);
            String databaseType = databaseConfig.getDatabaseType();

            AppContextFactory appContextFactory;
            if (MYSQL.equalsIgnoreCase(databaseType)) {
                appContextFactory = new MySqlAppContextFactory();
            } else if (POSTGRES.equalsIgnoreCase(databaseType)) {
                appContextFactory = new PostgresAppContextFactory();
            } else {
                throw new UnsupportedContextFactoryException(databaseType);
            }

            currentAppContext = appContextFactory.createAppContext(databaseConfig);
        } catch (DatabaseConfigCreationException e) {
            throw new AppContextCreationException(e.getMessage(), e);
        }

//        catch (UnsupportedContextFactoryException e) {
//            throw new AppContextInitializationException("Error creating app context: " + e.getMessage(), e);
//        } catch (AppContextCreationException e) {
//            throw new AppContextInitializationException("Error creating app context: " + e.getMessage(), e);
//        }

        return currentAppContext;
    }
}