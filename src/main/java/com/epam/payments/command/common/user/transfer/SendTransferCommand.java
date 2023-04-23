package com.epam.payments.command.common.user.transfer;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.core.model.dto.TransferDTO;
import com.epam.payments.core.service.transfer.TransferService;
import com.epam.payments.core.service.transfer.exception.PrepareTransferException;
import com.epam.payments.core.service.transfer.exception.SendTransferException;
import com.epam.payments.core.service.wallet.WalletService;
import com.epam.payments.core.utils.ServletUtils;
import com.epam.payments.core.utils.exeption.AttributeNotFoundException;
import com.epam.payments.exeption.InternalServerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.payments.command.constant.ParamNames.*;
import static com.epam.payments.command.constant.WebUrlConstants.*;

public class SendTransferCommand extends Command {
    private static final Logger LOG = Logger.getLogger(SendTransferCommand.class);

    private static final long serialVersionUID = -7190245479634943129L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException {
        LOG.trace("Start tracing SendTransferCommand");

        HttpSession session = request.getSession();
        TransferService transferService = ServletUtils.getService(request, TransferService.class);
        WalletService walletService = ServletUtils.getService(request, WalletService.class);
        TransferDTO transferDTO = ServletUtils.getAttribute(session, TRANSFER_DTO, TransferDTO.class);

        CommandResult redirect;
        try {
            transferService.sendTransfer(walletService, transferDTO);
            session.setAttribute(TRANSFER_SUCCESS, true);

            redirect = new RedirectResult(GO_SEND_TRANSFER_PAGE_URL);
        } catch (SendTransferException e) {
            session.setAttribute(WRONG_DATA, e.getMessage());
            redirect = new RedirectResult(GO_USER_WALLETS_PAGE_URL);
        }

        return redirect;
    }
}





