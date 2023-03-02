package com.epam.payments.command.common.user;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.core.service.exeption.user.RegistrationException;
import com.epam.payments.core.service.exeption.user.UserValidationException;
import com.epam.payments.core.service.UserService;
import com.epam.payments.core.utils.ServletUtils;
import com.epam.payments.core.service.impl.UserServiceImpl;
import com.epam.payments.exeption.InternalServerException;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.payments.command.constant.ParamNames.*;
import static com.epam.payments.command.constant.WebUrlConstants.LOGIN_URL;
import static com.epam.payments.command.constant.WebUrlConstants.REGISTER_URL;


@WebServlet(name = "RegisterCommand")
public class RegisterCommand extends Command {
    private static final Logger LOG = Logger.getLogger(RegisterCommand.class);
    private static final long serialVersionUID = -7190245479634943129L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException {
        LOG.trace("Start tracing RegisterCommand");

        final UserService userService = ServletUtils.getService(request, UserService.class);
        final String username = ServletUtils.getStringParameter(request, USERNAME);
        final String password = ServletUtils.getStringParameter(request, PASSWORD);

        HttpSession session = request.getSession();
        RedirectResult redirectResult;
        try{
            userService.registerUser(username, password);
            session.setAttribute(REGISTER_SUCCESS, true);
            redirectResult = new RedirectResult(LOGIN_URL);

        } catch (RegistrationException | UserValidationException e) {
            session.setAttribute(WRONG_DATA, e.getMessage());
            redirectResult = new RedirectResult(REGISTER_URL);
        }

        return redirectResult;
    }
}