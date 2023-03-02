package com.epam.payments.command.navigation.user;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.ForwardResult;
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
import java.util.List;

import static com.epam.payments.command.constant.ParamNames.*;
import static com.epam.payments.command.constant.SortConstants.*;


public class GoPrepareTransferCommand extends Command {
    public static final Logger LOG = Logger.getLogger(GoPrepareTransferCommand.class);
    private static final long serialVersionUID = 1821296870058989837L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Start tracing GoPrepareTransferCommand");

        HttpSession session = request.getSession();
        String walletSort = getWalletSortParameter(session);
        UserEntity userEntity = getUserDTO(session); // filter check

//        List<WalletEntity> wallets = walletServiceImpl.getUnblockedByUserId(userEntity, walletSort);
//        setRequestAttributes(request, wallets);

//        return new ForwardResult(PREPARE_TRANSFER_PATH);
        return new RedirectResult("USER_WALLETS_URL");
    }

    private void setRequestAttributes(HttpServletRequest request, List<WalletEntity> wallets) {
        request.setAttribute(WALLETS, wallets);
    }

    private UserEntity getUserDTO(HttpSession session) {
        return (UserEntity) session.getAttribute(USER_ENTITY);
    }

    private String getWalletSortParameter(HttpSession session) {
        String walletSort = DEFAULT_WALLET_SORT;
        if(session.getAttribute(WALLET_SORT) != null) {
            walletSort = (String) session.getAttribute(WALLET_SORT);
        }
        return walletSort;
    }

}
