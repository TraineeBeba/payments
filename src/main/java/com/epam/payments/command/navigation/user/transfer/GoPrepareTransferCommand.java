package com.epam.payments.command.navigation.user.transfer;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.ForwardResult;
import com.epam.payments.core.model.dto.UserDTO;
import com.epam.payments.core.model.dto.WalletDTO;
import com.epam.payments.core.model.enums.state.WalletState;
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
import static com.epam.payments.command.constant.WebPathConstants.USER_PREPARE_TRANSFER_PATH;


public class GoPrepareTransferCommand extends Command {
    public static final Logger LOG = Logger.getLogger(GoPrepareTransferCommand.class);
    private static final long serialVersionUID = 1821296870058989837L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException {
        LOG.trace("Start tracing GoPrepareTransferCommand");

        WalletService walletService = ServletUtils.getService(request, WalletService.class);
        HttpSession session = request.getSession();
        String walletSort;
        try {
            walletSort = ServletUtils.getStringParameter(request, WALLET_SORT);
        } catch (ParameterNotFoundException e) {
            walletSort = DEFAULT_WALLET_SORT;
        }
        UserDTO userDTO = ServletUtils.getAttribute(session, USER_DTO, UserDTO.class);

        List<WalletDTO> wallets = walletService.getSortedListByUserDTOAndState(userDTO, WalletState.UNBLOCKED, walletSort);
        request.setAttribute(WALLETS, wallets);

        return new ForwardResult(USER_PREPARE_TRANSFER_PATH);
    }
}
