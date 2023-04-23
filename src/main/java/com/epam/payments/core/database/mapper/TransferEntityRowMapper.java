package com.epam.payments.core.database.mapper;

import com.epam.payments.core.model.entity.TransferEntity;
import com.epam.payments.core.model.enums.status.TransferStatus;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import static com.epam.payments.core.database.dao.mysql.query.TransferQuery.*;

public class TransferEntityRowMapper implements RowMapper<TransferEntity>{
    @Override
    public TransferEntity mapRow(ResultSet rs) throws SQLException {

        Long id = rs.getLong(ID);
        TransferStatus status = TransferStatus.getStatus(rs.getLong(STATUS_ID));
        int sender_bill_number = rs.getInt(SENDER_BILL_NUMBER);
        int recipient_bill_number = rs.getInt(RECIPIENT_BILL_NUMBER);
        BigDecimal sum = rs.getBigDecimal(SUM);
        Date date = rs.getDate(DATE);

        TransferEntity transferEntity = new TransferEntity(id, status, sender_bill_number, recipient_bill_number, date, sum);
        return transferEntity;
    }
}
