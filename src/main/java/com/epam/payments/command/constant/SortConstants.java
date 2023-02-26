package com.epam.payments.command.constant;

public interface SortConstants {
    String DEFAULT_USER_SORT = "user.id DESC";
    String DEFAULT_WALLET_SORT = "wallet.bill_number ASC";
    String DEFAULT_TRANSFER_SORT = "transfer.id DESC";
    String DEFAULT_WALLET_REQUEST_SORT = "wallet_request.id DESC";
}