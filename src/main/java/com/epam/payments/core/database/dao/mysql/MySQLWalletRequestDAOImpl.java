package com.epam.payments.core.database.dao.mysql;

import com.epam.payments.core.database.dao.WalletRequestDAO;
import com.epam.payments.core.database.dao.mysql.query.Query;
import com.epam.payments.core.database.pool.ConnectionPool;
import com.epam.payments.core.model.entity.WalletRequestEntity;
import com.epam.payments.core.model.enums.status.RequestStatus;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLWalletRequestDAOImpl implements WalletRequestDAO {
    private static Logger LOG = Logger.getLogger(MySQLWalletRequestDAOImpl.class.getName());
    ConnectionPool connectionPool;
    private int noOfRecords;

    public MySQLWalletRequestDAOImpl(ConnectionPool connectionPool) {
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

    }

    @Override
    public void save(WalletRequestEntity walletRequestDTO) {
        LOG.trace("Start tracing MySQLWalletRequestDAOImpl#createRequest");

//        try (Connection connection = connectionPool.getConnection()) {
//            if (connection != null) {
//                try (PreparedStatement statement = connection.prepareStatement(Query.CREATE_WALLET_REQUEST)) {
//                    connection.setAutoCommit(false);
//
//                    LOG.info(walletRequestDTO.toString());
//                    statement.setLong(1, walletRequestDTO.getWallet_id());
//                    statement.setLong(2, walletRequestDTO.getStatus_id());
//                    statement.setLong(3, walletRequestDTO.getType_id());
//
//                    statement.executeUpdate();
//                    connection.commit();
//                } catch (SQLException ex) {
//                    LOG.error(ex.getLocalizedMessage());
//                    connection.rollback();
//                }
//            }
//        } catch (SQLException ex) {
//            LOG.error(ex.getLocalizedMessage());
//        }
    }

    @Override
    public boolean existsByWalletIdAndStatusID(Long walletId, Long status_id) {
        LOG.trace("Start tracing MySQLWalletRequestDAOImpl#exists");

        boolean res = false;
        try (Connection connection = connectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.СHECK_WALLET_REQUEST_EXISTENCE)) {
                    connection.setAutoCommit(false);
                    statement.setLong(1, walletId);
                    statement.setLong(2, status_id);
                    statement.execute();

                    ResultSet resultSet = statement.getResultSet();
                    if (resultSet.next()) {
                        int i_res = resultSet.getInt("count(*)");
                        LOG.info("RES!!!!!!!!!!!!!!!!!!!! --> " + i_res);
                        res =  i_res >= 1;
                    }
                    resultSet.close();
                    connection.commit();
                } catch (SQLException e) {
                    LOG.error(e.getLocalizedMessage());
                    connection.rollback();
                }
            }
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
        return res;
    }

    @Override
    public List<WalletRequestEntity> getSortedList(String walletRequestSort, int offset, int noOfRecords, RequestStatus status) {
        LOG.trace("Start tracing MySQLWalletDAOImpl#findWalletsByUserId");

        List<WalletRequestEntity> walletRequestDTOList = new ArrayList<>();
        WalletRequestEntity walletRequestDTO;

//        LOG.info("offset" + String.valueOf(offset));
//        LOG.info("noOfRecords" + String.valueOf(noOfRecords));
//        LOG.info("Queries " + Query
//                .SELECT_SORTED_WALLET_REQUESTS
//                .replace("<sortParam>", walletRequestSort)
//                .replace("<offsetParam>", String.valueOf(offset))
//                .replace("<noOfRecordsParam>", String.valueOf(noOfRecords)));
//
//        try (Connection connection = connectionPool.getConnection()) {
//            if (connection != null) {
//                try (PreparedStatement statement = connection.prepareStatement(
//                        Query
//                                .SELECT_SORTED_WALLET_REQUESTS
//                                .replace("<sortParam>", walletRequestSort)
//                                .replace("<offsetParam>", String.valueOf(offset))
//                                .replace("<noOfRecordsParam>", String.valueOf(noOfRecords)))
//                ) {
//                    connection.setAutoCommit(false);
//                    statement.setLong(1, status.getId());
//                    statement.execute();
//
//                    ResultSet resultSet = statement.getResultSet();
//                    while (resultSet.next()) {
//                        walletRequestDTO = new WalletRequestEntity(resultSet.getLong("id"), resultSet.getLong("wallet_id"),
//                                resultSet.getLong("status_id"), resultSet.getLong("type_id"));
//                        walletRequestDTOList.add(walletRequestDTO);
//
////                        LOG.info(transferDTO.toString());
//                    }
//
//                    statement.executeQuery("SELECT FOUND_ROWS()");
//                    resultSet = statement.getResultSet();
//                    if(resultSet.next()) this.noOfRecords = resultSet.getInt(1);
//                    LOG.info("FOUND_ROWS -->" + this.noOfRecords);
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

        return walletRequestDTOList;
    }
}
