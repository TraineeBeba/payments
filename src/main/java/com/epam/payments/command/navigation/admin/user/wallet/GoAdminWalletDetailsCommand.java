package com.epam.payments.command.navigation.admin.user.wallet;

import com.epam.payments.command.Command;
import com.epam.payments.command.navigation.user.wallet.GoWalletDetailsCommand;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.ForwardResult;
import com.epam.payments.core.model.dto.TransferDTO;
import com.epam.payments.core.model.dto.WalletDTO;
import com.epam.payments.core.model.mapper.WalletMapper;
import com.epam.payments.core.service.transfer.TransferService;
import com.epam.payments.core.service.user.UserService;
import com.epam.payments.core.service.wallet.exception.WalletNotFoundException;
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
import static com.epam.payments.command.constant.WebPathConstants.*;


public class GoAdminWalletDetailsCommand extends Command {
    public static final Logger LOG = Logger.getLogger(GoWalletDetailsCommand.class);
    private static final long serialVersionUID = 3612538167226370577L;

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_RECORDS_PER_PAGE = 4;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException {
        LOG.trace("Start tracing GetCommand");

        HttpSession session = request.getSession();
        ForwardResult forwardResult;
        TransferService transferService = ServletUtils.getService(request, TransferService.class);
        WalletService walletService = ServletUtils.getService(request, WalletService.class);
        UserService userService = ServletUtils.getService(request, UserService.class);
        String username = ServletUtils.getStringParameter(request, SELECTED_USER_NAME);
        int bill_number = ServletUtils.getIntParameter(request, BILL_NUMBER);

        try {
            WalletDTO currWallet =  WalletMapper.INSTANCE.toDTO(walletService.getWalletByBill(bill_number));
            int page = getPage(request);
            String transferSort = getTransferSort(request);
            int offset = (page - 1) * DEFAULT_RECORDS_PER_PAGE;

            List<TransferDTO> walletTransfers = transferService.getSortedTransfersByBill(
                    bill_number,
                    transferSort,
                    offset,
                    DEFAULT_RECORDS_PER_PAGE
            );
            int noOfTransferPages = (int) Math.ceil(transferService.getNoOfRecords() * 1.0 / DEFAULT_RECORDS_PER_PAGE);

            request.setAttribute(SELECTED_USER_NAME, username);
            request.setAttribute(PAGE, page);
            request.setAttribute(TRANSFER_SORT, transferSort);
            request.setAttribute(WALLET_TRANSFERS, walletTransfers);
            request.setAttribute(CURR_WALLET, currWallet);
            request.setAttribute(NO_OF_TRANSFER_PAGES, noOfTransferPages);

            LOG.info("currWallet " + currWallet);
            LOG.info("transferSort " + transferSort);
            LOG.info("currTransferPage " + page);

            forwardResult = new ForwardResult(ADMIN_USER_WALLET_DETAILS_PATH);
        } catch (WalletNotFoundException e) {
            session.setAttribute(WRONG_DATA, e.getMessage());
            forwardResult = new ForwardResult(ADMIN_USER_WALLETS_PATH);
        }

        return forwardResult;
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

    private String getTransferSort(HttpServletRequest request) {
        String transferSort;
        try {
            transferSort = ServletUtils.getStringParameter(request, TRANSFER_SORT);
        } catch (ParameterNotFoundException e) {
            transferSort = DEFAULT_TRANSFER_SORT;
        }
        return transferSort;
    }
}