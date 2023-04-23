package com.epam.payments.command.common.user.wallet;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.core.service.wallet.WalletService;
import com.epam.payments.core.service.wallet.WalletNotFoundException;
import com.epam.payments.core.utils.ServletUtils;
import com.epam.payments.exeption.InternalServerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.payments.command.constant.ParamNames.*;
import static com.epam.payments.command.constant.WebUrlConstants.GO_CREATE_WALLET_PAGE_URL;
import static com.epam.payments.command.constant.WebUrlConstants.GO_USER_WALLETS_PAGE_URL;

public class BlockWalletCommand extends Command {
    private static final Logger LOG = Logger.getLogger(BlockWalletCommand.class);
    private static final long serialVersionUID = 5063715519941606153L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException {
        LOG.trace("Start tracing BlockWalletCommand");
        HttpSession session = request.getSession();

        RedirectResult redirect;

        WalletService walletService = ServletUtils.getService(request, WalletService.class);
        int bill_number = ServletUtils.getIntParameter(request, BILL_NUMBER);

        try{
            walletService.blockWallet(bill_number);
            session.setAttribute(BLOCK_WALLET_SUCCESS, true);
            redirect = new RedirectResult(GO_USER_WALLETS_PAGE_URL);
        } catch (WalletNotFoundException e){
            session.setAttribute(WRONG_DATA, e.getMessage());
            redirect = new RedirectResult(GO_CREATE_WALLET_PAGE_URL);
        }

        return redirect;
    }
}
