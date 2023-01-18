package com.epam.payments.web.service;

import com.epam.payments.db.dao.IUserDAO;
import com.epam.payments.db.dao.IWalletDAO;
import com.epam.payments.db.dao.MySQL.WalletDAO;

public class WalletService {

    private IWalletDAO walletDAO = new WalletDAO();

    public IWalletDAO getWalletDAO() {
        return walletDAO;
    }

    public String checkCreate(String name) {
        return null;
    }

    public boolean checkBillNumber(int bill_number) {
        return !walletDAO.checkBillNumberExistance(bill_number);
    }
}
