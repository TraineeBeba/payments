package com.epam.payments.core.database.dao.mysql;

import com.epam.payments.core.database.dao.mysql.query.Query;
import com.epam.payments.core.database.dao.mysql.query.UserQuery;
import com.epam.payments.core.database.mapper.RowMapper;
import com.epam.payments.core.database.mapper.UserEntityRowMapper;
import com.epam.payments.core.database.pool.ConnectionPool;
import com.epam.payments.core.model.entity.UserEntity;
import com.epam.payments.core.database.dao.UserDAO;
import com.epam.payments.core.model.enums.role.Role;
import com.epam.payments.core.model.enums.state.UserState;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySQLUserDAOImpl implements UserDAO, UserQuery {
    private static Logger LOG = Logger.getLogger(MySQLUserDAOImpl.class.getName());
    private final UserEntityRowMapper rowMapper = new UserEntityRowMapper();
    private final ConnectionPool connectionPool;
    private int noOfRecords;

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

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {
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

    @Override
    public void update(UserEntity userEntity) {
        LOG.trace("Start tracing UserDao#updateById");

        try (Connection connection = connectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement1 = connection.prepareStatement(UPDATE_USER)) {
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
                try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)) {
                    int ind = 0;
                    statement.setLong(++ind, userId);
                    statement.execute();

                    ResultSet resultSet = statement.getResultSet();
                    if (resultSet.next()) {
                        userEntity = rowMapper.mapRow(resultSet);
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
                try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_NAME)) {
                    int ind = 0;
                    statement.setString(++ind, username);
                    statement.execute();

                    ResultSet resultSet = statement.getResultSet();
                    if (resultSet.next()) {
                        userEntity = rowMapper.mapRow(resultSet);
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

                                SELECT_SORTED_USERS
                                .replace("<sortParam>", userSort)
                                .replace("<offsetParam>", String.valueOf(offset))
                                .replace("<noOfRecordsParam>", String.valueOf(noOfRecords))
                );
                     PreparedStatement countStatement = connection.prepareStatement("SELECT FOUND_ROWS()");
                     ) {
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    while (resultSet.next()) {
                        userEntity = rowMapper.mapRow(resultSet);
                        users.add(userEntity);
                        LOG.info(userEntity);
                    }

                    ResultSet countResultSet = countStatement.executeQuery();
                    if (countResultSet.next()) {
                        this.noOfRecords = countResultSet.getInt(1);
                        LOG.info("Total rows count: " + noOfRecords);
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
        LOG.trace("Start tracing MySQLUserDAOImpl#existsByUsername");

        boolean exists = false;
        try (Connection connection = connectionPool.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(EXISTS_USER_BY_NAME)) {
                    int ind = 0;
                    statement.setString(++ind, username);
                    statement.execute();

                    ResultSet resultSet = statement.getResultSet();
                    if (resultSet.next()) {
                        exists = resultSet.getBoolean(EXISTS);
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
                try (PreparedStatement statement = connection.prepareStatement(EXISTS_USER_BY_NAME_AND_PASSWORD)) {
                    int ind = 0;
                    statement.setString(++ind, username);
                    statement.setString(++ind, password);
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    if (resultSet.next()) {
                        exists = resultSet.getBoolean(EXISTS);
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
                try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_STATE_BY_NAME)) {
                    int ind = 0;
                    statement.setString(++ind, username);
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    if (resultSet.next()) {
                        isBlocked = resultSet.getLong(STATE_ID) == UserState.BLOCKED.getId();
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

    @Override
    public int getNoOfRecords() {
        return this.noOfRecords;
    }
}
