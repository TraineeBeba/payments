package com.epam.payments.command.constant;

import static com.epam.payments.command.constant.CommandNames.*;
import static com.epam.payments.command.constant.ParamNames.COMMAND;


public final class WebUrlConstants {
    public static final String APP_URL = "/payments";
    public static final String CONTROLLER_URL = "/controller";
    public static final String COMMON_URL_PREFIX = APP_URL + CONTROLLER_URL;
    public static final String COMMAND_PARAM_PREFIX = "?" + COMMAND + "=";

    public static final String LOGOUT_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + LOGOUT;

    public static final String GO_LOGIN_PAGE_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + GO_LOGIN_PAGE;
    public static final String GO_REGISTER_PAGE_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + GO_REGISTER_PAGE;
    public static final String GO_USER_WALLETS_PAGE_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + GO_USER_WALLETS_PAGE;
    public static final String GO_WALLETS_DETAILS_PAGE_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + GO_WALLET_DETAILS_PAGE;
    public static final String GO_CREATE_WALLET_PAGE_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + GO_CREATE_WALLET_PAGE;

    public static final String GO_PREPARE_TRANSFER_PAGE_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + GO_PREPARE_TRANSFER_PAGE;
    public static final String GO_SEND_TRANSFER_PAGE_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + GO_SEND_TRANSFER_PAGE;
    public static final String GO_TOP_UP_BALANCE_PAGE_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + GO_TOP_UP_BALANCE_PAGE;
    public static final String GO_ADMIN_USERS_PAGE_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + GO_ADMIN_USERS_PAGE;

    public static final String GO_ADMIN_USER_WALLETS_PAGE_URL = COMMON_URL_PREFIX + COMMAND_PARAM_PREFIX + GO_ADMIN_USER_WALLETS_PAGE;

}

