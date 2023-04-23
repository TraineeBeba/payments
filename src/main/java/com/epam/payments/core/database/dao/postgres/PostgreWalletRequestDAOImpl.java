package com.epam.payments.core.database.dao.postgres;

import com.epam.payments.core.database.dao.WalletRequestDAO;
import com.epam.payments.core.database.pool.ConnectionPool;
import com.epam.payments.core.database.dao.postgres.query.Queries;
import com.epam.payments.core.model.entity.WalletRequestEntity;
import com.epam.payments.core.model.enums.status.RequestStatus;
import org.apache.log4j.Logger;

import java.util.List;

public class PostgreWalletRequestDAOImpl implements WalletRequestDAO, Queries {
    private static Logger LOG = Logger.getLogger(PostgreWalletRequestDAOImpl.class.getName());
    ConnectionPool connectionPool;
    private int noOfRecords;

    public PostgreWalletRequestDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<WalletRequestEntity> findAll() {
        return null;
    }

    @Override
    public WalletRequestEntity findById(Long id) {
        return null;
    }

    @Override
    public void update(WalletRequestEntity entity) {
        // Waiting for you to implement it :)
    }

    @Override
    public void save(WalletRequestEntity walletRequestDTO) {
        // Waiting for you to implement it :)
    }

    @Override
    public boolean existsByWalletIdAndStatus(Long walletId, RequestStatus requestStatus) {
        // Waiting for you to implement it :)
        return false;
    }

    @Override
    public List<WalletRequestEntity> getSortedList(String walletRequestSort, int offset, int noOfRecords, RequestStatus status) {
        // Waiting for you to implement it :)
        return null;
    }

    @Override
    public int getNoOfRecords() {
        // Waiting for you to implement it :)
        return 0;
    }

    @Override
    public WalletRequestEntity findByWalletIDandRequestStatus(Long walletId, RequestStatus requestStatus) {
        // Waiting for you to implement it :)
        return null;
    }
}
