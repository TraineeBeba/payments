package com.epam.payments.web.command.go;

import com.epam.payments.Path;
import com.epam.payments.db.dto.WalletDTO;
import com.epam.payments.web.command.Command;
import com.epam.payments.web.command.result.CommandResult;
import com.epam.payments.web.command.result.ForwardResult;
import com.epam.payments.web.service.WalletService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoToMainCommand extends Command {
    public static final Logger LOG = Logger.getLogger(GoToMainCommand.class);
    private WalletService walletService = new WalletService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing GoToMainCommand");

        HttpSession session = request.getSession();
        Long user_id = (Long) session.getAttribute("user_id");

        List<WalletDTO> wallets = walletService.getWalletDAO().findWalletsByUserId(user_id);

        request.setAttribute("wallets", wallets);
        return new ForwardResult(Path.PAGE_MAIN);
    }
}
