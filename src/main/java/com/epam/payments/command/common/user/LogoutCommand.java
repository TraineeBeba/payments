package com.epam.payments.command.common.user;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.exeption.InternalServerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.payments.command.constant.ParamNames.*;
import static com.epam.payments.command.constant.WebUrlConstants.LOGIN_URL;

public class LogoutCommand extends Command {
    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);
    private static final long serialVersionUID = -7190245479634943129L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException {
        LOG.trace("Start tracing LogoutCommand");

        
        // TODO

        HttpSession session = request.getSession();
        session.removeAttribute(USER_ENTITY);
        session.removeAttribute(WALLET_SORT);

        session.removeAttribute(SELECTED_USER);
        session.setAttribute(LOGOUT, true);

        return new RedirectResult(LOGIN_URL);
    }


}