package com.epam.payments.core.database.config;

import com.epam.payments.core.database.config.exeption.*;
import com.epam.payments.core.database.config.exeption.creation.DatabaseConfigCreationException;
import com.epam.payments.core.database.config.exeption.creation.concrete.DatabaseTypeNotSpecifiedException;
import com.epam.payments.core.database.config.exeption.creation.concrete.LoadingConfigurationFileException;
import com.epam.payments.core.database.config.exeption.creation.concrete.UnsupportedDatabaseTypeException;
import com.epam.payments.core.database.constant.DatabaseConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig implements DatabaseConstants {
    private final String dataSourceName;
    private final String databaseType;

    public DatabaseConfig(String configFile) throws DatabaseConfigCreationException {
//        try {
            Properties properties = new Properties();


//            try (InputStream input = new FileInputStream(configFile)) {
            try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("/" + configFile)) {
                properties.load(input);
            } catch (IOException e) {
                throw new LoadingConfigurationFileException(e.getMessage(), e.getCause());
            }

            // Set the data source name based on the current database type
            this.databaseType = properties.getProperty(DATABASE_TYPE);
            if (databaseType == null || databaseType.isEmpty()) {
                throw new DatabaseTypeNotSpecifiedException();
            }

            switch (databaseType) {
                case MYSQL:
                    this.dataSourceName = properties.getProperty(MYSQL_DATASOURCE_NAME);
                    break;
                case POSTGRES:
                    this.dataSourceName = properties.getProperty(POSTGRES_DATASOURCE_NAME);
                    break;
                default:
                    throw new UnsupportedDatabaseTypeException(databaseType);
            }
//        } catch (LoadingConfigurationFileException |
//                 DatabaseTypeNotSpecifiedException |
//                 UnsupportedDatabaseTypeException e) {
//            throw new DatabaseConfigCreationException(e.getMessage(), e.getCause());
////            throw new DatabaseConfigException(e.getMessage(), e.getCause());
//        }
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public String getDatabaseType() {
        return databaseType;
    }
}






