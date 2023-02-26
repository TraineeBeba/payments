package com.epam.payments.core.database.dao.mysql;

import com.epam.payments.core.database.dao.mysql.query.Query;
import com.epam.payments.core.database.pool.ConnectionPool;
import com.epam.payments.core.model.entity.UserEntity;
import com.epam.payments.core.database.dao.UserDAO;
import com.epam.payments.core.model.enums.role.Role;
import com.epam.payments.core.model.enums.state.UserState;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySQLUserDAOImpl implements UserDAO {
    private static Logger LOG = Logger.getLogger(MySQLUserDAOImpl.class.getName());
    ConnectionPool connectionPool;

    public MySQLUserDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<UserEntity> findAll() {
        return null; //TODO
    }

    @Override
    public void save(UserEntity userEntity) {
        LOG.trace("Start tracing MySQLUserDAOImpl#createUser");

        try (Connection connection = connectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.CREATE_USER)) {
                    int ind = 0;
                    statement.setLong(++ind, userEntity.getRole().getId());
                    statement.setLong(++ind, userEntity.getState().getId());
                    statement.setString(++ind, userEntity.getUsername());
                    statement.setString(++ind, userEntity.getPassword());
                    statement.executeUpdate();
                } catch (SQLException ex) {
                    LOG.error(ex.getLocalizedMessage());
                }
            }
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
    }

    @Override
    public void update(UserEntity userEntity) {
        LOG.trace("Start tracing UserDao#updateById");

        try (Connection connection = connectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement1 = connection.prepareStatement(Query.UPDATE_USER)) {
                    int ind = 0;
                    statement1.setLong(++ind, userEntity.getRole().getId());
                    statement1.setLong(++ind, userEntity.getState().getId());
                    statement1.setString(++ind, userEntity.getUsername());
                    statement1.setString(++ind, userEntity.getPassword());
                    statement1.executeUpdate();
                } catch (SQLException e) {
                    LOG.error(e.getLocalizedMessage());
                }
            }
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
    }

    @Override
    public UserEntity findById(Long userId) {
        LOG.trace("Start tracing MySQLUserDAOImpl#getById");

        UserEntity userEntity = null;
        try (Connection connection = connectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_USER_BY_ID)) {
                    int ind = 0;
                    statement.setLong(++ind, userId);
                    statement.execute();

                    ResultSet resultSet = statement.getResultSet();
                    if (resultSet.next()) {
                        Role role = Role.getRole(resultSet.getLong("role_id"));
                        UserState state = UserState.getState(resultSet.getLong("state_id"));

                        userEntity = new UserEntity(resultSet.getLong("id"), role,
                                state, resultSet.getString("username"),
                                resultSet.getString("password"));
                    }
                    resultSet.close();
                } catch (SQLException e) {
                    LOG.error(e.getLocalizedMessage());
                }
            }
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
        return userEntity;
    }

    @Override
    public UserEntity findByUsername(String username) {
        LOG.trace("Start tracing MySQLUserDAOImpl#getByUsername");

        UserEntity userEntity = null;
        try (Connection connection = connectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_USER_BY_NAME)) {
                    int ind = 0;
                    statement.setString(++ind, username);
                    statement.execute();

                    ResultSet resultSet = statement.getResultSet();
                    if (resultSet.next()) {
                        Role role = Role.getRole(resultSet.getLong("role_id"));
                        UserState state = UserState.getState(resultSet.getLong("state_id"));

                        userEntity = new UserEntity(resultSet.getLong("id"), role,
                                state, resultSet.getString("username"),
                                resultSet.getString("password"));
                    }
                    resultSet.close();
                } catch (SQLException e) {
                    LOG.error(e.getLocalizedMessage());
                }
            }
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
        return userEntity;
    }

    @Override
    public List<UserEntity> getSortedList(String userSort, int offset, int noOfRecords) {
        LOG.trace("Start tracing MySQLUserDAOImpl#getAllUsers");

        List<UserEntity> users = new ArrayList<>();
        UserEntity userEntity;

        try (Connection connection = connectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(
                        Query
                                .SELECT_SORTED_USERS
                                .replace("<sortParam>", userSort)
                                .replace("<offsetParam>", String.valueOf(offset))
                                .replace("<noOfRecordsParam>", String.valueOf(noOfRecords))
                )) {
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    while (resultSet.next()) {
                        Role role = Role.getRole(resultSet.getLong("role_id"));
                        UserState state = UserState.getState(resultSet.getLong("state_id"));

                        userEntity = new UserEntity(resultSet.getLong("id"), role,
                                state, resultSet.getString("username"),
                                resultSet.getString("password"));

                        users.add(userEntity);
                        LOG.info(userEntity);
                    }


                    resultSet.close();
                } catch (SQLException ex) {
                    LOG.error(ex.getLocalizedMessage());
                }
            }
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }

        return users;
    }

    @Override
    public boolean existsByUsername(String username) {
        LOG.trace("Start tracing MySQLUserDAOImpl#findByUsername");

        boolean exists = false;
        try (Connection connection = connectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.EXISTS_USER_BY_NAME)) {
                    int ind = 0;
                    statement.setString(++ind, username);
                    statement.execute();

                    ResultSet resultSet = statement.getResultSet();
                    if (resultSet.next()) {
                        exists = resultSet.getBoolean("exists");
                        LOG.info("findByUsername --> " + exists);
                    }
                    resultSet.close();

                } catch (SQLException e) {
                    LOG.error(e.getLocalizedMessage());
                }
            }
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
        return exists;
    }

    @Override
    public boolean existsByUsernameAndPassword(String username, String password) {
        LOG.trace("Start tracing MySQLUserDAOImpl#findByUsernameAndPassword");

        boolean exists = false;
        try (Connection connection = connectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.EXISTS_USER_BY_NAME_AND_PASSWORD)) {
                    int ind = 0;
                    statement.setString(++ind, username);
                    statement.setString(++ind, password);
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    if (resultSet.next()) {
                        exists = resultSet.getBoolean("exists");
                        LOG.info("findByUsernameAndPassword --> " + exists);
                    }
                    resultSet.close();
                } catch (SQLException e) {
                    LOG.error(e.getLocalizedMessage());
                }
            }
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
        return exists;
    }

    @Override
    public boolean isBlocked(String username) {
        LOG.trace("Start tracing MySQLUserDAOImpl#checkUserStateByName");

        boolean isBlocked = false;
        try (Connection connection = connectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_USER_STATE_BY_NAME)) {
                    int ind = 0;
                    statement.setString(++ind, username);
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    if (resultSet.next()) {
                        isBlocked = resultSet.getLong("state_id") == UserState.BLOCKED.getId();
                    }

                    resultSet.close();
                } catch (SQLException e) {
                    LOG.error(e.getLocalizedMessage());
                    connection.rollback();
                }
            }
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
        return isBlocked;
    }
}
