package com.epam.payments.command.common.wallet;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.core.model.entity.UserEntity;
import com.epam.payments.core.model.entity.WalletEntity;
import com.epam.payments.core.service.WalletService;
import com.epam.payments.core.service.impl.WalletServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.payments.core.model.enums.role.Role.ROLE_ADMIN;
import static com.epam.payments.core.model.enums.state.WalletState.BLOCKED;


public class UnblockWalletCommand extends Command {
    private static final Logger LOG = Logger.getLogger(UnblockWalletCommand.class);
    private static final long serialVersionUID = 5063715519941606153L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Start tracing BlockWalletCommand");
        HttpSession session = request.getSession();

        RedirectResult redirect;

//        Long walletId;
//        if (request.getParameter(WALLET_ID) != null) {
//            walletId = Long.parseLong(request.getParameter(WALLET_ID));
//            WalletEntity walletEntity = walletService.findById(walletId);
//            walletEntity.setState(BLOCKED);
//
//            walletService.update(walletEntity);
//
//            UserEntity userEntity = (UserEntity) session.getAttribute(USER_ENTITY);
//            if(userEntity.getRole() == ROLE_ADMIN) {
//                session.setAttribute(UNBLOCK_WALLET_SUCCESS, true);
//            }
//        }
//        redirect = new RedirectResult(USER_WALLETS_URL);
        return new RedirectResult("USER_WALLETS_URL");

//        return redirect;
    }
}
