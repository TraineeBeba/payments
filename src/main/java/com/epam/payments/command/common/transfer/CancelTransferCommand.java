package com.epam.payments.command.common.transfer;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.payments.command.constant.ParamNames.CANCEL_TRANSFER;

public class CancelTransferCommand extends Command {
    private static final Logger LOG = Logger.getLogger(CancelTransferCommand.class);

    private static final long serialVersionUID = -7190245479634943129L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Start tracing CancelTransferCommand");

        HttpSession session = request.getSession();
        setAttributes(session);

//        return new RedirectResult(USER_WALLETS_URL);
        return new RedirectResult("USER_WALLETS_URL");
    }

    private void setAttributes(HttpSession session) {
        session.setAttribute(CANCEL_TRANSFER, true);
    }
}
