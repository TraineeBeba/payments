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
        if (transferDTO.getRecipient_bill_number() == transferDTO.getSender_bill_number()){
            return "alertError.bad_selftransfer";
        }

        WalletDTO senderWallet = walletDAO.getWalletByBill(transferDTO.getSender_bill_number());
        if (Double.compare(senderWallet.getBalance(), transferDTO.getSum()) < 0){
            return "alertError.few_balance";
        }

        WalletDTO recipientWallet = walletDAO.getWalletByBill(transferDTO.getRecipient_bill_number());
        if (recipientWallet == null) {
            return "alertError.no_wallet";
        }

        if (recipientWallet.getState().equals(UserStatus.BLOCKED.getName())){
            return "alertError.wallet_blocked";
        }

        return null;
    }
}
