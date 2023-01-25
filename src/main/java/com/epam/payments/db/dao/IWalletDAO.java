package com.epam.payments.db.dao;

import com.epam.payments.db.dto.TransferDTO;
import com.epam.payments.db.dto.WalletDTO;

import java.util.List;

public interface IWalletDAO {

    List<WalletDTO> findWalletsByUserId(Long user_id, String sortBy);
    void createWallet(WalletDTO walletDTO);

    boolean checkBillNumberExistance(int bill_number);

    WalletDTO getWalletByBill(int bill_number);

    void doTransfer(TransferDTO transferDTO, double sender_balance, double recipient_balance);

    boolean checkWalletExistenceByNameAndUserId(Long user_id, String name);

    boolean checkWalletsCountByUserId(Long user_id);

}
