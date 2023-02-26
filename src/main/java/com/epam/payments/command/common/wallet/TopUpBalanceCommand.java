package com.epam.payments.command.common.wallet;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.core.model.entity.WalletEntity;
import com.epam.payments.core.service.WalletService;
import com.epam.payments.core.service.impl.WalletServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class TopUpBalanceCommand extends Command {
    private static final Logger LOG = Logger.getLogger(TopUpBalanceCommand.class);


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Start tracing CreateWalletCommand");

        HttpSession session = request.getSession();
        RedirectResult redirect= null;

//        int bill_number = Integer.parseInt(request.getParameter(BILL_NUMBER));
//        BigDecimal sum = new BigDecimal(request.getParameter(SUM));
//        WalletEntity walletEntity = walletService.findByBill(bill_number);
//
//        String topUpCheck = walletService.isValidTopUp(walletEntity, sum);
//        if(topUpCheck == null) {
//            BigDecimal new_balance = walletEntity.getBalance().add(sum);
//            walletEntity.setBalance(new_balance);
//
//            walletService.update(walletEntity);
//            session.setAttribute(TOP_UP_SUCCESS, "щось там");
//
//            redirect = new RedirectResult(USER_WALLETS_URL);
//        } else {
//            session.setAttribute(WRONG_DATA, topUpCheck);
//            redirect = new RedirectResult(TOP_UP_BALANCE_URL);
//        }

        return redirect;
    }
}
