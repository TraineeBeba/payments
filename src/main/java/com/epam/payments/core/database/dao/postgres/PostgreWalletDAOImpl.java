package com.epam.payments.core.database.dao.postgres;

import com.epam.payments.core.database.pool.ConnectionPool;
import com.epam.payments.core.database.dao.WalletDAO;
import com.epam.payments.core.database.dao.postgres.query.Queries;
import com.epam.payments.core.model.dto.TransferDTO;
import com.epam.payments.core.model.entity.TransferEntity;
import com.epam.payments.core.model.entity.UserEntity;
import com.epam.payments.core.model.entity.WalletEntity;
import com.epam.payments.core.model.enums.state.WalletState;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class PostgreWalletDAOImpl implements WalletDAO, Queries {
    private static Logger LOG = Logger.getLogger(PostgreWalletDAOImpl.class.getName());
    ConnectionPool connectionPool;
    private int noOfRecords;

    public PostgreWalletDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<WalletEntity> findAll() {
        // Waiting for you to implement it :)
        return null;
    }

    @Override
    public WalletEntity findById(Long id) {
        // Waiting for you to implement it :)
        return null;
    }

    @Override
    public void update(WalletEntity entity) {
        // Waiting for you to implement it :)
    }

    @Override
    public List<WalletEntity> getSortedListByUserEntity(UserEntity userEntity, String sortBy) {
        // Waiting for you to implement it :)
        return null;
    }

    @Override
    public List<WalletEntity> getSortedListByUserEntityAndState(UserEntity userEntity, WalletState state, String sortBy) {
        // Waiting for you to implement it :)
        return null;
    }

    @Override
    public void save(WalletEntity walletEntity) {
        // Waiting for you to implement it :)
    }

    @Override
    public boolean existsByBill(int bill_number) {
        // Waiting for you to implement it :)
        return false;
    }

    @Override
    public WalletEntity findByBill(int bill_number) {
        // Waiting for you to implement it :)
        return null;
    }

    @Override
    public boolean existsByNameAndUserId(Long user_id, String name) {
        // Waiting for you to implement it :)
        return false;
    }

    @Override
    public TransferEntity doTransfer(TransferDTO transferDTO) {
        // Waiting for you to implement it :)
        return null;
    }
}
