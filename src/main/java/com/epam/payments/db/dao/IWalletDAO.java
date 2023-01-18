package com.epam.payments.db.dao;

import com.epam.payments.db.dto.WalletDTO;

import java.util.List;

public interface IWalletDAO {

    List<WalletDTO> findWalletsByUserId(Long user_id);


    void createWallet(WalletDTO walletDTO);

    boolean checkBillNumberExistance(int bill_number);
}
