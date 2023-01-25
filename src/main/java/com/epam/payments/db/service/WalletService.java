package com.epam.payments.db.service;

import com.epam.payments.db.dao.IWalletDAO;
import com.epam.payments.db.dao.MySQL.WalletDAO;

public class WalletService {

    private IWalletDAO walletDAO = new WalletDAO();

    public IWalletDAO getWalletDAO() {
        return walletDAO;
    }

    public String checkCreate(Long user_id, String name) {
        if (!name.equals(name.trim()) || name.isBlank()){
            return "alertError.whitespaces";
        }

        if(walletDAO.checkWalletExistenceByNameAndUserId(user_id, name)){
           return "alertError.wallet_name_exists";
        }

        if(walletDAO.checkWalletsCountByUserId(user_id)){
            return "alertError.max_wallets";
        }

        return null;
    }

    public boolean checkBillNumber(int bill_number) {
        return !walletDAO.checkBillNumberExistance(bill_number);
    }

}
