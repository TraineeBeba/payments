package com.epam.payments.core.database.mapper;

import com.epam.payments.core.database.dao.mysql.query.UserQuery;
import com.epam.payments.core.model.entity.UserEntity;
import com.epam.payments.core.model.enums.role.Role;
import com.epam.payments.core.model.enums.state.UserState;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserEntityRowMapper implements RowMapper<UserEntity>, UserQuery {

    @Override
    public UserEntity mapRow(ResultSet rs) throws SQLException {
        Long id = rs.getLong(ID);
        Role role = Role.getRole(rs.getLong(ROLE_ID));
        UserState state = UserState.getState(rs.getLong(STATE_ID));
        String username = rs.getString(USERNAME);
        String password = rs.getString(PASSWORD);

        return new UserEntity(id, role, state, username, password);
    }
}
