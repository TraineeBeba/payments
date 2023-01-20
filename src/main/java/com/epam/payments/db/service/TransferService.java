package com.epam.payments.db.service;

import com.epam.payments.db.UserStatus;
import com.epam.payments.db.dao.ITransferDAO;
import com.epam.payments.db.dao.IWalletDAO;
import com.epam.payments.db.dao.MySQL.TransferDAO;
import com.epam.payments.db.dao.MySQL.WalletDAO;
import com.epam.payments.db.dto.TransferDTO;
import com.epam.payments.db.dto.WalletDTO;


public class TransferService {
    private ITransferDAO transferDAO = new TransferDAO();
    private IWalletDAO walletDAO = new WalletDAO();

    public ITransferDAO getTransferDAO() {
        return transferDAO;
    }

    public String transferCheck(TransferDTO transferDTO) {
        return null;
//        if (!walletDAO.checkSum(transferDTO.getSum())){
//            return "Замало коштів";
//        }
//
//        WalletDTO walletDTO = walletDAO.getWalletByRecipentBill(transferDTO.getRecipient_bill_number());
//        if (walletDTO == null) {
//            return "Такого гаманця отримувача не існує";
//        }
//
//        if (walletDTO.getState().equals(UserStatus.BLOCKED.getName())){
//            return "Гаманець отримувача заблокований";
//        }
//
//        return null;
    }
}
