package com.epam.payments.core.database.dao.mysql;

import com.epam.payments.core.database.dao.WalletDAO;
import com.epam.payments.core.database.dao.mysql.query.WalletQuery;
import com.epam.payments.core.database.mapper.WalletEntityRowMapper;
import com.epam.payments.core.database.pool.ConnectionPool;
import com.epam.payments.core.model.dto.TransferDTO;
import com.epam.payments.core.model.entity.TransferEntity;
import com.epam.payments.core.model.entity.UserEntity;
import com.epam.payments.core.model.entity.WalletEntity;
import com.epam.payments.core.model.enums.state.WalletState;
import com.epam.payments.core.model.mapper.TransferMapper;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLWalletDAOImpl implements WalletDAO, WalletQuery {
    private static Logger LOG = Logger.getLogger(MySQLWalletDAOImpl.class.getName());
    private final WalletEntityRowMapper rowMapper = new WalletEntityRowMapper();
    private final ConnectionPool connectionPool;

    public MySQLWalletDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<WalletEntity> findAll() {
        return null;
    }

    @Override
    public WalletEntity findById(Long id) {
        LOG.trace("Start tracing MySQLWalletDAOImpl#findById");

        WalletEntity walletEntity = null;
//        try (Connection connection = connectionPool.getConnection()) {
//            if (connection != null) {
//                try (PreparedStatement statement = connection.prepareStatement(WalletRequestQuery.SELECT_WALLET_BY_ID)) {
//                    connection.setAutoCommit(false);
//                    statement.setLong(1, id);
//                    statement.execute();
//                    ResultSet resultSet = statement.getResultSet();
//                    if (resultSet.next()) {
//                        walletEntity = new WalletEntity(resultSet.getLong("id"), resultSet.getLong("user_id"),
//                                resultSet.getLong("state_id"), resultSet.getString("name"),
//                                resultSet.getInt("bill_number"), resultSet.getInt("balance"));
//                    }
//                    resultSet.close();
//                    connection.commit();
//                } catch (SQLException e) {
//                    LOG.error(e.getLocalizedMessage());
//                    connection.rollback();
//                }
//            }
//        } catch (SQLException ex) {
//            LOG.error(ex.getLocalizedMessage());
//        }
        return walletEntity;
    }

    @Override
    public void update(WalletEntity entity) {
        LOG.trace("Start tracing MySQLWalletDAOImpl#blockByWalletId");

        try (Connection connection = connectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement1 = connection.prepareStatement(UPDATE_WALLET_BY_BILL)) {
                    statement1.setLong(1, entity.getUser_id());
                    statement1.setLong(2, entity.getState().getId());
                    statement1.setString(3, entity.getName());
                    statement1.setBigDecimal(4, entity.getBalance());
                    statement1.setInt(5, entity.getBill_number());
                    statement1.execute();

                } catch (SQLException e) {
                    LOG.error(e.getLocalizedMessage());
                    connection.rollback();
                }
            }
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
    }

    @Override
    public List<WalletEntity> getSortedListByUserEntity(UserEntity userEntity, String sortBy) {
        LOG.trace("Start tracing MySQLWalletDAOImpl#getSortedListByUserEntity");

        List<WalletEntity> walletEntities = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_SORTED_WALLETS_BY_USER_ID
                     .replace(SORT_PARAM, sortBy))) {

                statement.setLong(1, userEntity.getId());
                statement.execute();

                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    walletEntities.add(rowMapper.mapRow(resultSet));
                }
                resultSet.close();
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
        LOG.info(walletEntities.size());

        return walletEntities;
    }

    @Override
    public List<WalletEntity> getSortedListByUserEntityAndState(UserEntity userEntity, WalletState state, String sortBy) {
        LOG.trace("Start tracing MySQLWalletDAOImpl#findUnblockedWalletsByUserId");

        List<WalletEntity> walletEntities = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_SORTED_WALLETS_BY_USER_ID_AND_STATE
                     .replace(SORT_PARAM, sortBy))) {

            statement.setLong(1, userEntity.getId());
            statement.setLong(2, state.getId());
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                walletEntities.add(rowMapper.mapRow(resultSet));
            }
            resultSet.close();
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
        LOG.info(walletEntities.size());

        return walletEntities;
    }

    @Override
    public void save(WalletEntity walletEntity) {
        LOG.trace("Start tracing MySQLWalletDAOImpl#save");

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_WALLET)) {
                    statement.setLong(1, walletEntity.getUser_id());
                    statement.setLong(2, walletEntity.getState().getId());
                    statement.setString(3, walletEntity.getName());
                    statement.setInt(4, walletEntity.getBill_number());
                    statement.setBigDecimal(5, walletEntity.getBalance());
                    statement.executeUpdate();
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
    }

    @Override
    public boolean existsByBill(int bill_number) {
        LOG.trace("Start tracing MySQLWalletDAOImpl#checkBillNumberExistance");

        boolean res = false;
        try (Connection connection = connectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(CHECK_BILL_NUMBER_EXISTENCE)) {
                    connection.setAutoCommit(false);
                    statement.setInt(1, bill_number);
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
    public WalletEntity findByBill(int bill_number) {
        LOG.trace("Start tracing MySQLWalletDAOImpl#findByBill");

        WalletEntity walletEntity = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_WALLET_BY_BILL)) {

                statement.setInt(1, bill_number);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    walletEntity = rowMapper.mapRow(resultSet);
                }
                resultSet.close();
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
        return walletEntity;
    }

    @Override
    public boolean existsByNameAndUserId(Long user_id, String name) {
        LOG.trace("Start tracing MySQLWalletDAOImpl#checkWalletExistenceByName");

        boolean res = false;
        try (Connection connection = connectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(CHECK_WALLET_EXISTENCE_BY_NAME_AND_USER_ID)) {
                    connection.setAutoCommit(false);
                    statement.setLong(1, user_id);
                    statement.setString(2, name);
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
    public TransferEntity doTransfer(TransferDTO transferDTO) {
        LOG.trace("Start tracing MySQLWalletDAOImpl#checkWalletExistenceByName");

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement findSenderStmt = connection.prepareStatement(SELECT_WALLET_BY_BILL);
                 PreparedStatement findRecipientStmt = connection.prepareStatement(SELECT_WALLET_BY_BILL);
                 PreparedStatement updateSenderStmt = connection.prepareStatement(UPDATE_WALLET_BY_BILL);
                 PreparedStatement updateRecipientStmt = connection.prepareStatement(UPDATE_WALLET_BY_BILL)) {

                // Start a transaction
                connection.setAutoCommit(false);

                findSenderStmt.setInt(1, transferDTO.getSender_bill_number());
                findSenderStmt.execute();
                ResultSet resultSet2 = findSenderStmt.getResultSet();
                WalletEntity senderWallet = null;
                if (resultSet2.next()) {
                    senderWallet= rowMapper.mapRow(resultSet2);
                }

                if (senderWallet.getBalance().compareTo(transferDTO.getSum()) < 0) {
                    throw new SQLException();
                }

                findRecipientStmt.setInt(1, transferDTO.getRecipient_bill_number());
                findRecipientStmt.execute();
                ResultSet resultSet1 = findRecipientStmt.getResultSet();
                WalletEntity recipientWallet = null;
                if (resultSet1.next()) {
                    recipientWallet= rowMapper.mapRow(resultSet1);
                }

                // Prepare the statement for updating the sender wallet by bill number

                updateSenderStmt.setLong(1, senderWallet.getUser_id());
                updateSenderStmt.setLong(2, senderWallet.getState().getId());
                updateSenderStmt.setString(3, senderWallet.getName());
                updateSenderStmt.setBigDecimal(4, senderWallet.getBalance().subtract(transferDTO.getSum()));
                updateSenderStmt.setInt(5, senderWallet.getBill_number());

                // Execute the update statement for sender wallet
                updateSenderStmt.executeUpdate();
//                int rowsUpdatedSender = updateSenderStmt.executeUpdate();
    //            if (rowsUpdatedSender != 1) {
    //                throw new SQLException("Failed to update the sender wallet with bill number " + billNumber);
    //            }

                // Prepare the statement for updating the recipient wallet by bill number
                updateRecipientStmt.setLong(1, recipientWallet.getUser_id());
                updateRecipientStmt.setLong(2, recipientWallet.getState().getId());
                updateRecipientStmt.setString(3, recipientWallet.getName());
                updateRecipientStmt.setBigDecimal(4, recipientWallet.getBalance().add(transferDTO.getSum()));
                updateRecipientStmt.setInt(5, recipientWallet.getBill_number());

                // Execute the update statement for recipient wallet
                updateRecipientStmt.executeUpdate();
    //            int rowsUpdatedRecipient = updateRecipientStmt.executeUpdate();
    //            if (rowsUpdatedRecipient != 1) {
    //                throw new SQLException("Failed to update the recipient wallet with bill number " + billNumber);
    //            }

                // Commit the transaction
                connection.commit();

                LOG.info("HERE");

                transferDTO.setDate(new Date(System.currentTimeMillis()));
            } catch (SQLException e){
                LOG.error(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            // Rollback the transaction in case of any errors
            LOG.error(e.getMessage());
        }

        LOG.info(transferDTO.getSum());
        TransferEntity transferEntity = TransferMapper.INSTANCE.toEntity(transferDTO);
        LOG.info(transferEntity.getSum());
        return transferEntity;
    }
}
