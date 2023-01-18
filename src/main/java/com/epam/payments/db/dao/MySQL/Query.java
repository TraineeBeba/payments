package com.epam.payments.db.dao.MySQL;

public class Query {

    public static final String SELECT_ALL_USERS                   =           "SELECT * FROM USER";
    public static final String CREATE_USER                        =           "INSERT INTO USER         VALUES (DEFAULT, 1, 1, ?, ?)";
    public static final String SELECT_LAST_USER_ID                =           "SELECT MAX(ID) FROM USER";
    public static final String СHECK_USER_EXISTENCE_BY_NAME       =           "SELECT count(*) FROM user WHERE user.username = ?";
    public static final String СHECK_USER_EXISTENCE_BY_NAME_AND_PASSWORD  =   "SELECT count(*) FROM user WHERE user.username = ? AND user.password = ?";
    public static final String SELECT_USER_BY_NAME                =           "SELECT * FROM user WHERE user.username = ? ";
    public static final String СHECK_USER_STATUS_BY_NAME          =           "SELECT user.state_id FROM user WHERE user.username = ?";
    public static final String SELECT_ALL_WALLETS_BY_USER_ID      =           "SELECT * FROM wallet WHERE wallet.user_id = ?";
    public static final String СHECK_BILL_NUMBER_EXISTENCE        =           "SELECT count(*) FROM wallet WHERE wallet.bill_number = ?" ;
    public static final String CREATE_WALLET                      =           "INSERT INTO wallet VALUES (DEFAULT, ?, 1, ?, ?, ?)";
}
