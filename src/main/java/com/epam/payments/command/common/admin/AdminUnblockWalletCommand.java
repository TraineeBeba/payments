package com.epam.payments.command.common.admin;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.core.model.entity.WalletEntity;
import com.epam.payments.core.model.enums.status.RequestStatus;
import com.epam.payments.core.service.wallet.exception.WalletNotFoundException;
import com.epam.payments.core.service.wallet.WalletService;
import com.epam.payments.core.service.wallet_request.WalletRequestService;
import com.epam.payments.core.utils.ServletUtils;
import com.epam.payments.exeption.InternalServerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.payments.command.constant.ParamNames.*;
import static com.epam.payments.command.constant.WebUrlConstants.*;


public class AdminUnblockWalletCommand extends Command {
    private static final Logger LOG = Logger.getLogger(AdminUnblockWalletCommand.class);
    private static final long serialVersionUID = 5063715519941606153L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException {
        LOG.trace("Start tracing BlockWalletCommand");
        HttpSession session = request.getSession();

        RedirectResult redirect;

        WalletService walletService = ServletUtils.getService(request, WalletService.class);
        WalletRequestService walletRequestService = ServletUtils.getService(request, WalletRequestService.class);
        int bill_number = ServletUtils.getIntParameter(request, BILL_NUMBER);
        String username = ServletUtils.getStringParameter(request, SELECTED_USER_NAME);

        session.setAttribute(SELECTED_USER_NAME, username);

        try{
            walletService.unblockWallet(bill_number);
            WalletEntity walletByBill = walletService.getWalletByBill(bill_number);
            if(walletRequestService.existsByWalletIdAndStatus(walletByBill.getId(), RequestStatus.IN_PROCESS)){
                walletRequestService.confirmByWalletIdAndStatus(walletByBill.getId(), RequestStatus.IN_PROCESS);
            }
            session.setAttribute(UNBLOCK_WALLET_SUCCESS, true);
            redirect = new RedirectResult(GO_ADMIN_USER_WALLETS_PAGE_URL);
        } catch (WalletNotFoundException e){
            session.setAttribute(WRONG_DATA, e.getMessage());
            redirect = new RedirectResult(GO_ADMIN_USER_WALLETS_PAGE_URL);
        }

        return redirect;
    }
}
