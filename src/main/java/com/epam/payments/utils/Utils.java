package com.epam.payments.utils;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Utils methods for commands
 */

public class Utils {

    public static final Logger LOG = Logger.getLogger(Utils.class);

    /**
     * Hash the input string in the md5
     * @param pass
     * @return
     */
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

}