package com.epam.payments.core.service;

import com.epam.payments.core.model.entity.WalletEntity;

import java.math.BigDecimal;

public interface WalletService extends Service{
    String isValidCreate(WalletEntity walletEntity);

    boolean existsByBill(int bill_number);

    String isValidTopUp(WalletEntity walletEntity, BigDecimal sum);

}
