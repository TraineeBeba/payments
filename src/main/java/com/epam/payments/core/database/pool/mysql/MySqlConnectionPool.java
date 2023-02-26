package com.epam.payments.core.database.pool.mysql;

import com.epam.payments.core.database.pool.ConnectionPool;
import com.epam.payments.core.database.pool.exeption.creation.ConnectionPoolCreationException;
import com.epam.payments.core.database.pool.exeption.creation.concrete.DataSourceCreationException;
import com.epam.payments.core.database.pool.exeption.creation.concrete.InitialContextCreationException;
import com.epam.payments.core.database.pool.postgres.PostgresConnectionPool;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

public class MySqlConnectionPool implements ConnectionPool {
    private static final Logger LOG = Logger.getLogger(PostgresConnectionPool.class);
    private final DataSource dataSource;
    private final ThreadLocal<Context> CONTEXT;

    public MySqlConnectionPool(String dataSourceName) throws ConnectionPoolCreationException {
        AtomicReference<InitialContextCreationException> exception = new AtomicReference<>();
        CONTEXT = ThreadLocal.withInitial(() -> {
            try {
                return new InitialContext();
            } catch (NamingException ex) {
//                LOG.error("Failed to create InitialContext", ex);
                exception.set(new InitialContextCreationException(ex.getMessage(), ex));
                return null;
            }
        });
        if (CONTEXT.get() == null) {
            throw exception.get();
        }
        this.dataSource = createDataSource(dataSourceName);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private DataSource createDataSource(String dataSourceName) throws DataSourceCreationException {
        try {
            Context initCtx = CONTEXT.get();
            Context envCtx = (Context) initCtx.lookup("java:/comp/env");
            return (DataSource) envCtx.lookup(dataSourceName);
        } catch (NamingException e) {
//            LOG.error("Failed to create DataSource", e);
            throw new DataSourceCreationException(e.getMessage(), e);
        }
    }
}