package com.epam.payments.command.navigation.user.wallet;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.ForwardResult;
import com.epam.payments.core.model.dto.UserDTO;
import com.epam.payments.core.model.dto.WalletDTO;
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

public class GoUserWalletsCommand extends Command {
    public static final Logger LOG = Logger.getLogger(GoUserWalletsCommand.class);
    private static final long serialVersionUID = -4327477400419749096L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException {
        LOG.trace("Start tracing GoUserWalletsCommand");
        HttpSession session = request.getSession();

        WalletService walletService = ServletUtils.getService(request, WalletService.class);
        String walletSort = getWalletSort(request);

        UserDTO userDTO = ServletUtils.getAttribute(session, USER_DTO, UserDTO.class);
        List<WalletDTO> wallets = walletService.getSortedListByUserDTO(userDTO, walletSort);

        request.setAttribute(WALLETS, wallets);

        return new ForwardResult(USER_WALLETS_PATH);
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
}