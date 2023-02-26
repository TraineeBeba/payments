package com.epam.payments.core.database.dao;

import com.epam.payments.core.model.entity.UserEntity;

import java.util.List;

public interface UserDAO extends DAO<UserEntity> {
    UserEntity findByUsername(String username);

    List<UserEntity> getSortedList(String userSort, int offset, int recordsPerPage);

    boolean existsByUsername(String username);

    boolean existsByUsernameAndPassword(String username, String password);

    boolean isBlocked(String username);

}
