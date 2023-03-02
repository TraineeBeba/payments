package com.epam.payments.command.navigation.user;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.ForwardResult;
import com.epam.payments.command.result.RedirectResult;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoCreateWalletCommand extends Command  {
    public static final Logger LOG = Logger.getLogger(GoCreateWalletCommand.class);
    private static final long serialVersionUID = -3743555001204859976L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        LOG.trace("Start tracing GetCommand");

//        return new ForwardResult(CREATE_WALLET_PATH);
        return new RedirectResult("USER_WALLETS_URL");
    }
}