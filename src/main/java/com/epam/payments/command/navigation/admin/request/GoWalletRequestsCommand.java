package com.epam.payments.command.navigation.admin.request;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.core.model.entity.WalletRequestEntity;
import com.epam.payments.core.service.wallet_request.WalletRequestService;
import com.epam.payments.core.utils.ServletUtils;
import com.epam.payments.core.utils.exeption.ParameterNotFoundException;
import com.epam.payments.exeption.InternalServerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


import static com.epam.payments.command.constant.ParamNames.*;
import static com.epam.payments.command.constant.SortConstants.*;
import static com.epam.payments.core.model.enums.status.RequestStatus.IN_PROCESS;

public class GoWalletRequestsCommand extends Command {
    public static final Logger LOG = Logger.getLogger(GoWalletRequestsCommand.class);
    private static final long serialVersionUID = 3725743937108507007L;

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_RECORDS_PER_PAGE = 4;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException {
        LOG.trace("Start tracing GoWalletRequestsCommand");
        WalletRequestService walletRequestService = ServletUtils.getService(request, WalletRequestService.class);
        String walletRequestSort = getWalletRequestSortParameter(request);
        int page = getPage(request);
        int offset = (page-1)* DEFAULT_RECORDS_PER_PAGE;
        List<WalletRequestEntity> walletRequests = walletRequestService.getSortedList(
                walletRequestSort, offset, DEFAULT_RECORDS_PER_PAGE, IN_PROCESS
        );
        int noOfTransferPages = (int) Math.ceil(walletRequestService.getNoOfRecords() * 1.0 / DEFAULT_RECORDS_PER_PAGE);

        request.setAttribute(PAGE, page);
        request.setAttribute(WALLET_REQUEST_SORT, walletRequestSort);
        request.setAttribute(WALLET_REQUESTS, walletRequests);
        request.setAttribute(NO_OF_TRANSFER_PAGES, noOfTransferPages);

//        return new ForwardResult(WALLET_REQUESTS_PATH);
        return new RedirectResult("USER_WALLETS_URL");
    }

    private String getWalletRequestSortParameter(HttpServletRequest request) {
        String walletRequestSort;
        try {
            walletRequestSort = ServletUtils.getStringParameter(request, WALLET_REQUEST_SORT);
        } catch (ParameterNotFoundException e) {
            walletRequestSort = DEFAULT_WALLET_REQUEST_SORT;
        }
        return walletRequestSort;
    }

    private int getPage(HttpServletRequest request) {
        int page;
        try {
            page = ServletUtils.getIntParameter(request, PAGE);
        } catch (ParameterNotFoundException e) {
            page = DEFAULT_PAGE;
        }
        return page;
    }
}