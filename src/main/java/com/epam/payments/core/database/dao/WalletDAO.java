package com.epam.payments.core.database.dao;

import com.epam.payments.core.model.dto.TransferDTO;
import com.epam.payments.core.model.entity.TransferEntity;
import com.epam.payments.core.model.entity.UserEntity;
import com.epam.payments.core.model.entity.WalletEntity;
import com.epam.payments.core.model.enums.state.WalletState;

import java.math.BigDecimal;
import java.util.List;

public interface WalletDAO extends DAO<WalletEntity> {
    WalletEntity findByBill(int bill_number);

    List<WalletEntity> getSortedListByUserEntity(UserEntity userEntity, String sortBy);

    List<WalletEntity> getSortedListByUserEntityAndState(UserEntity userEntity, WalletState state, String sortBy);

    boolean existsByBill(int bill_number);

    boolean existsByNameAndUserId(Long user_id, String name);

    TransferEntity doTransfer(TransferDTO transferDTO);
}
