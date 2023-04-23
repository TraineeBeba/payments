package com.epam.payments.core.database.dao.mysql.query;

public interface UserQuery {
    String USER_TABLE = "user";
    String ID = "id";
    String ROLE_ID = "role_id";
    String STATE_ID = "state_id";
    String USERNAME = "username";
    String PASSWORD = "password";
    String EXISTS = "exists";

    String CREATE_USER                      = "INSERT INTO `" + USER_TABLE + "` (`" + ID + "`, `" + ROLE_ID + "`, `" + STATE_ID + "`, `" + USERNAME + "`, `" + PASSWORD + "`) VALUES (DEFAULT, ?, ?, ?, ?)";

    String SELECT_USER_BY_ID                = "SELECT * FROM `" + USER_TABLE + "` WHERE user.id = ? ";
    String SELECT_USER_STATE_BY_NAME        = "SELECT user.state_id FROM user WHERE user.username = ?";
    String SELECT_SORTED_USERS = "SELECT SQL_CALC_FOUND_ROWS * FROM user ORDER BY <sortParam> LIMIT <offsetParam>,<noOfRecordsParam>";
    String SELECT_USER_BY_NAME              = "SELECT * FROM `" + USER_TABLE + "` WHERE user.username = ? ";

    String UPDATE_USER                      = "UPDATE user SET user.role_id=?, user.state_id=?, user.username=?, user.password=? WHERE user.id=?";

    String EXISTS_USER_BY_NAME              = "SELECT EXISTS (SELECT * FROM `" + USER_TABLE + "` WHERE `" + USERNAME + "` = ?) AS `" + EXISTS + "`";
    String EXISTS_USER_BY_NAME_AND_PASSWORD = "SELECT EXISTS (SELECT * FROM user WHERE user.username = ? AND user.password = ?) AS `" + EXISTS + "`";
}
