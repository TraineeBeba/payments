package com.epam.payments.command.common.user.transfer;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.core.model.dto.TransferDTO;
import com.epam.payments.core.service.transfer.TransferService;
import com.epam.payments.core.service.transfer.exception.PrepareTransferException;
import com.epam.payments.core.service.wallet.WalletService;
import com.epam.payments.core.utils.ServletUtils;
import com.epam.payments.exeption.InternalServerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

import static com.epam.payments.command.constant.ParamNames.*;
import static com.epam.payments.command.constant.WebUrlConstants.*;

public class PrepareTransferCommand extends Command {
    private static final Logger LOG = Logger.getLogger(PrepareTransferCommand.class);

    private static final long serialVersionUID = -7190245479634943129L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException {
        LOG.trace("Start tracing PrepareTransferCommand");

        HttpSession session = request.getSession();
        RedirectResult redirect;

        TransferService transferService = ServletUtils.getService(request, TransferService.class);
        WalletService walletService = ServletUtils.getService(request, WalletService.class);
        int senderBillNumber = ServletUtils.getIntParameter(request, SENDER_BILL_NUMBER);
        int recipientBillNumber = ServletUtils.getIntParameter(request, RECIPIENT_BILL_NUMBER);
        BigDecimal sum = new BigDecimal(ServletUtils.getStringParameter(request, SUM));

        try {
            TransferDTO transferDTO = transferService.prepareTransfer(walletService, senderBillNumber, recipientBillNumber, sum);
            session.setAttribute(TRANSFER_DTO, transferDTO);
        } catch (PrepareTransferException e) {
            session.setAttribute(WRONG_DATA, e.getMessage());
            return new RedirectResult(GO_PREPARE_TRANSFER_PAGE_URL);
        }
        redirect = new RedirectResult(GO_SEND_TRANSFER_PAGE_URL);

        return redirect;
    }
}
