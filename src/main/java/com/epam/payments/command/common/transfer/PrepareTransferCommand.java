package com.epam.payments.command.common.transfer;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.core.model.entity.TransferEntity;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

import static com.epam.payments.core.model.enums.status.TransferStatus.PREPARED;

public class PrepareTransferCommand extends Command {
    private static final Logger LOG = Logger.getLogger(PrepareTransferCommand.class);

    private static final long serialVersionUID = -7190245479634943129L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Start tracing PrepareTransferCommand");

        HttpSession session = request.getSession();
        RedirectResult redirect = null;

//        int senderBillNumber = getSenderBillNumberParameter(request);
//        int recipientBillNumber = getRecipientBillNumberParameter(request);
//        BigDecimal sum = getSumParameter(request);
//
//        TransferEntity transferEntity = new TransferEntity(PREPARED, senderBillNumber, recipientBillNumber, sum);
//        String isValid = transferServiceImpl.isValidTransfer(transferEntity);
//
//        if (isValid == null) {
//            session.setAttribute(TRANSFER_ENTITY, transferEntity);
//        } else {
//            session.setAttribute(WRONG_DATA, isValid);
//        }
//        redirect = new RedirectResult(SEND_TRANSFER_URL);

        return redirect;
    }

    private int getSenderBillNumberParameter(HttpServletRequest request) {
        int senderBillNumberParam = 0;
        if (request.getParameter(SENDER_BILL_NUMBER) != null) {
            senderBillNumberParam = Integer.parseInt(request.getParameter(SENDER_BILL_NUMBER));
        } else {
            LOG.info("error");
        }

        return senderBillNumberParam;
    }

    private int getRecipientBillNumberParameter(HttpServletRequest request) {
        int recipientBillNumberParam = 0;
        if (request.getParameter(RECIPIENT_BILL_NUMBER) != null) {
            recipientBillNumberParam = Integer.parseInt(request.getParameter(RECIPIENT_BILL_NUMBER));
        } else {
            LOG.info("error");
        }

        return recipientBillNumberParam;
    }

    private BigDecimal getSumParameter(HttpServletRequest request) {
        BigDecimal sumParam = new BigDecimal(0);
        if (request.getParameter(SUM) != null) {
            sumParam = new BigDecimal(request.getParameter(SUM));
        } else {
            LOG.info("error");
        }

        return sumParam;
    }
}
