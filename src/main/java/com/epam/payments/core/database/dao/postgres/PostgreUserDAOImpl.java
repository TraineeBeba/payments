package com.epam.payments.core.database.dao.postgres;

import com.epam.payments.core.database.pool.ConnectionPool;
import com.epam.payments.core.database.dao.UserDAO;
import com.epam.payments.core.database.dao.postgres.query.Queries;
import com.epam.payments.core.model.entity.UserEntity;
import org.apache.log4j.Logger;

import java.util.List;


public class PostgreUserDAOImpl implements UserDAO, Queries {
    private static Logger LOG = Logger.getLogger(PostgreUserDAOImpl.class.getName());
    ConnectionPool connectionPool;

    public PostgreUserDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<UserEntity> findAll() {
        // Waiting for you to implement it :)
        return null;
    }

    @Override
    public void save(UserEntity userEntity) {
        // Waiting for you to implement it :)
    }

    @Override
    public void update(UserEntity userEntity) {
        // Waiting for you to implement it :)
    }

    @Override
    public UserEntity findById(Long userId) {
        // Waiting for you to implement it :)
        return null;
    }

    @Override
    public UserEntity findByUsername(String username) {
        // Waiting for you to implement it :)
        return null;
    }

    @Override
    public List<UserEntity> getSortedList(String userSort, int offset, int noOfRecords) {
        // Waiting for you to implement it :)
        return null;
    }

    @Override
    public boolean existsByUsername(String username) {
        // Waiting for you to implement it :)
        return false;
    }

    @Override
    public boolean existsByUsernameAndPassword(String username, String password) {
        // Waiting for you to implement it :)
        return false;
    }

    @Override
    public boolean isBlocked(String username) {
        // Waiting for you to implement it :)
        return false;
    }

    @Override
    public int getNoOfRecords() {
        // Waiting for you to implement it :)
        return 0;
    }
}
