package com.epam.payments.command.constant;

public interface WebPathConstants {
    String PATH_PREFIX = "WEB-INF/jsp/";
    String ADMIN_USERS_PATH = PATH_PREFIX + "admin-users.jsp";
    String LOGIN_PATH = PATH_PREFIX + "login.jsp";
    String REGISTER_PATH = PATH_PREFIX + "register.jsp";
    String CREATE_WALLET_PATH = PATH_PREFIX + "create-wallet.jsp";
    String PREPARE_TRANSFER_PATH = PATH_PREFIX + "prepare-transfer.jsp";
    String SEND_TRANSFER_PATH = PATH_PREFIX + "send-transfer.jsp";
    String USER_WALLETS_PATH = PATH_PREFIX + "user-wallets.jsp";
    String WALLET_DETAILS_PATH = PATH_PREFIX + "wallet-details.jsp";
    String TOP_UP_BALANCE_PATH = PATH_PREFIX + "top-up-balance.jsp";
    String NOTIFICATIONS_PATH = PATH_PREFIX + "notifications.jsp";
    String WALLET_REQUESTS_PATH = PATH_PREFIX + "wallet-requests.jsp";
}