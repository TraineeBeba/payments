package com.epam.payments.db.dao.MySQL;

public class Query {

    public static final String SELECT_ALL_USERS =           "SELECT * FROM USER";
    public static final String CREATE_USER =                "INSERT INTO USER         VALUES (DEFAULT, 1, 1, ?, ?)";
    public static final String SELECT_LAST_USER_ID =                                    "SELECT MAX(ID) FROM USER";
    public static final String СHECK_USER_EXISTENCE_BY_NAME =                                    "SELECT count(*) FROM user WHERE user.username = ?";
}
