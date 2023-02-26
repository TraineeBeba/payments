package com.epam.payments.command.common.wallet;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.core.model.entity.UserEntity;
import com.epam.payments.core.model.entity.WalletEntity;
import com.epam.payments.core.service.WalletService;
import com.epam.payments.core.service.impl.WalletServiceImpl;
import com.epam.payments.core.utils.Utils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

import static com.epam.payments.core.model.enums.state.WalletState.UNBLOCKED;


public class CreateWalletCommand extends Command {
    private static final Logger LOG = Logger.getLogger(CreateWalletCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Start tracing CreateWalletCommand");

//        HttpSession session = request.getSession();
//
//        String name = request.getParameter(WALLET_NAME);
//        UserEntity userEntity = getUserEntityAttribute(session);
//
//        if (name == null || userEntity == null) {
//            session.setAttribute(WRONG_DATA, "Some data is missing");
//            return new RedirectResult("goError!!!");
//        }
//
//        int billNumber = Utils.generateBill();
//        BigDecimal balance = new BigDecimal(0.0);
//        WalletEntity walletEntity = new WalletEntity(userEntity, UNBLOCKED, name, billNumber, balance);
//
//        String isValidCreate = walletService.isValidCreate(walletEntity);
//        if(isValidCreate == null) {
//            walletService.save(walletEntity);
//            session.setAttribute(WALLET_CREATION_SUCCESS, true);
//            return new RedirectResult(USER_WALLETS_URL);
//        } else {
//            session.setAttribute(WRONG_DATA, isValidCreate);
//            return new RedirectResult(CREATE_WALLET_URL);
//        }
        return null;
    }

    public UserEntity getUserEntityAttribute(HttpSession session) {
        return (UserEntity) session.getAttribute(USER_ENTITY);
    }
}