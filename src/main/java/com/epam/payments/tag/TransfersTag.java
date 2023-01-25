package com.epam.payments.tag;

import com.epam.payments.db.dto.TransferDTO;
import com.epam.payments.db.service.TransferService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class TransfersTag extends TagSupport {
    public static final Logger LOG = Logger.getLogger(TransfersTag.class);
    private TransferService transferService = new TransferService();

    @Override
    public int doStartTag() throws JspException {
        LOG.info("Start tracing TransfersTag");
        HttpSession session = pageContext.getSession();

        if (session.getAttribute("transferSort") == null) {
            session.setAttribute("transferSort", "transfer.id DESC");
        }
        String sortBy = String.valueOf(session.getAttribute("transferSort"));
        LOG.info("SORT BY -->" + sortBy);

        int currTransferPage = 1;
        int recordsPerPage = 2;
        if(session.getAttribute("currTransferPage") != null) currTransferPage = (Integer) session.getAttribute("currTransferPage");

        int c = 829815;

        List<TransferDTO> sortedTransfers = transferService.getTransferDAO()
                                                            .findTransfersBySenderBill(
//                                                                    (Long) session.getAttribute("user_id"),
                                                                    c,
                                                                    sortBy,
                                                                    (currTransferPage-1)*recordsPerPage,
                                                                    recordsPerPage
                                                            );
        int noOfRecords = transferService.getTransferDAO().getNoOfRecords();
        int noOfTransferPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        session.setAttribute("currTransferPage", currTransferPage);
        session.setAttribute("noOfTransferPages", noOfTransferPages);

//        LOG.info("USER ID -->" + session.getAttribute("user_id"));
//        ResourceBundle rb = ResourceBundle.getBundle("resources", new Locale(language));

        TransferDTO transferDTO;
        JspWriter out = pageContext.getOut();
        Iterator walletDTOIterator = sortedTransfers.iterator();

        StringBuffer table = new StringBuffer();

        table.append("<table class=\"table table-dark table-bordered table-hover text-center\">")
                .append("<thead>")
                    .append("<tr class=\"row ml-3\">")
                        .append("<th class=\"col-2\"><b>").append("Номер").append("</b></th>")
                        .append("<th class=\"col-3\"><b>").append("Номер гаманця відправника").append("</b></th>")
                        .append("<th class=\"col-3\"><b>").append("Номер гаманця отримувача").append("</b></th>")
                        .append("<th class=\"col-2\"><b>").append("Сума").append("</b></th>")
                        .append("<th class=\"col-2\"><b>").append("Дата").append("</b></th>")
                    .append("</tr>")
                .append("</thead>")
                .append("<tbody>")
            .append("<tr class=\"row ml-3\" >");

        while (walletDTOIterator.hasNext()) {
            transferDTO = (TransferDTO) walletDTOIterator.next();
            table
                    .append("<td class=\"col-2\">").append(transferDTO.getId()).append("</td>")
                    .append("<td class=\"col-3\">").append(transferDTO.getSender_bill_number()).append("</td>")
                    .append("<td class=\"col-3\">").append(transferDTO.getRecipient_bill_number()).append("</td>")
                    .append("<td class=\"col-2\">").append(transferDTO.getSum()).append("</td>")
                    .append("<td class=\"col-2\">").append(transferDTO.getDate().toString()).append("</td>");

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