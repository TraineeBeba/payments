package com.epam.payments.command.constant;

import com.epam.payments.core.database.dao.mysql.query.UserQuery;
import com.epam.payments.core.database.dao.mysql.query.WalletQuery;

public final class SortConstants {

    public static final String WALLET_SORT_BY_BILL = "`" + WalletQuery.BILL_NUMBER + "`" + "ASC";
    public static final String WALLET_SORT_BY_NAME_ASC = "`" + WalletQuery.NAME + "`" + "ASC";
    public static final String WALLET_SORT_BY_BALANCE_ASC = "`" + WalletQuery.BALANCE + "`" + "ASC";
    public static final String DEFAULT_WALLET_SORT = WALLET_SORT_BY_BILL;

    public static final String DEFAULT_USER_SORT = "`" + UserQuery.ID + "`" + " DESC";
    public static final String DEFAULT_TRANSFER_SORT = "transfer.id DESC";
    public static final String DEFAULT_WALLET_REQUEST_SORT = "wallet_request.id DESC";
}