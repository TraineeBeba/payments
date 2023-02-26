package com.epam.payments.command.constant;

public interface ParameterNames {
    String APP_CONTEXT = "appContext";

    String USERNAME = "username";
    String PASSWORD = "password";
    String LOGOUT = "logout";

    String USER_ENTITY = "userEntity";
    String TRANSFER_ENTITY = "transferEntity";
    String WALLET_ENTITY = "walletEntity";
    String WALLET_REQUEST_ENTITY = "walletRequestEntity";

    String SELECTED_USER_NAME = "selectedUserName";
    String SELECTED_USER = "selectedUser";

    String CURR_WALLET = "currWallet";
    String CURR_USER_ID = "currUserId";

    String USERS = "users";
    String WALLETS = "wallets";
    String WALLET_REQUESTS = "walletRequests";
    String WALLET_TRANSFERS = "walletTransfers";

    String USER_SORT = "userSort";
    String WALLET_REQUEST_SORT = "walletRequestSort";
    String WALLET_SORT = "walletSort";
    String TRANSFER_SORT = "transferSort";

    String BILL_NUMBER = "bill_number";
    String RECIPIENT_BILL_NUMBER = "recipient_bill_number";
    String SENDER_BILL_NUMBER = "sender_bill_number";
    String SUM = "sum";
    String REQUEST_STATUS_ID = "requestStatusId";
    String REQUEST_EXIST = "requestExist";
    String REQUEST_TYPE = "requestType";
    String WALLET_REQUEST_ID = "walletRequestId";
    String WALLET_ID = "walletId";
    String WALLET_NAME = "walletName";
    String CANCEL_TRANSFER = "cancelTransfer";

    String PAGE = "page";
    String NO_OF_TRANSFER_PAGES = "noOfTransferPages";

    String WRONG_DATA = "wrongData";
    String TRANSFER_SUCCESS = "transferSuccess";
    String REGISTER_SUCCESS = "registerSuccess";
    String REQUEST_CREATION_SUCCESS = "requestCreationSuccess";
    String WALLET_CREATION_SUCCESS = "walletCreationSuccess";
    String BLOCK_WALLET_SUCCESS = "blockWalletSuccess";
    String UNBLOCK_WALLET_SUCCESS = "unblockWalletSuccess";
    String TOP_UP_SUCCESS = "topUpSuccess";
}
