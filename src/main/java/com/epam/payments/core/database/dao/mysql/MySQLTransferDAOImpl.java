package com.epam.payments.core.database.dao.mysql;

import com.epam.payments.core.database.dao.mysql.query.Query;
import com.epam.payments.core.database.pool.ConnectionPool;
import com.epam.payments.core.model.entity.TransferEntity;
import com.epam.payments.core.model.enums.status.TransferStatus;
import com.epam.payments.core.database.dao.TransferDAO;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLTransferDAOImpl implements TransferDAO {
    private static Logger LOG = Logger.getLogger(MySQLTransferDAOImpl.class.getName());
    ConnectionPool connectionPool;

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
                try (PreparedStatement statement = connection.prepareStatement(Query.CREATE_TRANSFER, Statement.RETURN_GENERATED_KEYS)) {
                    connection.setAutoCommit(false);
                    statement.setLong(1, TransferStatus.SENT.getId());
                    statement.setInt(2, transferDTO.getSender_bill_number());
                    statement.setInt(3, transferDTO.getRecipient_bill_number());
                    statement.setDouble(4, transferDTO.getSum());
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

        LOG.info("offset" + String.valueOf(offset));
        LOG.info("noOfRecords" + String.valueOf(noOfRecords));
        LOG.info("Queries " + Query
                .SELECT_SORTED_TRANSFERS_BY_USER_ID
                .replace("<sortParam>", sortBy)
                .replace("<offsetParam>", String.valueOf(offset))
                .replace("<noOfRecordsParam>", String.valueOf(noOfRecords)));

//        try (Connection connection = connectionPool.getConnection()) {
//            if (connection != null) {
//                try (PreparedStatement statement = connection.prepareStatement(
//                        Query
//                                .SELECT_SORTED_TRANSFERS_BY_USER_ID
//                                .replace("<sortParam>", sortBy)
//                                .replace("<offsetParam>", String.valueOf(offset))
//                                .replace("<noOfRecordsParam>", String.valueOf(noOfRecords))
//                    )
//                ) {
//                    connection.setAutoCommit(false);
//                    statement.setInt(1, bill_number);
//                    statement.setInt(2, bill_number);
//                    statement.execute();
//                    ResultSet resultSet = statement.getResultSet();
//                    while (resultSet.next()) {
//                        transferDTO = new TransferEntity(resultSet.getLong("id"), resultSet.getLong("status_id"),
//                                resultSet.getInt("sender_bill_number"), resultSet.getInt("recipient_bill_number"),
//                                resultSet.getDouble("sum"), resultSet.getDate("date"));
//                        transferDTOList.add(transferDTO);
//
////                        LOG.info(transferDTO.toString());
//                    }
//
//                    resultSet.close();
//                    connection.commit();
//                } catch (SQLException ex) {
//                    LOG.error(ex.getLocalizedMessage());
//                    connection.rollback();
//                }
//            }
//        } catch (SQLException ex) {
//            LOG.error(ex.getLocalizedMessage());
//        }

        return transferDTOList;
    }
}
