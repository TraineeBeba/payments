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
//        ResourceBundle rb = ResourceBundle.getBundle("resources", new Locale(language));

        WalletDTO walletDTO;
        JspWriter out = pageContext.getOut();
        Iterator walletDTOIterator = sortedWallets.iterator();

        StringBuffer table = new StringBuffer();

        table.append("<table class=\"table table-dark table-bordered table-hover text-center\">")
                .append("<thead>")
                    .append("<tr class=\"row ml-3\">")
                        .append("<th class=\"col-2\"><b>").append("Назва").append("</b></th>")
                        .append("<th class=\"col-3\"><b>").append("Номер рахунку").append("</b></th>")
                        .append("<th class=\"col-3\"><b>").append("Баланс").append("</b></th>")
                        .append("<th class=\"col-2\"><b>").append("Статус").append("</b></th>")
                        .append("<th class=\"col-2\"><b>").append("Детальніше").append("</b></th>")
                    .append("</tr>")
                .append("</thead>")
                .append("<tbody>")
            .append("<tr class=\"row ml-3\" >");

        while (walletDTOIterator.hasNext()) {
            walletDTO = (WalletDTO) walletDTOIterator.next();
            table
                    .append("<td class=\"col-2\">").append(walletDTO.getName()).append("</td>")
                    .append("<td class=\"col-3\">").append(walletDTO.getBill_number()).append("</td>")
                    .append("<td class=\"col-3\">").append(walletDTO.getBalance()).append("</td>")
                    .append("<td class=\"col-2\">").append(walletDTO.getState()).append("</td>")
                    .append("<td class=\"pupa href-container col-2\">")
                    .append("<a class=\"nav-link\" href=\"/controller?command=goDetails-WalletCommand\">Детальніше</a>")
                    .append("</td>");
				}
        table   .append("</tr>")
                .append("</tbody>")
                .append("</table>");

        try {
            out.println(table);
        } catch (IOException e) {
            LOG.error(e.getLocalizedMessage());
        }

        return EVAL_PAGE;
    }
}