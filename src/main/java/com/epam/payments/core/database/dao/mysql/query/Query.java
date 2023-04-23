package com.epam.payments.core.database.dao.mysql.query;

public interface Query {

    String CREATE_WALLET_REQUEST = "INSERT INTO wallet_request        VALUES (DEFAULT, ?, ?, ?)";
    String СHECK_WALLET_REQUEST_EXISTENCE     =           "SELECT count(*) FROM wallet_request WHERE wallet_request.wallet_id = ? AND wallet_request.status_id = ?";


    String SELECT_SORTED_WALLET_REQUESTS =           "SELECT SQL_CALC_FOUND_ROWS * FROM wallet_request WHERE wallet_request.status_id = ? ORDER BY <sortParam> LIMIT <offsetParam>,<noOfRecordsParam>";

}
