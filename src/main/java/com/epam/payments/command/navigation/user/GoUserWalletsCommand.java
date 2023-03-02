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

import static com.epam.payments.command.constant.ParamNames.*;
import static com.epam.payments.command.constant.SortConstants.*;
import static com.epam.payments.command.constant.WebPathConstants.*;

public class GoUserWalletsCommand extends Command {
    public static final Logger LOG = Logger.getLogger(GoUserWalletsCommand.class);
    private static final long serialVersionUID = -4327477400419749096L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Start tracing GoUserWalletsCommand");

//        UserService userService = UserServiceImpl.getInstance();
//        HttpSession session = request.getSession();
//        String walletSort = getWalletSortParameter(request);
//        UserEntity userEntity = getUserEntity(request, session);
//        List<WalletEntity> wallets = walletService.getSortedListByUserId(userEntity.getId(), walletSort);
//
//        setAttributes(request, session, wallets, walletSort);

        return new ForwardResult(USER_WALLETS_PATH);
    }

    private String getWalletSortParameter(HttpServletRequest request) {
        String walletSort = DEFAULT_WALLET_SORT;
        if (request.getParameter(WALLET_SORT) != null) {
            walletSort = request.getParameter(WALLET_SORT);
        }
        return walletSort;
    }

//    private UserEntity getUserEntity(HttpServletRequest request, HttpSession session) {
//        UserEntity userEntity;
//        if(request.getParameter(SELECTED_USER_NAME) != null) {
//            String userName = request.getParameter(SELECTED_USER_NAME);
////            userEntity = userService.findByUsername(userName);
//
////            session.setAttribute(SELECTED_USER, userEntity);
//        } else if (session.getAttribute(SELECTED_USER) != null){
//            userEntity = (UserEntity) session.getAttribute(SELECTED_USER);
//        } else {
//            userEntity = (UserEntity) session.getAttribute(USER_ENTITY);
//        }
//
//        return userEntity;
//    }

    private void setAttributes(HttpServletRequest request, HttpSession session, List<WalletEntity> wallets,
                               String walletSort) {
        request.setAttribute(WALLETS, wallets);
        session.setAttribute(WALLET_SORT, walletSort);
    }
}