package com.epam.payments.command.navigation.admin;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.ForwardResult;
import com.epam.payments.core.model.dto.UserDTO;
import com.epam.payments.core.model.entity.UserEntity;
import com.epam.payments.core.service.user.UserService;
import com.epam.payments.core.service.wallet.WalletService;
import com.epam.payments.core.utils.ServletUtils;
import com.epam.payments.core.utils.exeption.ParameterNotFoundException;
import com.epam.payments.exeption.InternalServerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.epam.payments.command.constant.ParamNames.*;
import static com.epam.payments.command.constant.SortConstants.*;
import static com.epam.payments.command.constant.WebPathConstants.ADMIN_USERS_PATH;


public class GoAdminUsersCommand extends Command {
    public static final Logger LOG = Logger.getLogger(GoAdminUsersCommand.class);
    private static final long serialVersionUID = -8980260478647442818L;

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_RECORDS_PER_PAGE = 4;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException {
        LOG.trace("Start tracing GetCommand");

        HttpSession session = request.getSession();
        UserService userService = ServletUtils.getService(request, UserService.class);
        int page;
        try {
            page = ServletUtils.getIntParameter(request, PAGE);
        } catch (ParameterNotFoundException e) {
            page = DEFAULT_PAGE;
        }
        String userSort;
        try {
            userSort = ServletUtils.getStringParameter(request, USER_SORT);
        } catch (ParameterNotFoundException e) {
            userSort = DEFAULT_USER_SORT;
        }
        int offset = (page - 1) * DEFAULT_RECORDS_PER_PAGE;
        List<UserDTO> users = userService.getSortedList(userSort, offset, DEFAULT_RECORDS_PER_PAGE);
        int noOfTransferPages = (int) Math.ceil(userService.getNoOfRecords() * 1.0 / DEFAULT_RECORDS_PER_PAGE);

        request.setAttribute(PAGE, page);
        request.setAttribute(USER_SORT, userSort);
        request.setAttribute(USERS, users);
        request.setAttribute(NO_OF_TRANSFER_PAGES, noOfTransferPages);

        return new ForwardResult(ADMIN_USERS_PATH);
    }
}