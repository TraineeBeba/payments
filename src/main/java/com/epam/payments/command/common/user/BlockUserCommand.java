package com.epam.payments.command.common.user;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.core.service.UserService;
import com.epam.payments.core.service.exeption.user.UserNotFoundException;
import com.epam.payments.core.utils.ServletUtils;
import com.epam.payments.exeption.InternalServerException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BlockUserCommand extends Command {
    private static final Logger LOG = Logger.getLogger(BlockUserCommand.class);

    private static final long serialVersionUID = 5063715519941606153L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException {
        LOG.trace("Start tracing BlockUserCommand");

        UserService userService = ServletUtils.getService(request, UserService.class);
        Long userId = ServletUtils.getLongParameter(request, CURR_USER_ID);
        try {
            userService.blockUser(userId);
        } catch (UserNotFoundException e) {
            HttpSession session = request.getSession();
            session.setAttribute(WRONG_DATA, e.getMessage());
            return new RedirectResult(ADMIN_USERS_URL);
        }

        return new RedirectResult(ADMIN_USERS_URL);
    }
}
