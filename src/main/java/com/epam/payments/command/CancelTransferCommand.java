package com.epam.payments.command;

import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.db.dto.TransferDTO;
import com.epam.payments.db.service.TransferService;
import com.epam.payments.db.service.WalletService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

public class CancelTransferCommand extends Command{
    private static final Logger LOG = Logger.getLogger(CancelTransferCommand.class);

    private static final long serialVersionUID = -7190245479634943129L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response, String forward) throws IOException, ServletException {
        LOG.trace("Start tracing CancelTransferCommand");

        HttpSession session = request.getSession();
        session.removeAttribute("transferDTO");
        session.setAttribute("cancelTransfer", true);

        return new RedirectResult(request.getParameter("redirect"));
    }
}
