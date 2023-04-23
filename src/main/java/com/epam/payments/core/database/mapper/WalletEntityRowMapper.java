package com.epam.payments.core.database.mapper;

import com.epam.payments.core.database.dao.mysql.query.WalletQuery;
import com.epam.payments.core.model.entity.WalletEntity;
import com.epam.payments.core.model.enums.state.WalletState;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletEntityRowMapper implements RowMapper<WalletEntity>, WalletQuery {

    @Override
    public WalletEntity mapRow(ResultSet rs) throws SQLException {

        Long id = rs.getLong(ID);
        Long user_id = rs.getLong(USER_ID);
        WalletState state = WalletState.getState(rs.getLong(STATE_ID));
        String name = rs.getString(NAME);
        int bill_number = rs.getInt(BILL_NUMBER);
        BigDecimal balance = rs.getBigDecimal(BALANCE);

        return new WalletEntity(id, user_id, state, name, bill_number, balance);
    }
}
