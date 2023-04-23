package com.epam.payments.command.common.user.transfer;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.payments.command.constant.ParamNames.CANCEL_TRANSFER;
import static com.epam.payments.command.constant.WebUrlConstants.GO_USER_WALLETS_PAGE_URL;

public class CancelTransferCommand extends Command {
    private static final Logger LOG = Logger.getLogger(CancelTransferCommand.class);

    private static final long serialVersionUID = -7190245479634943129L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Start tracing CancelTransferCommand");

        HttpSession session = request.getSession();
        session.setAttribute(CANCEL_TRANSFER, true);

        return new RedirectResult(GO_USER_WALLETS_PAGE_URL);
    }
}
