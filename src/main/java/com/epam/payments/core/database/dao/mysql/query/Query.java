package com.epam.payments.core.database.dao.mysql.query;

public interface Query {
    // TODO split into 4 interfaces
    String CREATE_USER                      = "INSERT INTO user VALUES (DEFAULT, ?, ?, ?, ?)";
    String SELECT_USER_BY_ID                = "SELECT * FROM user WHERE user.id = ? ";
    String SELECT_USER_BY_NAME              = "SELECT * FROM user WHERE user.username = ? ";
    String UPDATE_USER                      = "UPDATE user SET SET user.role_id=?, user.state_id=?, user.username=?, user.password=? WHERE user.id=?\"";
    String EXISTS_USER_BY_NAME              = "SELECT EXISTS (SELECT * FROM user WHERE user.username = ?) AS exists";
    String EXISTS_USER_BY_NAME_AND_PASSWORD = "SELECT EXISTS (SELECT * FROM user WHERE user.username = ? AND user.password = ?) AS exists";
    String SELECT_USER_STATE_BY_NAME        = "SELECT user.state_id FROM user WHERE user.username = ?";

    String SELECT_SORTED_WALLETS_BY_USER_ID =           "SELECT * FROM wallet WHERE wallet.user_id = ? ORDER BY <sortParam>";
    String SELECT_SORTED_UNBLOCKED_WALLETS_BY_USER_ID =           "SELECT * FROM wallet WHERE wallet.user_id = ? AND wallet.state_id = '1' ORDER BY <sortParam>";
    String SELECT_SORTED_TRANSFERS_BY_USER_ID =           "SELECT SQL_CALC_FOUND_ROWS * FROM transfer WHERE transfer.sender_bill_number = ? OR transfer.recipient_bill_number = ? ORDER BY <sortParam> LIMIT <offsetParam>,<noOfRecordsParam>";
    String СHECK_BILL_NUMBER_EXISTENCE        =           "SELECT count(*) FROM wallet WHERE wallet.bill_number = ?" ;
    String CREATE_WALLET                      =           "INSERT INTO wallet VALUES (DEFAULT, ?, 1, ?, ?, ?)";
    String СHECK_WALLET_EXISTENCE_BY_NAME_AND_USER_ID     =           "SELECT count(*) FROM wallet WHERE wallet.user_id =? AND wallet.name = ?";
    String СHECK_WALLETS_COUNT_BY_USER_ID       =           "SELECT count(*) FROM wallet WHERE wallet.user_id =?";
    String SELECT_WALLET_BY_BILL =           "SELECT * FROM wallet WHERE wallet.bill_number = ? ";
    String SELECT_WALLET_BY_ID =           "SELECT * FROM wallet WHERE wallet.id = ? ";
    String CREATE_TRANSFER = "INSERT INTO TRANSFER         VALUES (DEFAULT, ?, ?, ?, ?, ?)";
    String UPDATE_WALLET_SUM =        "UPDATE wallet       SET wallet.balance = ? WHERE wallet.bill_number = ?";
    String BLOCK_WALLET =        "UPDATE wallet SET wallet.state_id = ? WHERE wallet.id = ?";
    String TOP_UP_WALLET = "UPDATE wallet SET wallet.balance = ? + wallet.balance WHERE wallet.id = ?";
    String SELECT_SORTED_USERS = "SELECT SQL_CALC_FOUND_ROWS * FROM user ORDER BY <sortParam> LIMIT <offsetParam>,<noOfRecordsParam>";
    String CREATE_NOTIFICATION = "INSERT INTO notification         VALUES (DEFAULT, ?, ?, ?, ?)";

    String CREATE_WALLET_REQUEST = "INSERT INTO wallet_request        VALUES (DEFAULT, ?, ?, ?)";
    String СHECK_WALLET_REQUEST_EXISTENCE     =           "SELECT count(*) FROM wallet_request WHERE wallet_request.wallet_id = ? AND wallet_request.status_id = ?";


    String SELECT_SORTED_WALLET_REQUESTS =           "SELECT SQL_CALC_FOUND_ROWS * FROM wallet_request WHERE wallet_request.status_id = ? ORDER BY <sortParam> LIMIT <offsetParam>,<noOfRecordsParam>";

    String UPDATE_UNSEEN_NOTIFICATIONS_BY_ID = "UPDATE notification SET notification.status_id = ? WHERE notification.id = ?";
    String SELECT_NOTIFICATIONS_BY_LIST = "SELECT * FROM notification WHERE notification.id IN (<idList>)";
}
