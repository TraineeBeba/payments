package com.epam.payments.command.common.admin;

import com.epam.payments.command.common.user.wallet.BlockWalletCommand;
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

import static com.epam.payments.command.constant.ParamNames.CURR_USER_ID;
import static com.epam.payments.command.constant.ParamNames.WRONG_DATA;

public class UnblockUserCommand extends Command {
    private static final long serialVersionUID = -4490739173914257087L;
    private static final Logger LOG = Logger.getLogger(BlockWalletCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException {
        LOG.trace("Start tracing UnblockUserCommand");

        UserService userService = ServletUtils.getService(request, UserService.class);
        Long userId = ServletUtils.getLongParameter(request, CURR_USER_ID);
        try {
            userService.unblockUser(userId);
        } catch (UserNotFoundException e) {
            HttpSession session = request.getSession();
            session.setAttribute(WRONG_DATA, e.getMessage());
//            return new RedirectResult(ADMIN_USERS_URL);
            return new RedirectResult("USER_WALLETS_URL");
        }

//        return new RedirectResult(ADMIN_USERS_URL);
        return new RedirectResult("USER_WALLETS_URL");
    }
}
