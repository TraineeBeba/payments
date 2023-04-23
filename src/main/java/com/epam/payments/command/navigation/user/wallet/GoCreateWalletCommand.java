package com.epam.payments.command.navigation.user.wallet;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.ForwardResult;
import com.epam.payments.command.result.RedirectResult;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.payments.command.constant.CommandNames.GO_CREATE_WALLET_PAGE;
import static com.epam.payments.command.constant.WebPathConstants.USER_CREATE_WALLET_PATH;

public class GoCreateWalletCommand extends Command  {
    public static final Logger LOG = Logger.getLogger(GoCreateWalletCommand.class);
    private static final long serialVersionUID = -3743555001204859976L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        LOG.trace("Start tracing GoCreateWalletCommand");

        return new ForwardResult(USER_CREATE_WALLET_PATH);
    }
}