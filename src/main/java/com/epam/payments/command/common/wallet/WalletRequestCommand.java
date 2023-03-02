package com.epam.payments.command.common.wallet;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.core.model.entity.WalletRequestEntity;
import com.epam.payments.core.model.enums.type.WalletRequestType;
import com.epam.payments.core.service.WalletRequestService;
import com.epam.payments.core.service.impl.WalletRequestServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.epam.payments.core.model.enums.status.RequestStatus.IN_PROCESS;

public class WalletRequestCommand extends Command {
    private static final Logger LOG = Logger.getLogger(WalletRequestCommand.class);

    private static final long serialVersionUID = 5063715519941606153L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Start tracing RequestCommandCommand");

        HttpSession session = request.getSession();

        RedirectResult redirect;

//        WalletRequestType walletRequestType;
//        Long walletId;
//        if (request.getParameter(WALLET_ID) != null && request.getParameter(REQUEST_TYPE) != null) {
//
//            walletId = Long.parseLong(request.getParameter(WALLET_ID));
//            if (walletRequestService.existsByWalletIdAndStatusID(walletId, IN_PROCESS.getId())){
//                session.setAttribute(REQUEST_EXIST, true);
//            } else {
//                walletRequestType = WalletRequestType.getType(request.getParameter(REQUEST_TYPE));
//
//                WalletRequestEntity walletRequestEntity = new WalletRequestEntity(walletId, IN_PROCESS.getId(), walletRequestType.getId());
//                walletRequestService.save(walletRequestEntity);
//
//                session.setAttribute(REQUEST_CREATION_SUCCESS, true);
//            }
//
//        }

//        redirect = new RedirectResult(USER_WALLETS_URL);
        return new RedirectResult("USER_WALLETS_URL");
//        return redirect;
    }
}
