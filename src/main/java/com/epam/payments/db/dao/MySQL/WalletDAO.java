package com.epam.payments.db.dao.MySQL;

import com.epam.payments.db.dao.ConnectionPool;
import com.epam.payments.db.dao.IWalletDAO;
import com.epam.payments.db.dto.TransferDTO;
import com.epam.payments.db.dto.WalletDTO;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WalletDAO implements IWalletDAO {
    private static Logger LOG = Logger.getLogger(WalletDAO.class.getName());
    private int noOfRecords;

    @Override
    public List<WalletDTO> findWalletsByUserId(Long user_id, String sortBy) {
        LOG.trace("Start tracing WalletDAO#findWalletsByUserId");

        List<WalletDTO> walletDTOList = new ArrayList<>();
        WalletDTO walletDTO;

        try (Connection connection = ConnectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_SORTED_WALLETS_BY_USER_ID
                                                                                    .replace("<sortParam>", sortBy))) {
                    connection.setAutoCommit(false);
                    statement.setLong(1, user_id);
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    while (resultSet.next()) {
                        walletDTO = new WalletDTO(resultSet.getLong("id"), resultSet.getLong("user_id"),
                                resultSet.getLong("state_id"), resultSet.getString("name"),
                                resultSet.getInt("bill_number"), resultSet.getDouble("balance"));
                        walletDTOList.add(walletDTO);

                        LOG.info(walletDTO.toString());
                    }
                    resultSet.close();
                    connection.commit();
                } catch (SQLException ex) {
                    LOG.error(ex.getLocalizedMessage());
                    connection.rollback();
                }
            }
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }

        return walletDTOList;
    }

    @Override
    public void createWallet(WalletDTO walletDTO) {
        LOG.trace("Start tracing WalletDAO#createWallet");

        try (Connection connection = ConnectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.CREATE_WALLET)) {
                    connection.setAutoCommit(false);
                    statement.setLong(1, walletDTO.getUser_id());
                    statement.setString(2, walletDTO.getName());
                    statement.setInt(3, walletDTO.getBill_number());
                    statement.setDouble(4, walletDTO.getBalance());
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
    public boolean checkBillNumberExistance(int bill_number) {
        LOG.trace("Start tracing WalletDAO#checkBillNumberExistance");

        boolean res = false;
        try (Connection connection = ConnectionPool.getConnection()) {
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
    public void doTransfer(TransferDTO transferDTO, double sender_balance, double recipient_balance) {
        LOG.trace("Start tracing WalletDAO#doTransfer");

        try (Connection connection = ConnectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement1 = connection.prepareStatement(Query.UPDATE_WALLET)) {
                    connection.setAutoCommit(false);

                    statement1.setDouble(1, sender_balance - transferDTO.getSum());
                    statement1.setInt(2, transferDTO.getSender_bill_number());
                    statement1.execute();

                    PreparedStatement statement2 = connection.prepareStatement(Query.UPDATE_WALLET);
                    statement2.setDouble(1, recipient_balance + transferDTO.getSum());
                    statement2.setInt(2, transferDTO.getRecipient_bill_number());
                    statement2.execute();

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
    public WalletDTO getWalletByBill(int bill_number) {
        LOG.trace("Start tracing WalletDAO#getWalletByBill");

        WalletDTO walletDTO = null;
        try (Connection connection = ConnectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_WALLET_BY_BILL)) {
                    connection.setAutoCommit(false);
                    statement.setInt(1, bill_number);
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    if (resultSet.next()) {
                        walletDTO = new WalletDTO(resultSet.getLong("id"), resultSet.getLong("user_id"),
                                resultSet.getLong("state_id"), resultSet.getString("name"),
                                resultSet.getInt("bill_number"), resultSet.getInt("balance"));
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
        return walletDTO;
    }

    @Override
    public boolean checkWalletExistenceByNameAndUserId(Long user_id, String name) {
        LOG.trace("Start tracing WalletDAO#checkWalletExistenceByName");

        boolean res = false;
        try (Connection connection = ConnectionPool.getConnection()) {
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
    public boolean checkWalletsCountByUserId(Long user_id) {
        LOG.trace("Start tracing WalletDAO#checkWalletExistenceByName");

        boolean res = false;
        try (Connection connection = ConnectionPool.getConnection()) {
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
