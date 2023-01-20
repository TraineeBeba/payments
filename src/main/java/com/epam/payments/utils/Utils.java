package com.epam.payments.utils;

import com.epam.payments.db.dto.WalletDTO;
import com.epam.payments.db.service.WalletService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;


/**
 * Utils methods for commands
 */

public class Utils {
    public static final Logger LOG = Logger.getLogger(Utils.class);

    private static WalletService walletService  = new WalletService();

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

    public static int generateBill() {
        Random rnd = new Random();
        return  100000 + rnd.nextInt(900000);
    }

    public static Properties getNameProperties() {
        Properties names = new Properties();
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("/commands.properties");
        try {
            names.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return names;
    }

    public static void loadInfo(HttpServletRequest request, String forward) {
        if(forward.equals("main")){
            HttpSession session = request.getSession();
            List<WalletDTO> wallets = walletService.getWalletDAO().
                                                    findWalletsByUserId((Long) session.getAttribute("user_id"));
            session.setAttribute("wallets", wallets);
        }
    }
}