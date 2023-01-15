package com.epam.payments.db.dao.MySQL;

import com.epam.payments.db.dao.ConnectionPool;
import com.epam.payments.db.dao.IUserDAO;
import com.epam.payments.db.dto.UserDTO;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private static Logger LOG = Logger.getLogger(ConnectionPool.class.getName());

    @Override
    public List<UserDTO> getAllUsers() {
        LOG.trace("Start tracing UserDAO#getAllUsers");

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

    @Override
    public UserDTO createUser(String username, String password) {
        LOG.trace("Start tracing UserDAO#createUser");
        UserDTO user = null;
        Long id = -1L;

        try (Connection connection = ConnectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
                    connection.setAutoCommit(false);
                    statement.setString(1, username);
                    statement.setString(2, password);
                    statement.executeUpdate();
                    PreparedStatement stmt = connection.prepareStatement(Query.SELECT_LAST_USER_ID);
                    stmt.execute();
                    ResultSet resultSet = stmt.getResultSet();
                    if (resultSet.next()) {
                        id = resultSet.getLong("max(id)");
                    }
                    connection.commit();
                } catch (SQLException ex) {
                    LOG.error(ex.getLocalizedMessage());
                    connection.rollback();
                }
            }
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
        user = new UserDTO(id, 1L, 1L, username, password);
        return user;
    }

    @Override
    public boolean checkExistenceByUsername(String username) {
        LOG.trace("Start tracing MySQLUserDAO#findUserByLogin");

        boolean res = false;
        try (Connection connection = ConnectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.СHECK_USER_EXISTENCE_BY_NAME)) {
                    connection.setAutoCommit(false);
                    statement.setString(1, username);
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    if (resultSet.next()) {
                        int i_res = resultSet.getInt("count(*)");
                        LOG.info("RES!!!!!!!!!!!!!!!!!!!! --> " + i_res);
                        res =  i_res> 1;
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
