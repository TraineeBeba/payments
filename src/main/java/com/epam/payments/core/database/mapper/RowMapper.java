package com.epam.payments.core.database.mapper;

import com.epam.payments.core.model.entity.UserEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    T mapRow(ResultSet rs) throws SQLException;
}
