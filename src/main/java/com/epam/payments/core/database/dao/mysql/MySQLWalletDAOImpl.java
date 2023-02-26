package com.epam.payments.core.database.dao.mysql;

import com.epam.payments.core.database.dao.WalletDAO;
import com.epam.payments.core.database.dao.mysql.query.Query;
import com.epam.payments.core.database.pool.ConnectionPool;
import com.epam.payments.core.model.entity.UserEntity;
import com.epam.payments.core.model.entity.WalletEntity;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLWalletDAOImpl implements WalletDAO {
    private static Logger LOG = Logger.getLogger(MySQLWalletDAOImpl.class.getName());
    ConnectionPool connectionPool;

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
//                try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_WALLET_BY_ID)) {
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
                try (PreparedStatement statement1 = connection.prepareStatement(Query.BLOCK_WALLET)) {
                    connection.setAutoCommit(false);

                    statement1.setLong(1, entity.getState().getId());
                    statement1.setLong(2, entity.getId());
                    statement1.execute();

                    connection.commit();
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
    public List<WalletEntity> getSortedListByUserId(Long user_id, String sortBy) {
        LOG.trace("Start tracing MySQLWalletDAOImpl#findWalletsByUserId");

        List<WalletEntity> walletEntities = new ArrayList<>();
        WalletEntity walletEntity;

//        try (Connection connection = connectionPool.getConnection()) {
//            if (connection != null) {
//                try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_SORTED_WALLETS_BY_USER_ID
//                                                                                    .replace("<sortParam>", sortBy))) {
//                    connection.setAutoCommit(false);
//                    statement.setLong(1, user_id);
//                    statement.execute();
//                    ResultSet resultSet = statement.getResultSet();
//                    while (resultSet.next()) {
//                        walletEntity = new WalletEntity(resultSet.getLong("id"), resultSet.getLong("user_id"),
//                                resultSet.getLong("state_id"), resultSet.getString("name"),
//                                resultSet.getInt("bill_number"), resultSet.getDouble("balance"));
//                        walletEntities.add(walletEntity);
//
//                        LOG.info(walletEntity.toString());
//                    }
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

        return walletEntities;
    }

    @Override
    public List<WalletEntity> getUnblockedByUserId(UserEntity userEntity, String sortBy) {
        LOG.trace("Start tracing MySQLWalletDAOImpl#findUnblockedWalletsByUserId");

        List<WalletEntity> walletDTOList = new ArrayList<>();
        WalletEntity walletDTO;

//        try (Connection connection = connectionPool.getConnection()) {
//            if (connection != null) {
//                try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_SORTED_UNBLOCKED_WALLETS_BY_USER_ID
//                        .replace("<sortParam>", sortBy))) {
//                    connection.setAutoCommit(false);
//                    statement.setLong(1, userEntity.getId());
//                    statement.execute();
//                    ResultSet resultSet = statement.getResultSet();
//                    while (resultSet.next()) {
//                        walletDTO = new WalletEntity(resultSet.getLong("id"), resultSet.getLong("user_id"),
//                                resultSet.getLong("state_id"), resultSet.getString("name"),
//                                resultSet.getInt("bill_number"), resultSet.getDouble("balance"));
//                        walletDTOList.add(walletDTO);
//
//                        LOG.info(walletDTO.toString());
//                    }
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

        return walletDTOList;
    }

    @Override
    public void save(WalletEntity walletEntity) {
        LOG.trace("Start tracing MySQLWalletDAOImpl#createWallet");

//        try (Connection connection = connectionPool.getConnection()) {
//            if (connection != null) {
//                try (PreparedStatement statement = connection.prepareStatement(Query.CREATE_WALLET)) {
//                    connection.setAutoCommit(false);
//                    statement.setLong(1, walletEntity.getUser_id());
//                    statement.setString(2, walletEntity.getName());
//                    statement.setInt(3, walletEntity.getBill_number());
//                    statement.setDouble(4, walletEntity.getBalance());
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
    public boolean existsByBill(int bill_number) {
        LOG.trace("Start tracing MySQLWalletDAOImpl#checkBillNumberExistance");

        boolean res = false;
        try (Connection connection = connectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.СHECK_BILL_NUMBER_EXISTENCE)) {
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
        LOG.trace("Start tracing MySQLWalletDAOImpl#getWalletByBill");

        WalletEntity walletEntity = null;
//        try (Connection connection = connectionPool.getConnection()) {
//            if (connection != null) {
//                try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_WALLET_BY_BILL)) {
//                    connection.setAutoCommit(false);
//                    statement.setInt(1, bill_number);
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
    public boolean existsByNameAndUserId(Long user_id, String name) {
        LOG.trace("Start tracing MySQLWalletDAOImpl#checkWalletExistenceByName");

        boolean res = false;
        try (Connection connection = connectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.СHECK_WALLET_EXISTENCE_BY_NAME_AND_USER_ID)) {
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
    public boolean isValidCountByUserId(Long user_id) {
        LOG.trace("Start tracing MySQLWalletDAOImpl#checkWalletExistenceByName");

        boolean res = false;
        try (Connection connection = connectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.СHECK_WALLETS_COUNT_BY_USER_ID)) {
                    connection.setAutoCommit(false);
                    statement.setLong(1, user_id);
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    if (resultSet.next()) {
                        int i_res = resultSet.getInt("count(*)");
                        LOG.info("RES!!!!!!!!!!!!!!!!!!!! --> " + i_res);
                        res =  i_res >= 3;
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
}
