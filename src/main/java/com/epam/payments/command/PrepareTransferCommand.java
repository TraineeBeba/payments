package com.epam.payments.command;

import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.db.PaymentStatus;
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

import static com.epam.payments.db.PaymentStatus.PREPARED;


public class PrepareTransferCommand extends Command {

    private static final Logger LOG = Logger.getLogger(PrepareTransferCommand.class);
    private TransferService transferService = new TransferService();
    private WalletService walletService = new WalletService();
    private static final long serialVersionUID = -7190245479634943129L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response, String forward) throws IOException, ServletException {
        LOG.trace("Start tracing PrepareTransferCommand");

        HttpSession session = request.getSession();
        RedirectResult redirect;

        int sender_bill_number = Integer.parseInt(request.getParameter("sender_bill_number"));
        int recipient_bill_number = Integer.parseInt(request.getParameter("recipient_bill_number"));
        double sum = Double.parseDouble(request.getParameter("sum"));

        TransferDTO transferDTO = new TransferDTO(PREPARED.getId(), sender_bill_number, recipient_bill_number, sum);
        String transferCheck = transferService.transferCheck(transferDTO);
        if(transferCheck == null) {
            session.setAttribute("transferDTO", transferDTO);
            redirect = new RedirectResult(request.getParameter("redirect"));
        } else {
            session.setAttribute("wrongData", transferCheck);
            redirect = new RedirectResult("?command=goPrepare-TransferCommand");
        }

        return redirect;
    }

}