package com.epam.payments.command.navigation.admin;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.ForwardResult;
import com.epam.payments.core.model.entity.UserEntity;
import com.epam.payments.core.service.UserService;
import com.epam.payments.core.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        LOG.trace("Start tracing GetCommand");

//        UserService userService = UserServiceImpl.getInstance();
//        int page = getPageParameter(request);
//        int offset = (page - 1) * DEFAULT_RECORDS_PER_PAGE;
//        String userSort = getUserSortParameter(request);
//        List<UserEntity> users = userService.getSortedList(userSort, offset, DEFAULT_RECORDS_PER_PAGE);
//        int noOfTransferPages = getNoOfTransferPages();
//
//        setRequestAttributes(request, page, userSort, users, noOfTransferPages);

        return new ForwardResult(ADMIN_USERS_PATH);
    }

//    private int getNoOfTransferPages() {
//        int noOfRecords = userService.getNoOfRecords();
//        return (int) Math.ceil(noOfRecords * 1.0 / DEFAULT_RECORDS_PER_PAGE);
//    }


    private int getPageParameter(HttpServletRequest request) {
        int page = DEFAULT_PAGE;
        if (request.getParameter(PAGE) == null) {
            page = Integer.parseInt(request.getParameter(PAGE));
        }

        return page;
    }

    public String getUserSortParameter(HttpServletRequest request) {
        String userSort = DEFAULT_USER_SORT;
        if (request.getParameter(USER_SORT) != null) {
            userSort = request.getParameter(USER_SORT);
        }
        LOG.info("Sort is switched to " + userSort.toUpperCase());

        return userSort;
    }

    private void setRequestAttributes(HttpServletRequest request, int page, String userSort,
                                      List<UserEntity> users, int noOfTransferPages) {
        request.setAttribute(PAGE, page);
        request.setAttribute(USER_SORT, userSort);
        request.setAttribute(USERS, users);
        request.setAttribute(NO_OF_TRANSFER_PAGES, noOfTransferPages);
    }
}