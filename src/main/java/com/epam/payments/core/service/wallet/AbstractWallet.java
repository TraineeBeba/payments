package com.epam.payments.core.service.wallet;

import com.epam.payments.core.database.dao.UserDAO;
import com.epam.payments.core.database.dao.WalletDAO;

import java.math.BigDecimal;

public abstract class AbstractWallet {
    protected final WalletDAO walletDAO;
    protected final UserDAO userDAO;

    public AbstractWallet(WalletDAO walletDAO, UserDAO userDAO) {
        this.walletDAO = walletDAO;
        this.userDAO = userDAO;
    }
}
