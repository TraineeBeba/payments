package com.epam.payments.command.navigation.admin;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.core.model.entity.WalletRequestEntity;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


import static com.epam.payments.command.constant.ParamNames.*;
import static com.epam.payments.command.constant.SortConstants.*;

public class GoWalletRequestsCommand extends Command {
    public static final Logger LOG = Logger.getLogger(GoWalletRequestsCommand.class);
    private static final long serialVersionUID = 3725743937108507007L;

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_RECORDS_PER_PAGE = 4;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Start tracing GoWalletRequestsCommand");
//        WalletRequestService walletRequestService = WalletRequestServiceImpl.getInstance();
//        String walletRequestSort = getWalletRequestSortParameter(request);
//        int page = getPageParameter(request);
//        int offset = (page-1)* DEFAULT_RECORDS_PER_PAGE;
//        List<WalletRequestEntity> walletRequests = walletRequestService.getSortedList(
//                walletRequestSort, offset, DEFAULT_RECORDS_PER_PAGE, IN_PROCESS
//        );
//        int noOfTransferPages = getNoOfTransferPages();
//
//        setRequestAttributes(request, page, walletRequestSort, walletRequests, noOfTransferPages);

//        return new ForwardResult(WALLET_REQUESTS_PATH);
        return new RedirectResult("USER_WALLETS_URL");
    }

//    private int getNoOfTransferPages() {
//        int noOfRecords = walletRequestService.getNoOfRecords();
//        return (int) Math.ceil(noOfRecords * 1.0 / DEFAULT_RECORDS_PER_PAGE);
//    }

    private String getWalletRequestSortParameter(HttpServletRequest request) {
        String walletRequestSort = DEFAULT_WALLET_REQUEST_SORT;
        if (request.getParameter(WALLET_REQUEST_SORT) != null) {
            walletRequestSort = request.getParameter(WALLET_REQUEST_SORT);
        }
        LOG.info("Sort is switched to " + walletRequestSort.toUpperCase());

        return walletRequestSort;
    }

    private int getPageParameter(HttpServletRequest request) {
        int page = DEFAULT_PAGE;
        if (request.getParameter(PAGE) != null) {
            page = Integer.parseInt(request.getParameter(PAGE));
        }
        return page;
    }

    private void setRequestAttributes(HttpServletRequest request, int page, String walletRequestSort,
                                      List<WalletRequestEntity> walletRequests, int noOfTransferPages) {
        request.setAttribute(PAGE, page);
        request.setAttribute(WALLET_REQUEST_SORT, walletRequestSort);
        request.setAttribute(WALLET_REQUESTS, walletRequests);
        request.setAttribute(NO_OF_TRANSFER_PAGES, noOfTransferPages);
    }
}