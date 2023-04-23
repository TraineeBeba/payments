package com.epam.payments.command.common.user.wallet;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.core.service.wallet.WalletService;
import com.epam.payments.core.service.wallet.MaxBalanceException;
import com.epam.payments.core.utils.ServletUtils;
import com.epam.payments.exeption.InternalServerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.math.BigDecimal;

import static com.epam.payments.command.constant.ParamNames.*;
import static com.epam.payments.command.constant.WebUrlConstants.GO_TOP_UP_BALANCE_PAGE_URL;
import static com.epam.payments.command.constant.WebUrlConstants.GO_USER_WALLETS_PAGE_URL;

public class TopUpBalanceCommand extends Command {
    private static final Logger LOG = Logger.getLogger(TopUpBalanceCommand.class);


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException {
        LOG.trace("Start tracing CreateWalletCommand");

        HttpSession session = request.getSession();
        WalletService walletService = ServletUtils.getService(request, WalletService.class);

        int bill_number = ServletUtils.getIntParameter(request, BILL_NUMBER);
        BigDecimal sum = new BigDecimal(ServletUtils.getStringParameter(request, SUM));

        RedirectResult redirect;
        try {
            walletService.topUpWallet(bill_number, sum);

            session.setAttribute(TOP_UP_SUCCESS, true);
            redirect = new RedirectResult(GO_USER_WALLETS_PAGE_URL);
        } catch (MaxBalanceException e) {
            session.setAttribute(WRONG_DATA, e.getMessage());
            redirect = new RedirectResult(GO_TOP_UP_BALANCE_PAGE_URL);
        }

        return redirect;
    }
}
