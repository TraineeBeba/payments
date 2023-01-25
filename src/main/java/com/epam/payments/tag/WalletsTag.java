package com.epam.payments.tag;

import com.epam.payments.db.dto.WalletDTO;
import com.epam.payments.db.service.WalletService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class WalletsTag extends TagSupport {
    public static final Logger LOG = Logger.getLogger(WalletsTag.class);
    private WalletService walletService  = new WalletService();

    @Override
    public int doStartTag() throws JspException {
        LOG.info("Start tracing WalletsTag");
        HttpSession session = pageContext.getSession();

        if (session.getAttribute("walletSort") == null) session.setAttribute("walletSort","wallet.balance");
        String sortBy = String.valueOf(session.getAttribute("walletSort"));

        List<WalletDTO> sortedWallets = walletService.getWalletDAO().findWalletsByUserId((Long) session.getAttribute("user_id"), sortBy);

        LOG.info("SORT BY -->" + sortBy);
        LOG.info("USER ID -->" + session.getAttribute("user_id"));

        session.setAttribute("wallets", sortedWallets);
//        ResourceBundle rb = ResourceBundle.getBundle("resources", new Locale(language));

        return EVAL_PAGE;
    }
}