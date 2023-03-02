package com.epam.payments.command.constant;

public final class WebPathConstants {
    public static final String PATH_PREFIX = "/WEB-INF/jsp/";

    // Admin paths
    public static final String ADMIN_PATH_PREFIX = PATH_PREFIX + "admin/";
    public static final String ADMIN_USERS_PATH = ADMIN_PATH_PREFIX + "user/users.jsp";
    public static final String ADMIN_WALLET_REQUESTS_PATH = ADMIN_PATH_PREFIX + "request/wallet-requests.jsp";

    // Authentication paths
    public static final String AUTHENTICATION_PATH_PREFIX = PATH_PREFIX + "authentication/";
    public static final String LOGIN_PATH = AUTHENTICATION_PATH_PREFIX + "login.jsp";
    public static final String REGISTER_PATH = AUTHENTICATION_PATH_PREFIX + "register.jsp";

    // User wallet paths
    public static final String USER_PATH_PREFIX = PATH_PREFIX + "user/";
    public static final String USER_WALLETS_PATH = USER_PATH_PREFIX + "wallet/wallets.jsp";
    public static final String USER_WALLET_DETAILS_PATH = USER_PATH_PREFIX + "wallet/wallet-details.jsp";
    public static final String USER_CREATE_WALLET_PATH = USER_PATH_PREFIX + "wallet/wallet-create.jsp";
    public static final String USER_TOP_UP_BALANCE_PATH = USER_PATH_PREFIX + "wallet/wallet-top-up.jsp";

    // User transfer paths
    public static final String USER_TRANSFER_PATH_PREFIX = USER_PATH_PREFIX + "transfer/";
    public static final String USER_PREPARE_TRANSFER_PATH = USER_TRANSFER_PATH_PREFIX + "transfer-prepare.jsp";
    public static final String USER_SEND_TRANSFER_PATH = USER_TRANSFER_PATH_PREFIX + "transfer-send.jsp";

    // Common paths
    public static final String COMMON_PATH_PREFIX = PATH_PREFIX + "jspf/";
    public static final String ALERTS_PATH = COMMON_PATH_PREFIX + "alerts.jspf";
    public static final String HEADER_PATH = COMMON_PATH_PREFIX + "header.jspf";
    public static final String HEAD_PATH = COMMON_PATH_PREFIX + "head.jspf";

    public static String print (){
        return "HUI";
    }
}