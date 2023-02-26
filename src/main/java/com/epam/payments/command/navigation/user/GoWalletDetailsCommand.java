package com.epam.payments.command.navigation.user;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.ForwardResult;
import com.epam.payments.core.model.entity.TransferEntity;
import com.epam.payments.core.model.entity.WalletEntity;
import com.epam.payments.core.service.WalletService;
import com.epam.payments.core.service.impl.WalletServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class GoWalletDetailsCommand extends Command {
    public static final Logger LOG = Logger.getLogger(GoWalletDetailsCommand.class);
    private static final long serialVersionUID = 3612538167226370577L;

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_RECORDS_PER_PAGE = 4;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Start tracing GetCommand");

//        int bill_number = getBillNumberParameter(request);
//        int page = getPageParameter(request);
//        int offset = (page - 1) * DEFAULT_RECORDS_PER_PAGE;
//        String transferSort = getTransferSort(request);
//        WalletEntity currWallet = walletService.findByBill(bill_number);
//
//        List<TransferEntity> walletTransfers = transferService.getSortedListByBill(
//                currWallet.getBill_number(),
//                transferSort,
//                offset,
//                DEFAULT_RECORDS_PER_PAGE
//        );
//        int noOfTransferPages = getNoOfTransferPages();
//
//        setRequestAttributes(request, page, transferSort, walletTransfers, currWallet, noOfTransferPages);

        return new ForwardResult(WALLET_DETAILS_PATH);
    }

    private int getBillNumberParameter(HttpServletRequest request) {
        int bill_number = 0;
        if (request.getParameter(BILL_NUMBER) != null) {
            bill_number = Integer.parseInt(request.getParameter(BILL_NUMBER));
        } else {
            LOG.info("error");
        }

        return bill_number;
    }

//    private int getNoOfTransferPages() {
//        int noOfRecords = transferService.getNoOfRecords();
//
//        return (int) Math.ceil(noOfRecords * 1.0 / DEFAULT_RECORDS_PER_PAGE);
//    }

    private String getTransferSort(HttpServletRequest request) {
        String transferSort = DEFAULT_TRANSFER_SORT;
        if (request.getParameter(TRANSFER_SORT) != null) {
            transferSort = request.getParameter(TRANSFER_SORT);
        }
        LOG.info("Sort is switched to " + transferSort.toUpperCase());

        return transferSort;
    }

    private int getPageParameter(HttpServletRequest request) {
        int page = DEFAULT_PAGE;
        if (request.getParameter(PAGE) != null) {
            page = Integer.parseInt(request.getParameter(PAGE));
        }

        return page;
    }

    private void setRequestAttributes(HttpServletRequest request, int page, String transferSort, List<TransferEntity> walletTransfers, WalletEntity currWallet, int noOfTransferPages) {
        request.setAttribute(PAGE, page);
        request.setAttribute(TRANSFER_SORT, transferSort);
        request.setAttribute(WALLET_TRANSFERS, walletTransfers);
        request.setAttribute(CURR_WALLET, currWallet);
        request.setAttribute(NO_OF_TRANSFER_PAGES, noOfTransferPages);

        LOG.info("currWallet " + currWallet);
        LOG.info("transferSort " + transferSort);
        LOG.info("currTransferPage " + page);
        LOG.info("SORT BY -->" + transferSort);
    }
}




