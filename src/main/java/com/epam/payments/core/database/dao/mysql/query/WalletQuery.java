package com.epam.payments.core.database.dao.mysql.query;

public interface WalletQuery {
    String WALLET_TABLE = "wallet";
    String ID = "id";
    String USER_ID = "user_id";
    String STATE_ID = "state_id";
    String NAME = "name";
    String BILL_NUMBER = "bill_number";
    String BALANCE = "balance";
    String EXISTS = "exists";
    String SORT_PARAM = "<sortParam>";

    String CREATE_WALLET = "INSERT INTO `" + WALLET_TABLE + "` VALUES (DEFAULT, ?, ?, ?, ?, ?)";

    String SELECT_SORTED_WALLETS_BY_USER_ID = "SELECT * FROM `" + WALLET_TABLE + "` WHERE `" + USER_ID + "` = ? ORDER BY " + SORT_PARAM;
    String SELECT_SORTED_WALLETS_BY_USER_ID_AND_STATE = "SELECT * FROM `" + WALLET_TABLE + "` WHERE `" + USER_ID + "` = ? AND `" + STATE_ID + "` = ? ORDER BY " + SORT_PARAM;
    String SELECT_WALLET_BY_BILL = "SELECT * FROM `" + WALLET_TABLE + "` WHERE `" + WALLET_TABLE + "`.`" + BILL_NUMBER + "` = ?";
    String SELECT_WALLET_BY_ID = "SELECT * FROM `" + WALLET_TABLE + "` WHERE `" + WALLET_TABLE + "`.`" + ID + "` = ?";

    String CHECK_BILL_NUMBER_EXISTENCE = "SELECT COUNT(*) FROM `" + WALLET_TABLE + "` WHERE `" + WALLET_TABLE + "`.`" + BILL_NUMBER + "` = ?";
    String CHECK_WALLET_EXISTENCE_BY_NAME_AND_USER_ID = "SELECT COUNT(*) FROM `" + WALLET_TABLE + "` WHERE `" + WALLET_TABLE + "`.`" + NAME + "` = ? AND `" + WALLET_TABLE + "`.user_id = ?";
    String CHECK_WALLETS_COUNT_BY_USER_ID = "SELECT COUNT(*) FROM `" + WALLET_TABLE + "` WHERE `" + WALLET_TABLE + "`.user_id = ?";

    String UPDATE_WALLET_BY_BILL = "UPDATE `" + WALLET_TABLE + "`" +
            " SET `" + USER_ID + "` = ?, `" + STATE_ID + "` = ?, `" + NAME + "` = ?, `" + BALANCE + "` = ?" +
            " WHERE `" + BILL_NUMBER + "` = ?";

    String UPDATE_WALLET_SUM = "UPDATE `" + WALLET_TABLE + "` SET `" + BALANCE + "` = ? WHERE `" + WALLET_TABLE + "`.`" + BILL_NUMBER + "` = ?";
    String TOP_UP_WALLET = "UPDATE `" + WALLET_TABLE + "` SET `" + BALANCE + "` = ? + `" + BALANCE + "` WHERE `" + WALLET_TABLE + "`.`" + ID + "` = ?";
    String BLOCK_WALLET = "UPDATE `" + WALLET_TABLE + "` SET `" + STATE_ID + "` = ? WHERE `" + WALLET_TABLE + "`.`" + ID + "` = ?";
}
