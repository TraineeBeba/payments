package com.epam.payments.command.common.authentication;

import com.epam.payments.command.Command;
import com.epam.payments.core.model.dto.UserDTO;
import com.epam.payments.core.model.enums.role.Role;
import com.epam.payments.core.service.user.exception.AuthenticationException;
import com.epam.payments.core.service.user.exception.UserValidationException;
import com.epam.payments.core.service.user.UserService;
import com.epam.payments.core.utils.ServletUtils;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.exeption.InternalServerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.payments.command.constant.ParamNames.*;
import static com.epam.payments.command.constant.WebUrlConstants.*;

public class LoginCommand extends Command {
    private static final Logger LOG = Logger.getLogger(LoginCommand.class);
    private static final long serialVersionUID = -7190245479634943129L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException {
        LOG.trace("Start tracing LoginCommand");

        final UserService userService = ServletUtils.getService(request, UserService.class);
        final String username = ServletUtils.getStringParameter(request, USERNAME);
        final String password = ServletUtils.getStringParameter(request, PASSWORD);

        HttpSession session = request.getSession();
        RedirectResult redirectResult;
        try{
            UserDTO userEntity = userService.authenticateUser(username, password);
            session.setAttribute(USER_DTO, userEntity);

            if (userEntity.getRole() == Role.ROLE_ADMIN) {
                return new RedirectResult(GO_ADMIN_USERS_PAGE_URL);
            } else {
                redirectResult = new RedirectResult(GO_USER_WALLETS_PAGE_URL);
            }
        } catch (AuthenticationException | UserValidationException e) {
            session.setAttribute(WRONG_DATA, e.getMessage());
            redirectResult =  new RedirectResult(GO_LOGIN_PAGE_URL);
        }

        return redirectResult;
    }
}
