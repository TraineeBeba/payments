package com.epam.payments.command.common.wallet;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;

import com.epam.payments.core.model.enums.status.RequestStatus;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.payments.command.constant.ParamNames.*;

public class AnswerWalletRequestCommand extends Command {
    private static final Logger LOG = Logger.getLogger(AnswerWalletRequestCommand.class);

    private static final long serialVersionUID = 5063715519941606153L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Start tracing RequestCommandCommand");
        HttpSession session = request.getSession();

        RedirectResult redirect;

        Long requestStatusId;
        Long walletRequestId;
        if(request.getParameter(REQUEST_STATUS_ID) != null && request.getParameter(WALLET_REQUEST_ID) != null) {
            requestStatusId = Long.parseLong(request.getParameter(REQUEST_STATUS_ID));
            walletRequestId = Long.parseLong(request.getParameter(WALLET_REQUEST_ID));

//            new WalletRequestServiceImpl().getMySQLWalletRequestDAO().update(walletRequestId, requestStatusId);
            if(RequestStatus.AFFIRMATIVE.getId().equals(requestStatusId)){
//                new WalletServiceImpl().getMySQLWalletDAO().updateByWalletId();
            }


            session.setAttribute(REQUEST_CREATION_SUCCESS, "chet tam");
        }

//

//        redirect = new RedirectResult(USER_WALLETS_URL);
        return new RedirectResult("USER_WALLETS_URL");

//        return redirect;
    }
}
