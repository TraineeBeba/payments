package com.epam.payments.db.dao.MySQL;

import com.epam.payments.db.dao.ConnectionPool;
import com.epam.payments.db.dao.IUserDAO;
import com.epam.payments.db.dto.UserDTO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private static Logger LOG = Logger.getLogger(ConnectionPool.class.getName());

    @Override
    public List<UserDTO> getAllUsers() {
        LOG.trace("Start tracing MySQLUserDAO#getAllUsers");

        List<UserDTO> users = new ArrayList<>();
        UserDTO user;

        try (Connection connection = ConnectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_ALL_USERS)) {
                    connection.setAutoCommit(false);
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    while (resultSet.next()) {
                        user = new UserDTO(resultSet.getLong("id"), resultSet.getLong("role_id"),
                                resultSet.getLong("state_id"), resultSet.getString("username"),
                                resultSet.getString("password"));
                        users.add(user);
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

        return users;
    }
}
