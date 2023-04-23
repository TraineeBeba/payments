package com.epam.payments.command.constant;

import static com.epam.payments.command.constant.CommandNames.*;
import static com.epam.payments.command.constant.ParamNames.COMMAND;


public final class WebUrlConstants {
    public static final String APP_URL = "/payments";
    public static final String CONTROLLER_URL = "/controller";
    public static final String COMMON_URL_PREFIX = APP_URL + CONTROLLER_URL;
    public static final String COMMAND_PARAM_PREFIX = "?" + COMMAND + "=";

    public static final String LANGUAGE_URL =  COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + LANGUAGE;
    public static final String LOGIN_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + LOGIN;
    public static final String REGISTER_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + REGISTER;
    public static final String LOGOUT_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + LOGOUT;

    public static final String GO_LOGIN_PAGE_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + GO_LOGIN_PAGE;
    public static final String GO_REGISTER_PAGE_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + GO_REGISTER_PAGE;
    public static final String GO_USER_WALLETS_PAGE_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + GO_USER_WALLETS_PAGE;
    public static final String GO_CREATE_WALLET_PAGE_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + GO_CREATE_WALLET_PAGE;

    public static final String GO_PREPARE_TRANSFER_PAGE_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + GO_PREPARE_TRANSFER_PAGE;
    public static final String GO_SEND_TRANSFER_PAGE_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + GO_SEND_TRANSFER_PAGE;
    public static final String GO_TOP_UP_BALANCE_PAGE_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + GO_TOP_UP_BALANCE_PAGE;
    public static final String GO_ADMIN_USERS_PAGE_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + GO_ADMIN_USERS_PAGE;
    public static final String GO_WALLET_REQUESTS_PAGE_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + GO_WALLET_REQUESTS_PAGE;


//    String ADMIN_USERS_URL = COMMAND_PARAM_PREFIX + "admin-users";
//    String REGISTER_URL = "?command=goRegisterCommand";
//    String CREATE_WALLET_URL = "?command=create-wallet";

//    String SEND_TRANSFER_URL = "?command=send-transfer";
//    String USER_WALLETS_URL = "?command=user-wallets";
//    String WALLET_DETAILS_URL = "?command=wallet-details";
//    String TOP_UP_BALANCE_URL = "?command=top-up-balance";
//    String NOTIFICATIONS_URL = "?command=notifications";
//    String WALLET_REQUESTS_URL = "?command=wallet-requests";
}

