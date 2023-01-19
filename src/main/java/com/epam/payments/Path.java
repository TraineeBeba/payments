package com.epam.payments;

/**
 * Path holder (jsp pages, controller commands).
 * @author Sergey Tatarinov
 */
public final class Path {

    public static final String PAGE_LOGIN            = "/WEB-INF/jsp/login.jsp";
    public static final String PAGE_REGISTER         = "/WEB-INF/jsp/register.jsp";
    public static final String PAGE_MAIN             = "/WEB-INF/jsp/main.jsp";


    public static final String PAGE_ERROR            = "/WEB-INF/jsp/error.jsp";
    public static final String PAGE_ADMIN            = "/WEB-INF/jsp/admin.jsp";


    // commands
    public static final String PAGE_HOME             = "/controller?command=loginCommand";

    public static final String PAGE_CREATE_WALLET    = "/WEB-INF/jsp/create-wallet.jsp";
}