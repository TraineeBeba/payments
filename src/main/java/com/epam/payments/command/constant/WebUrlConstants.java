package com.epam.payments.command.constant;

public interface WebUrlConstants {
    String ADMIN_USERS_URL = "?command=admin-users";
    String LOGIN_URL = "?command=login";
//    String REGISTER_URL = "?command=register";
    String REGISTER_URL = "?command=goRegisterCommand";
    String CREATE_WALLET_URL = "?command=create-wallet";
    String PREPARE_TRANSFER_URL = "?command=prepare-transfer";
    String SEND_TRANSFER_URL = "?command=send-transfer";
    String USER_WALLETS_URL = "?command=user-wallets";
    String WALLET_DETAILS_URL = "?command=wallet-details";
    String TOP_UP_BALANCE_URL = "?command=top-up-balance";
    String NOTIFICATIONS_URL = "?command=notifications";
    String WALLET_REQUESTS_URL = "?command=wallet-requests";
}