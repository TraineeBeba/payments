package com.epam.payments.command.constant;

import static com.epam.payments.command.constant.CommandNames.*;
import static com.epam.payments.command.constant.ParamNames.COMMAND;


public final class WebUrlConstants {
//    public static final String CONTROLLER_URL = "/controller";
    public static final String CONTROLLER_URL = "/controller";
    public static final String COMMAND_PARAM_PREFIX = "?" + COMMAND + "=";

    public static final String LANGUAGE_URL = CONTROLLER_URL + COMMAND_PARAM_PREFIX + LANGUAGE;
    public static final String LOGIN_URL = CONTROLLER_URL + COMMAND_PARAM_PREFIX + LOGIN;
    public static final String REGISTER_URL = CONTROLLER_URL + COMMAND_PARAM_PREFIX + REGISTER;

    public static final String GO_LOGIN_PAGE_URL = "/payments" + CONTROLLER_URL + COMMAND_PARAM_PREFIX + GO_LOGIN_PAGE;
    public static final String GO_REGISTER_PAGE_URL = "/payments" + CONTROLLER_URL + COMMAND_PARAM_PREFIX + GO_REGISTER_PAGE;

//    String ADMIN_USERS_URL = COMMAND_PARAM_PREFIX + "admin-users";
//    String REGISTER_URL = "?command=goRegisterCommand";
//    String CREATE_WALLET_URL = "?command=create-wallet";
//    String PREPARE_TRANSFER_URL = "?command=prepare-transfer";
//    String SEND_TRANSFER_URL = "?command=send-transfer";
//    String USER_WALLETS_URL = "?command=user-wallets";
//    String WALLET_DETAILS_URL = "?command=wallet-details";
//    String TOP_UP_BALANCE_URL = "?command=top-up-balance";
//    String NOTIFICATIONS_URL = "?command=notifications";
//    String WALLET_REQUESTS_URL = "?command=wallet-requests";
}

