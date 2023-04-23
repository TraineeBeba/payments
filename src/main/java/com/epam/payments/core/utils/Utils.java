package com.epam.payments.core.utils;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;


public class Utils {
    public static final Logger LOG = Logger.getLogger(Utils.class);

    public static String encrypt(String pass) {
        StringBuilder hex = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(pass.getBytes(StandardCharsets.UTF_8));
            byte[] hash = digest.digest();
            for (int i = 0; i < hash.length; i++) {
                hex.append(String.format("%02x", hash[i]));
            }
        } catch (NoSuchAlgorithmException e) {
            LOG.error(e.getLocalizedMessage());
        }
        return hex.toString();
    }

    public static int generateBill() {
        return  100000 + new Random().nextInt(900000);
    }

    public static ResourceBundle getLocale(HttpSession session) {
        String language = String.valueOf(session.getAttribute("language"));
        Locale locale = new Locale(language);
        return ResourceBundle.getBundle("resources", locale);
    }
}