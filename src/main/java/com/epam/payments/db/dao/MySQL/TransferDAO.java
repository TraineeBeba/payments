package com.epam.payments.db.dao.MySQL;

import com.epam.payments.db.dao.ConnectionPool;
import com.epam.payments.db.dao.ITransferDAO;
import com.epam.payments.db.dto.TransferDTO;
import com.epam.payments.db.dto.UserDTO;
import com.epam.payments.db.dto.WalletDTO;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransferDAO implements ITransferDAO {
    private static Logger LOG = Logger.getLogger(TransferDAO.class.getName());
    private int noOfRecords;

    @Override
    public void createTransfer(TransferDTO transferDTO) {
        LOG.trace("Start tracing TransferDAO#createTranfer");

        try (Connection connection = ConnectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.CREATE_TRANSFER, Statement.RETURN_GENERATED_KEYS)) {
                    connection.setAutoCommit(false);
                    statement.setInt(1, transferDTO.getSender_bill_number());
                    statement.setInt(2, transferDTO.getRecipient_bill_number());
                    statement.setDouble(3, transferDTO.getSum());
                    statement.setDate(4, transferDTO.getDate());
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
    public List<TransferDTO> findTransfersByBill(int bill_number, String sortBy, int offset, int noOfRecords) {
        LOG.trace("Start tracing WalletDAO#findWalletsByUserId");

        List<TransferDTO> transferDTOList = new ArrayList<>();
        TransferDTO transferDTO;

        LOG.info("offset" + String.valueOf(offset));
        LOG.info("noOfRecords" + String.valueOf(noOfRecords));
        LOG.info("Query " + Query
                .SELECT_SORTED_TRANSFERS_BY_USER_ID
                .replace("<sortParam>", sortBy)
                .replace("<offsetParam>", String.valueOf(offset))
                .replace("<noOfRecordsParam>", String.valueOf(noOfRecords)));

        try (Connection connection = ConnectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(
                        Query
                                .SELECT_SORTED_TRANSFERS_BY_USER_ID
                                .replace("<sortParam>", sortBy)
                                .replace("<offsetParam>", String.valueOf(offset))
                                .replace("<noOfRecordsParam>", String.valueOf(noOfRecords))
                    )
                ) {
                    connection.setAutoCommit(false);
                    statement.setInt(1, bill_number);
                    statement.setInt(2, bill_number);
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    while (resultSet.next()) {
                        transferDTO = new TransferDTO(resultSet.getLong("id"), resultSet.getLong("status_id"),
                                resultSet.getInt("sender_bill_number"), resultSet.getInt("recipient_bill_number"),
                                resultSet.getDouble("sum"), resultSet.getDate("date"));
                        transferDTOList.add(transferDTO);

//                        LOG.info(transferDTO.toString());
                    }

                    statement.executeQuery("SELECT FOUND_ROWS()");
                    resultSet = statement.getResultSet();
                    if(resultSet.next()) this.noOfRecords = resultSet.getInt(1);
                    LOG.info("FOUND_ROWS -->" + this.noOfRecords);

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

        return transferDTOList;
    }

    @Override
    public int getNoOfRecords() {
        return this.noOfRecords;
    }
}
