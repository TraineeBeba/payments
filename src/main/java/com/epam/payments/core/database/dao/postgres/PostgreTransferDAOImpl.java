package com.epam.payments.core.database.dao.postgres;

import com.epam.payments.core.database.pool.ConnectionPool;
import com.epam.payments.core.database.dao.TransferDAO;
import com.epam.payments.core.database.dao.postgres.query.Queries;
import com.epam.payments.core.model.entity.TransferEntity;
import org.apache.log4j.Logger;

import java.util.List;


public class PostgreTransferDAOImpl implements TransferDAO, Queries {
    private static Logger LOG = Logger.getLogger(PostgreTransferDAOImpl.class.getName());
    ConnectionPool connectionPool;

    public PostgreTransferDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<TransferEntity> findAll() {
        // Waiting for you to implement it :)
        return null;
    }

    @Override
    public TransferEntity findById(Long id) {
        // Waiting for you to implement it :)
        return null;
    }

    @Override
    public void update(TransferEntity entity) {
        // Waiting for you to implement it :)
    }

    @Override
    public void save(TransferEntity transferDTO) {
        // Waiting for you to implement it :)
    }

    @Override
    public List<TransferEntity> getSortedListByBill(int bill_number, String sortBy, int offset, int noOfRecords) {
        // Waiting for you to implement it :)
        return null;
    }
}
