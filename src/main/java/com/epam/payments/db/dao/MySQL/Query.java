package com.epam.payments.db.dao.MySQL;

public class Query {

    public static final String SELECT_ALL_USERS                   =           "SELECT * FROM user";
    public static final String CREATE_USER                        =           "INSERT INTO user         VALUES (DEFAULT, 1, 1, ?, ?)";
    public static final String SELECT_LAST_USER_ID                =           "SELECT MAX(ID) FROM user";
    public static final String СHECK_USER_EXISTENCE_BY_NAME       =           "SELECT count(*) FROM user WHERE user.username = ?";
    public static final String СHECK_USER_EXISTENCE_BY_NAME_AND_PASSWORD  =   "SELECT count(*) FROM user WHERE user.username = ? AND user.password = ?";
    public static final String SELECT_USER_BY_NAME                =           "SELECT * FROM user WHERE user.username = ? ";
    public static final String СHECK_USER_STATUS_BY_NAME          =           "SELECT user.status_id FROM user WHERE user.username = ?";
    public static final String SELECT_SORTED_WALLETS_BY_USER_ID =           "SELECT * FROM wallet WHERE wallet.user_id = ? ORDER BY <sortParam>";
    public static final String SELECT_SORTED_TRANSFERS_BY_USER_ID =           "SELECT SQL_CALC_FOUND_ROWS * FROM transfer WHERE transfer.sender_bill_number = ? OR transfer.recipient_bill_number = ? ORDER BY <sortParam> LIMIT <offsetParam>,<noOfRecordsParam>";
    public static final String СHECK_BILL_NUMBER_EXISTENCE        =           "SELECT count(*) FROM wallet WHERE wallet.bill_number = ?" ;
    public static final String CREATE_WALLET                      =           "INSERT INTO wallet VALUES (DEFAULT, ?, 1, ?, ?, ?)";
    public static final String СHECK_WALLET_EXISTENCE_BY_NAME_AND_USER_ID     =           "SELECT count(*) FROM wallet WHERE wallet.user_id =? AND wallet.name = ?";
    public static final String СHECK_WALLETS_COUNT_BY_USER_ID       =           "SELECT count(*) FROM wallet WHERE wallet.user_id =?";
    public static final String SELECT_WALLET_BY_BILL =           "SELECT * FROM wallet WHERE wallet.bill_number = ? ";
    public static final String CREATE_TRANSFER = "INSERT INTO TRANSFER         VALUES (DEFAULT, ?, ?, ?, ?)";
    public static final String UPDATE_WALLET =        "UPDATE wallet       SET wallet.balance = ? WHERE wallet.bill_number = ?";
}
