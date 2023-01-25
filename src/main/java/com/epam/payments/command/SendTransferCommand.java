package com.epam.payments.command;

import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.db.dto.TransferDTO;
import com.epam.payments.db.dto.WalletDTO;
import com.epam.payments.db.service.TransferService;
import com.epam.payments.db.service.WalletService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

public class SendTransferCommand extends Command {
    private static final Logger LOG = Logger.getLogger(PrepareTransferCommand.class);
    private WalletService walletService = new WalletService();
    private TransferService transferService = new TransferService();
    private static final long serialVersionUID = -7190245479634943129L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response, String forward) throws IOException, ServletException {
        LOG.trace("Start tracing SendTransferCommand");

        HttpSession session = request.getSession();
        CommandResult redirect = new RedirectResult(request.getParameter("redirect"));
        TransferDTO transferDTO = (TransferDTO) session.getAttribute("transferDTO");

        String transferCheck = transferService.transferCheck(transferDTO);
        if(transferCheck == null) {
            WalletDTO senderWallet = walletService.getWalletDAO().getWalletByBill(transferDTO.getSender_bill_number());
            WalletDTO recipientWallet = walletService.getWalletDAO().getWalletByBill(transferDTO.getRecipient_bill_number());

            transferDTO.setDate(new java.util.Date());
            walletService.getWalletDAO().doTransfer(transferDTO, senderWallet.getBalance(), recipientWallet.getBalance());
            transferService.getTransferDAO().createTransfer(transferDTO);

            session.removeAttribute("transferDTO");
            session.setAttribute("transferSuccess", true);
        } else {
            session.setAttribute("wrongData", transferCheck);
            redirect = new RedirectResult("?command=goSend-TransferCommand");
        }

        return redirect;
    }
}
