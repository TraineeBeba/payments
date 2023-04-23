package com.epam.payments.command.common.admin;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.core.service.user.UserService;
import com.epam.payments.core.service.user.exception.UserNotFoundException;
import com.epam.payments.core.utils.ServletUtils;
import com.epam.payments.exeption.InternalServerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.payments.command.constant.CommandNames.GO_ADMIN_USERS_PAGE;
import static com.epam.payments.command.constant.ParamNames.*;
import static com.epam.payments.command.constant.WebUrlConstants.GO_ADMIN_USERS_PAGE_URL;

public class BlockUserCommand extends Command {
    private static final Logger LOG = Logger.getLogger(BlockUserCommand.class);

    private static final long serialVersionUID = 5063715519941606153L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException {
        LOG.trace("Start tracing BlockUserCommand");

        HttpSession session = request.getSession();
        UserService userService = ServletUtils.getService(request, UserService.class);
        String username = ServletUtils.getStringParameter(request, SELECTED_USER_NAME);
        try {
            userService.blockUser(username);
        } catch (UserNotFoundException e) {
            session.setAttribute(WRONG_DATA, e.getMessage());
        }

        return new RedirectResult(GO_ADMIN_USERS_PAGE_URL);
    }
}
