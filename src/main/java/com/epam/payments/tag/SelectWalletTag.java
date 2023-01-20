package com.epam.payments.tag;

import com.epam.payments.db.dto.WalletDTO;
import com.epam.payments.db.service.WalletService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class SelectWalletTag extends TagSupport {
    public static final Logger LOG = Logger.getLogger(SelectWalletTag.class);
    private WalletService walletService  = new WalletService();

    @Override
    public int doStartTag() throws JspException {
        boolean hasLine = false;
        LOG.info("Start tracing SelectWalletTag");
        HttpSession session = pageContext.getSession();
        String language = String.valueOf(session.getAttribute("language"));
        ResourceBundle rb = ResourceBundle.getBundle("resources", new Locale(language));
        List<WalletDTO> wallets = walletService.getWalletDAO().
                findWalletsByUserId((Long) session.getAttribute("user_id"));
        session.setAttribute("wallets", wallets);

        WalletDTO walletDTO
                ;
        JspWriter out = pageContext.getOut();
        Iterator walletDTOIterator = wallets.iterator();
        StringBuffer select = new StringBuffer();

        select.append("<select name=\"sender_bill_number\" class=\"form-control select\">");
        while (walletDTOIterator.hasNext()) {
            walletDTO = (WalletDTO) walletDTOIterator.next();
            select.append("<option value=\"").append(walletDTO.getBill_number()).append("\">")
                    .append(walletDTO.getBill_number())
                    .append(" ")
                    .append(walletDTO.getBalance())
                    .append(" грн")
                    .append(" </option>");
        }
        select.append("</select>").append("<br>");
        try {
            out.println(select);
        } catch (IOException e) {
            LOG.error(e.getLocalizedMessage());
        }

        return EVAL_PAGE;
    }
}