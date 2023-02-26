package com.epam.payments.appcontext.impl.postgres;

import com.epam.payments.appcontext.exeption.creation.AppContextCreationException;
import com.epam.payments.appcontext.impl.AppContext;
import com.epam.payments.core.database.dao.postgres.PostgreTransferDAOImpl;
import com.epam.payments.core.database.dao.postgres.PostgreUserDAOImpl;
import com.epam.payments.core.database.dao.postgres.PostgreWalletDAOImpl;
import com.epam.payments.core.database.dao.postgres.PostgreWalletRequestDAOImpl;
import com.epam.payments.core.database.pool.ConnectionPool;
import com.epam.payments.core.database.config.DatabaseConfig;
import com.epam.payments.core.database.dao.TransferDAO;
import com.epam.payments.core.database.dao.UserDAO;
import com.epam.payments.core.database.dao.WalletDAO;
import com.epam.payments.core.database.dao.WalletRequestDAO;
import com.epam.payments.core.database.pool.exeption.creation.ConnectionPoolCreationException;
import com.epam.payments.core.database.pool.postgres.PostgresConnectionPool;
import com.epam.payments.core.service.TransferService;
import com.epam.payments.core.service.UserService;
import com.epam.payments.core.service.WalletRequestService;
import com.epam.payments.core.service.WalletService;
import com.epam.payments.core.service.impl.TransferServiceImpl;
import com.epam.payments.core.service.impl.UserServiceImpl;
import com.epam.payments.core.service.impl.WalletRequestServiceImpl;
import com.epam.payments.core.service.impl.WalletServiceImpl;


public class PostgresAppContext extends AppContext {
    public PostgresAppContext(DatabaseConfig databaseConfig) throws AppContextCreationException {
        try {
            // Create ConnectionPool object based on the current database type
            String dataSourceName = databaseConfig.getDataSourceName();
            ConnectionPool connectionPool = new PostgresConnectionPool(dataSourceName);

            // Create DAO objects based on the current database type
            UserDAO userDAO = new PostgreUserDAOImpl(connectionPool);
            WalletDAO walletDAO = new PostgreWalletDAOImpl(connectionPool);
            TransferDAO transferDAO = new PostgreTransferDAOImpl(connectionPool);
            WalletRequestDAO walletRequestDAO = new PostgreWalletRequestDAOImpl(connectionPool);

            // Create service objects based on the DAO factory
            this.userService = new UserServiceImpl(userDAO);
            this.walletService = new WalletServiceImpl(walletDAO);
            this.transferService = new TransferServiceImpl(transferDAO);
            this.walletRequestService = new WalletRequestServiceImpl(walletRequestDAO);

        } catch (ConnectionPoolCreationException e) {
            throw new AppContextCreationException(e.getMessage(), e);
        }
    }

    @Override
    public UserService getUserService() {
        return this.userService;
    }

    @Override
    public WalletService getWalletService() {
        return walletService;
    }

    @Override
    public TransferService getTransferService() {
        return transferService;
    }

    @Override
    public WalletRequestService getWalletRequestService() {
        return walletRequestService;
    }
}