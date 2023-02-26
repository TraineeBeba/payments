package com.epam.payments.core.service.impl;

import com.epam.payments.core.database.dao.WalletDAO;
import com.epam.payments.core.model.entity.UserEntity;
import com.epam.payments.core.model.entity.WalletEntity;
import com.epam.payments.core.service.WalletService;

import java.math.BigDecimal;
import java.util.List;

public class WalletServiceImpl implements WalletService {
    private final BigDecimal MAX_BALANCE = new BigDecimal(20_000.0);
    private static WalletServiceImpl INSTANCE;
    private final WalletDAO walletDAO;

    public WalletServiceImpl(WalletDAO walletDAO) {
        this.walletDAO = walletDAO;
    }

    @Override
    public String isValidCreate(WalletEntity walletEntity) {
        if (!walletEntity.getName().equals(walletEntity.getName().trim())
                || walletEntity.getName().isBlank()){
            return "alertError.whitespaces";
        }

        if(walletDAO.existsByNameAndUserId(walletEntity.getUser().getId(), walletEntity.getName())){
           return "alertError.wallet_name_exists";
        }

        if(walletDAO.isValidCountByUserId(walletEntity.getId())){
            return "alertError.max_wallets";
        }

        return null;
    }

    @Override
    public boolean existsByBill(int bill_number) {

        return !walletDAO.existsByBill(bill_number);
    }

    @Override
    public String isValidTopUp(WalletEntity walletEntity, BigDecimal sum) {
        BigDecimal balance = walletEntity.getBalance().add(sum);
        if (balance.compareTo(MAX_BALANCE) > 0){
            return "alertError.max-top-up";
        }

        return null;
    }


    public List<WalletEntity> findAll() {
        return null;
    }

    public WalletEntity findById(Long id) {
        return walletDAO.findById(id);
    }

    public void save(WalletEntity entity) {
        walletDAO.save(entity);
    }

    public void update(WalletEntity entity) {
        walletDAO.update(entity);
    }


    public WalletEntity findByBill(int bill_number) {
        return walletDAO.findByBill(bill_number);
    }

    public List<WalletEntity> getSortedListByUserId(Long user_id, String sortBy) {
        return walletDAO.getSortedListByUserId(user_id, sortBy);
    }

    public List<WalletEntity> getUnblockedByUserId(UserEntity userEntity, String sortBy) {
        return walletDAO.getUnblockedByUserId(userEntity, sortBy);
    }

    public boolean existsByNameAndUserId(Long user_id, String name) {
        return walletDAO.existsByNameAndUserId(user_id, name);
    }

    public boolean isValidCountByUserId(Long user_id) {
        return walletDAO.isValidCountByUserId(user_id);
    }
}
