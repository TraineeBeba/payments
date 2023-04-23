package com.epam.payments.command.common.user.wallet;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.core.model.dto.UserDTO;
import com.epam.payments.core.service.wallet.WalletService;
import com.epam.payments.core.service.wallet.WalletCreationException;
import com.epam.payments.core.utils.ServletUtils;
import com.epam.payments.exeption.InternalServerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.payments.command.constant.ParamNames.*;
import static com.epam.payments.command.constant.ParamNames.WRONG_DATA;
import static com.epam.payments.command.constant.WebUrlConstants.*;


public class CreateWalletCommand extends Command {
    private static final Logger LOG = Logger.getLogger(CreateWalletCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException {
        LOG.trace("Start tracing CreateWalletCommand");

        HttpSession session = request.getSession();
        WalletService walletService = ServletUtils.getService(request, WalletService.class);

        String walletName = ServletUtils.getStringParameter(request, WALLET_NAME);
        UserDTO userDTO = ServletUtils.getAttribute(session, USER_DTO, UserDTO.class);

        RedirectResult redirect;
        try {
            walletService.createWallet(userDTO, walletName);

            session.setAttribute(WALLET_CREATION_SUCCESS, true);
            redirect = new RedirectResult(GO_USER_WALLETS_PAGE_URL);
        } catch (WalletCreationException e) {
            session.setAttribute(WRONG_DATA, e.getMessage());
            redirect = new RedirectResult(GO_CREATE_WALLET_PAGE_URL);
        }

        return redirect;

    }


}