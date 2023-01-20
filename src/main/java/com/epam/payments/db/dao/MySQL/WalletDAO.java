package com.epam.payments.db.dao.MySQL;

import com.epam.payments.db.dao.ConnectionPool;
import com.epam.payments.db.dao.IWalletDAO;
import com.epam.payments.db.dto.TransferDTO;
import com.epam.payments.db.dto.UserDTO;
import com.epam.payments.db.dto.WalletDTO;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WalletDAO implements IWalletDAO {
    private static Logger LOG = Logger.getLogger(ConnectionPool.class.getName());

    @Override
    public List<WalletDTO> findWalletsByUserId(Long user_id) {
        LOG.trace("Start tracing WalletDAO#findWalletsByUserId");

        List<WalletDTO> walletDTOList = new ArrayList<>();
        WalletDTO walletDTO;

        try (Connection connection = ConnectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_ALL_WALLETS_BY_USER_ID)) {
                    connection.setAutoCommit(false);
                    statement.setLong(1, user_id);
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    while (resultSet.next()) {
                        walletDTO = new WalletDTO(resultSet.getLong("id"), resultSet.getLong("user_id"),
                                resultSet.getLong("state_id"), resultSet.getString("name"),
                                resultSet.getInt("bill_number"), resultSet.getDouble("balance"));
                        walletDTOList.add(walletDTO);
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
    public void doTransfer(TransferDTO transferDTO) {

    }

    @Override
    public boolean checkSum(double sum) {
        return false;
    }

    @Override
    public WalletDTO getWalletByRecipentBill(int recipient_bill_number) {
        return null;
    }
}
