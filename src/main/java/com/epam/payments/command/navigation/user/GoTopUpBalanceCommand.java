package com.epam.payments.command.navigation.user;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.ForwardResult;
import com.epam.payments.core.model.entity.UserEntity;
import com.epam.payments.core.model.entity.WalletEntity;
import com.epam.payments.core.service.UserService;
import com.epam.payments.core.service.WalletService;
import com.epam.payments.core.service.impl.UserServiceImpl;
import com.epam.payments.core.service.impl.WalletServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoTopUpBalanceCommand extends Command {
    private static final Logger LOG = Logger.getLogger(GoTopUpBalanceCommand.class);
    private static final long serialVersionUID = 5725994496025190049L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        LOG.trace("Start tracing GoTopUpBalanceCommand");

        HttpSession session = request.getSession();
        UserEntity userEntity = getUserEntityAttribute(session);
        String walletSort = getWalletSortParameter(session);
//        List<WalletEntity> wallets = walletServiceImpl.getUnblockedByUserId(userEntity, walletSort);

//        setRequestAttributes(request, wallets);

        return new ForwardResult(TOP_UP_BALANCE_PATH);
    }

    public String getWalletSortParameter(HttpSession session) {
        String walletSort = DEFAULT_WALLET_SORT;
        if(session.getAttribute(WALLET_SORT) != null) {
            walletSort = (String) session.getAttribute(WALLET_SORT);
        }
        return walletSort;
    }

    public UserEntity getUserEntityAttribute(HttpSession session) {
        return (UserEntity) session.getAttribute(USER_ENTITY);
    }

    private void setRequestAttributes(HttpServletRequest request, List<WalletEntity> wallets) {
        request.setAttribute(WALLETS, wallets);
    }
}
