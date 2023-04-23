package com.epam.payments.core.database.dao.mysql.query;

public interface TransferQuery {
    String TRANSFER_TABLE = "transfer";
    String ID = "id";
    String STATUS_ID = "status_id";
    String SENDER_BILL_NUMBER = "sender_bill_number";
    String RECIPIENT_BILL_NUMBER = "recipient_bill_number";
    String SUM = "sum";
    String DATE = "date";
    String SORT_PARAM = "<sortParam>";
    String OFFSET_PARAM = "<offsetParam>";
    String NO_OF_RECORDS_PARAM = "<noOfRecordsParam>";

    String SELECT_SORTED_TRANSFERS_BY_USER_ID = "SELECT SQL_CALC_FOUND_ROWS * FROM `" + TRANSFER_TABLE + "` WHERE `" + SENDER_BILL_NUMBER + "` = ? OR `" + RECIPIENT_BILL_NUMBER + "` = ? ORDER BY " + SORT_PARAM + " LIMIT " + OFFSET_PARAM + ", " + NO_OF_RECORDS_PARAM;
    String CREATE_TRANSFER = "INSERT INTO `" + TRANSFER_TABLE + "` VALUES (DEFAULT, ?, ?, ?, ?, ?)";
}
