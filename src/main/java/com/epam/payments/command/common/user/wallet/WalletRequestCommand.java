package com.epam.payments.command.common.user.wallet;

import com.epam.payments.command.Command;
import com.epam.payments.command.constant.WebUrlConstants;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.core.model.dto.WalletDTO;
import com.epam.payments.core.model.entity.WalletEntity;
import com.epam.payments.core.model.enums.type.WalletRequestType;
import com.epam.payments.core.service.wallet.WalletNotFoundException;
import com.epam.payments.core.service.wallet.WalletService;
import com.epam.payments.core.service.wallet_request.WalletRequestService;
import com.epam.payments.core.utils.ServletUtils;
import com.epam.payments.exeption.InternalServerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.net.Authenticator;

import static com.epam.payments.command.constant.ParamNames.*;
import static com.epam.payments.core.model.enums.status.RequestStatus.IN_PROCESS;

public class WalletRequestCommand extends Command {
    private static final Logger LOG = Logger.getLogger(WalletRequestCommand.class);

    private static final long serialVersionUID = 5063715519941606153L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException {
        LOG.trace("Start tracing RequestCommandCommand");

        HttpSession session = request.getSession();

        RedirectResult redirect;

        WalletRequestService walletRequestService = ServletUtils.getService(request, WalletRequestService.class);
        WalletService walletService = ServletUtils.getService(request, WalletService.class);
        int bill_number = ServletUtils.getIntParameter(request, BILL_NUMBER);
        WalletEntity walletEntity;
        try {
            walletEntity = walletService.getWalletByBill(bill_number);
        } catch (WalletNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (walletRequestService.existsByWalletIdAndStatus(walletEntity.getId(), IN_PROCESS)){
            session.setAttribute(REQUEST_EXIST, true);
        } else {
            WalletRequestType walletRequestType = WalletRequestType.getType(ServletUtils.getStringParameter(request, REQUEST_TYPE));
            walletRequestService.create(walletEntity, IN_PROCESS, walletRequestType);

            session.setAttribute(REQUEST_CREATION_SUCCESS, true);
        }

        redirect = new RedirectResult(WebUrlConstants.GO_USER_WALLETS_PAGE_URL);

        return redirect;
    }
}
