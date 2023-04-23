package com.epam.payments.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

import static com.epam.payments.command.i18n.constant.Locale.EN;

public final class RegisterPage /*implements Initializable*/{
    public static final String BUNDLE_NAME = "register";
    private static ResourceBundle register;

    static {
        register = ResourceBundle.getBundle(BUNDLE_NAME, new Locale(EN));
    }

//    public static final String TITLE = register.getString("TITLE");
//    public static final String ENTER_LABEL = register.getString("ENTER_LABEL");
//    public static final String USERNAME_PLACEHOLDER = register.getString("USERNAME_PLACEHOLDER");
//    public static final String PASSWORD_PLACEHOLDER = register.getString("PASSWORD_PLACEHOLDER");
//    public static final String REGISTER_BUTTON = register.getString("REGISTER_BUTTON");
//    public static final String GO_LOGIN_BUTTON = register.getString("GO_LOGIN_BUTTON");
}