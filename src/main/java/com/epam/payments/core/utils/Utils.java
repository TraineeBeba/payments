package com.epam.payments.core.utils;

import com.epam.payments.core.service.impl.WalletServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;


public class Utils {
    public static final Logger LOG = Logger.getLogger(Utils.class);
    public static Properties names = new Properties();

    static {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("/namespace.properties");
        try {
            names.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String encrypt(String pass) {
        StringBuilder hex = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(pass.getBytes("UTF-8"));
            byte[] hash = digest.digest();
            for (int i = 0; i < hash.length; i++) {
                hex.append(String.format("%02x", hash[i]));
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            LOG.error(e.getLocalizedMessage());
        }
        return hex.toString();
    }

//    public static int generateBill() {
//
//
//        int bill_number;
//        Random rnd = new Random();
//        do {
//            bill_number = 100000 + rnd.nextInt(900000);
//        } while (!walletServiceImpl.existsByBill(bill_number));
//
//        return  bill_number;
//    }

    public static Properties getNamespaceProperties() {
        return names;
    }

    public static ResourceBundle getLocale(HttpSession session) {
        String language = String.valueOf(session.getAttribute("language"));
        Locale locale = new Locale(language);
        return ResourceBundle.getBundle("resources", locale);
    }
}