package com.epam.payments.tag;

import com.epam.payments.db.dto.TransferDTO;
import com.epam.payments.db.service.TransferService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;

public class TransfersTag extends TagSupport {
    public static final Logger LOG = Logger.getLogger(TransfersTag.class);
    private TransferService transferService = new TransferService();

    private int value;

    public void setValue(int value){
        this.value= value;
    }

    @Override
    public int doStartTag() throws JspException {
        LOG.info("Start tracing TransfersTag");
        HttpSession session = pageContext.getSession();

        int currTransferPage = 1;
        int recordsPerPage = 2;
        int currWalletBill = value;

        if(session.getAttribute("currTransferPage") != null) currTransferPage = (Integer) session.getAttribute("currTransferPage");
//        if(session.getAttribute("currWalletBill") != null) currWalletBill = (Integer) session.getAttribute("currWalletBill");
        if (session.getAttribute("transferSort") == null) session.setAttribute("transferSort", "transfer.id DESC");

        String sortBy = String.valueOf(session.getAttribute("transferSort"));
        LOG.info("SORT BY -->" + sortBy);

        List<TransferDTO> sortedTransfers = transferService.getTransferDAO()
                                                            .findTransfersByBill(
                                                                    currWalletBill,
                                                                    sortBy,
                                                                    (currTransferPage-1)*recordsPerPage,
                                                                    recordsPerPage
                                                            );
        int noOfRecords = transferService.getTransferDAO().getNoOfRecords();
        int noOfTransferPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        session.setAttribute("currTransferPage", currTransferPage);
        session.setAttribute("noOfTransferPages", noOfTransferPages);
        session.setAttribute("walletTransfers", sortedTransfers);
//        ResourceBundle rb = ResourceBundle.getBundle("resources", new Locale(language));



        return EVAL_PAGE;
    }
}