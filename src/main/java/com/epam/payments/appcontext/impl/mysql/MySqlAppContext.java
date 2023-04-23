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
import com.epam.payments.core.service.transfer.TransferService;
import com.epam.payments.core.service.transfer.impl.TransferServiceImpl;
import com.epam.payments.core.service.user.UserService;
import com.epam.payments.core.service.user.impl.UserServiceImpl;
import com.epam.payments.core.service.wallet_request.WalletRequestService;
import com.epam.payments.core.service.wallet.WalletService;
import com.epam.payments.core.service.wallet.impl.WalletServiceImpl;
import com.epam.payments.core.service.wallet_request.impl.WalletRequestServiceImpl;

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
            this.walletService = new WalletServiceImpl(walletDAO, userDAO);
            this.transferService = new TransferServiceImpl(transferDAO);
            this.walletRequestService = new WalletRequestServiceImpl(walletRequestDAO);

        } catch (ConnectionPoolCreationException e) {
            throw new AppContextCreationException(e.getMessage(), e);
        }
    }

    @Override
    protected UserService getUserService() {
        return this.userService;
    }

    @Override
    protected WalletService getWalletService() {
        return walletService;
    }

    @Override
    protected TransferService getTransferService() {
        return transferService;
    }

    @Override
    protected WalletRequestService getWalletRequestService() {
        return walletRequestService;
    }
}