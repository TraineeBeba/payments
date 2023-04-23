package com.epam.payments.core.database.dao.mysql;

import com.epam.payments.core.database.dao.mysql.query.TransferQuery;
import com.epam.payments.core.database.mapper.TransferEntityRowMapper;
import com.epam.payments.core.database.pool.ConnectionPool;
import com.epam.payments.core.model.entity.TransferEntity;
import com.epam.payments.core.model.enums.status.TransferStatus;
import com.epam.payments.core.database.dao.TransferDAO;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLTransferDAOImpl implements TransferDAO, TransferQuery {
    private static Logger LOG = Logger.getLogger(MySQLTransferDAOImpl.class.getName());
    private final TransferEntityRowMapper rowMapper = new TransferEntityRowMapper();
    private final ConnectionPool connectionPool;
    private int noOfRecords;

    public MySQLTransferDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<TransferEntity> findAll() {
        return null; //TODO
    }

    @Override
    public TransferEntity findById(Long id) {
        return null;
    }

    @Override
    public void update(TransferEntity entity) {

    }

    @Override
    public void save(TransferEntity transferDTO) {
        LOG.trace("Start tracing MySQLTransferDAOImpl#createTranfer");

        try (Connection connection = connectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(CREATE_TRANSFER)) {
                    connection.setAutoCommit(false);
                    statement.setLong(1, TransferStatus.SENT.getId());
                    statement.setInt(2, transferDTO.getSender_bill_number());
                    statement.setInt(3, transferDTO.getRecipient_bill_number());
                    statement.setBigDecimal(4, transferDTO.getSum());
                    statement.setDate(5, transferDTO.getDate());
                    statement.executeUpdate();
                    connection.commit();
                } catch (SQLException ex) {
                    LOG.error(ex.getLocalizedMessage());
                    connection.rollback();
                }
            }
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
    }

    @Override
    public List<TransferEntity> getSortedListByBill(int bill_number, String sortBy, int offset, int noOfRecords) {
        LOG.trace("Start tracing MySQLWalletDAOImpl#findWalletsByUserId");

        List<TransferEntity> transferDTOList = new ArrayList<>();
        TransferEntity transferDTO;

//        LOG.info("offset" + offset);
//        LOG.info("noOfRecords" + noOfRecords);
//        LOG.info("Queries " + SELECT_SORTED_TRANSFERS_BY_USER_ID
//                .replace("<sortParam>", sortBy)
//                .replace("<offsetParam>", String.valueOf(offset))
//                .replace("<noOfRecordsParam>", String.valueOf(noOfRecords)));

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                SELECT_SORTED_TRANSFERS_BY_USER_ID
                        .replace(SORT_PARAM, sortBy)
                        .replace(OFFSET_PARAM, String.valueOf(offset))
                        .replace(NO_OF_RECORDS_PARAM, String.valueOf(noOfRecords))
            );
            PreparedStatement countStatement = connection.prepareStatement("SELECT FOUND_ROWS()");
        ) {
                statement.setInt(1, bill_number);
                statement.setInt(2, bill_number);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    transferDTO = rowMapper.mapRow(resultSet);
                    transferDTOList.add(transferDTO);
//                        LOG.info(transferDTO.toString());
                }

            ResultSet countResultSet = countStatement.executeQuery();
            if (countResultSet.next()) {
                this.noOfRecords = countResultSet.getInt(1);
                LOG.info("Total rows count: " + noOfRecords);
            }

                resultSet.close();
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }

        return transferDTOList;
    }

    @Override
    public int getNoOfRecords() {
        return this.noOfRecords;
    }
}
