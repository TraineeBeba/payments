package com.epam.payments.command;

import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.db.dto.WalletDTO;
import com.epam.payments.utils.Utils;
import com.epam.payments.db.service.WalletService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateWalletCommand extends Command {
    private static final Logger LOG = Logger.getLogger(CreateWalletCommand.class);
    private WalletService walletService = new WalletService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response, String forward) throws IOException, ServletException {
        LOG.trace("Start tracing CreateWalletCommand");

        HttpSession session = request.getSession();
        RedirectResult redirect = new RedirectResult(request.getParameter("redirect"));
        String name = "";
        Long user_id = -1L;

        if ((request.getParameter("name") != null) && (session.getAttribute("user_id") != null)) {
            name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
            user_id = Long.valueOf(String.valueOf(session.getAttribute("user_id")));
        }

        LOG.info("NAME -->" + name);
        LOG.info("USER_ID -->" + user_id);

        int bill_number;
        do {
            bill_number = Utils.generateBill();
        } while (!walletService.checkBillNumber(bill_number));

        String createWalletCheck = walletService.checkCreate(name);
        if(createWalletCheck == null) {
            walletService.getWalletDAO().createWallet(new WalletDTO(user_id, 1L, name, bill_number, 1000));
            session.setAttribute("walletCreationSuccess", true);
            redirect = new RedirectResult(request.getParameter("redirect"));
        } else {
            session.setAttribute("wrongData", createWalletCheck);
            redirect = new RedirectResult("?command=goWalletCreateCommand");
        }

        return redirect;
    }
}
