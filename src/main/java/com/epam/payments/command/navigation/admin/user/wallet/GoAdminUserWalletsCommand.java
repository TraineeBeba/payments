package com.epam.payments.command.navigation.admin.user.wallet;

import com.epam.payments.command.Command;
import com.epam.payments.command.navigation.user.wallet.GoUserWalletsCommand;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.ForwardResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.core.model.dto.UserDTO;
import com.epam.payments.core.model.dto.WalletDTO;
import com.epam.payments.core.service.user.UserService;
import com.epam.payments.core.service.user.exception.UserNotFoundException;
import com.epam.payments.core.service.wallet.WalletService;
import com.epam.payments.core.utils.ServletUtils;
import com.epam.payments.core.utils.exeption.AttributeNotFoundException;
import com.epam.payments.core.utils.exeption.ParameterNotFoundException;
import com.epam.payments.exeption.InternalServerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.epam.payments.command.constant.ParamNames.*;
import static com.epam.payments.command.constant.SortConstants.DEFAULT_USER_SORT;
import static com.epam.payments.command.constant.SortConstants.DEFAULT_WALLET_SORT;
import static com.epam.payments.command.constant.WebPathConstants.*;
import static com.epam.payments.command.constant.WebUrlConstants.GO_ADMIN_USERS_PAGE_URL;


public class GoAdminUserWalletsCommand extends Command {
    public static final Logger LOG = Logger.getLogger(GoUserWalletsCommand.class);
    private static final long serialVersionUID = -4327477400419749096L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException {
        LOG.trace("Start tracing GoUserWalletsCommand");
        HttpSession session = request.getSession();

        WalletService walletService = ServletUtils.getService(request, WalletService.class);
        UserService userService = ServletUtils.getService(request, UserService.class);
        String walletSort = getWalletSort(request);

        String username = getUsername(request, session);
        UserDTO userDTO;
        try {
            userDTO = userService.findByUsername(username);
            List<WalletDTO> wallets = walletService.getSortedListByUserDTO(userDTO, walletSort);
            request.setAttribute(WALLETS, wallets);
            request.setAttribute(SELECTED_USER_NAME, username);
//            session.setAttribute(WALLET_SORT, walletSort);

        } catch (UserNotFoundException e) {
            session.setAttribute(WRONG_DATA, e.getMessage());
            return new RedirectResult(GO_ADMIN_USERS_PAGE_URL);
        }

        return new ForwardResult(ADMIN_USER_WALLETS_PATH);
    }

    private String getWalletSort(HttpServletRequest request) {
        String walletSort;
        try {
            walletSort = ServletUtils.getStringParameter(request, WALLET_SORT);
        } catch (ParameterNotFoundException e) {
            walletSort = DEFAULT_WALLET_SORT;
        }
        return walletSort;
    }

    private String getUsername(HttpServletRequest request, HttpSession session) throws AttributeNotFoundException {
        String username;
        try {
            username = ServletUtils.getStringParameter(request, SELECTED_USER_NAME);
        } catch (ParameterNotFoundException e) {
            username = ServletUtils.getAttribute(session, SELECTED_USER_NAME, String.class);
            session.removeAttribute(SELECTED_USER_NAME);
        }
        return username;
    }
}