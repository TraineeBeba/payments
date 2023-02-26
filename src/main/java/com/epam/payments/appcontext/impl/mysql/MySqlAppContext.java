package com.epam.payments.appcontext.impl.mysql;

import com.epam.payments.appcontext.exeption.creation.AppContextCreationException;
import com.epam.payments.appcontext.impl.AppContext;
import com.epam.payments.core.database.dao.mysql.MySQLTransferDAOImpl;
import com.epam.payments.core.database.dao.mysql.MySQLUserDAOImpl;
import com.epam.payments.core.database.dao.mysql.MySQLWalletDAOImpl;
import com.epam.payments.core.database.dao.mysql.MySQLWalletRequestDAOImpl;
import com.epam.payments.core.database.pool.ConnectionPool;
import com.epam.payments.core.database.config.DatabaseConfig;
import com.epam.payments.core.database.dao.TransferDAO;
import com.epam.payments.core.database.dao.UserDAO;
import com.epam.payments.core.database.dao.WalletDAO;
import com.epam.payments.core.database.dao.WalletRequestDAO;
import com.epam.payments.core.database.pool.exeption.creation.ConnectionPoolCreationException;
import com.epam.payments.core.database.pool.mysql.MySqlConnectionPool;
import com.epam.payments.core.service.TransferService;
import com.epam.payments.core.service.UserService;
import com.epam.payments.core.service.WalletRequestService;
import com.epam.payments.core.service.WalletService;
import com.epam.payments.core.service.impl.TransferServiceImpl;
import com.epam.payments.core.service.impl.UserServiceImpl;
import com.epam.payments.core.service.impl.WalletRequestServiceImpl;
import com.epam.payments.core.service.impl.WalletServiceImpl;

public class MySqlAppContext extends AppContext {

    public MySqlAppContext(DatabaseConfig databaseConfig) throws AppContextCreationException {
        try{
            // Create ConnectionPool object based on the current database type
            String dataSourceName = databaseConfig.getDataSourceName();
            ConnectionPool connectionPool = new MySqlConnectionPool(dataSourceName);

            // Create DAO objects based on the current database type
            UserDAO userDAO = new MySQLUserDAOImpl(connectionPool);
            WalletDAO walletDAO = new MySQLWalletDAOImpl(connectionPool);
            TransferDAO transferDAO = new MySQLTransferDAOImpl(connectionPool);
            WalletRequestDAO walletRequestDAO = new MySQLWalletRequestDAOImpl(connectionPool);

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