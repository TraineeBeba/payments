package com.epam.payments.command.common.transfer;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.core.model.entity.TransferEntity;
import com.epam.payments.core.model.entity.WalletEntity;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SendTransferCommand extends Command {
    private static final Logger LOG = Logger.getLogger(SendTransferCommand.class);

    private static final long serialVersionUID = -7190245479634943129L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        LOG.trace("Start tracing SendTransferCommand");

        HttpSession session = request.getSession();
        CommandResult redirect = new RedirectResult(USER_WALLETS_URL);
//        TransferEntity transferEntity = (TransferEntity) session.getAttribute(TRANSFER_ENTITY);
//
//        if(transferEntity != null) {
//            String isValid = transferService.isValidTransfer(transferEntity);
//            if(isValid == null) {
//                WalletEntity senderWallet = walletService.findByBill(transferEntity.getSender_bill_number());
//                WalletEntity recipientWallet = walletService.findByBill(transferEntity.getRecipient_bill_number());
//
////                transferEntity.setDate(new java.util.Date());
////                walletServiceImpl.getMySQLWalletDAO().doTransfer(transferDTO, senderWallet.getBalance(), recipientWallet.getBalance());
////                TODO
//                transferService.save(transferEntity);
//
//                session.setAttribute(TRANSFER_SUCCESS, true);
//            } else {
//                session.setAttribute(WRONG_DATA, isValid);
//                redirect = new RedirectResult(USER_WALLETS_URL);
//            }
//        }

        return redirect;
    }
}





