package com.epam.payments.core.database.dao;

import com.epam.payments.core.model.entity.UserEntity;
import com.epam.payments.core.model.entity.WalletEntity;

import java.util.List;

public interface WalletDAO extends DAO<WalletEntity> {
    WalletEntity findByBill(int bill_number);

    List<WalletEntity> getSortedListByUserId(Long user_id, String sortBy);

    List<WalletEntity> getUnblockedByUserId(UserEntity userEntity, String sortBy);

    boolean existsByBill(int bill_number);

    boolean existsByNameAndUserId(Long user_id, String name);

    boolean isValidCountByUserId(Long user_id);
}
