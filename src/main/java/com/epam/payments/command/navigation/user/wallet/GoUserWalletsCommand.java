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
        String walletSort;
        try {
            walletSort = ServletUtils.getStringParameter(request, WALLET_SORT);
        } catch (ParameterNotFoundException e) {
            walletSort = DEFAULT_WALLET_SORT;
        }

        UserDTO userDTO = ServletUtils.getAttribute(session, USER_DTO, UserDTO.class);
        List<WalletDTO> wallets = walletService.getSortedListByUserDTO(userDTO, walletSort);

        request.setAttribute(WALLETS, wallets);
        session.setAttribute(WALLET_SORT, walletSort);

        return new ForwardResult(USER_WALLETS_PATH);
    }

//    private UserEntity getUserEntity(HttpServletRequest request, HttpSession session) {
//        UserEntity userEntity;
//        if(request.getParameter(SELECTED_USER_NAME) != null) {
//            String userName = request.getParameter(SELECTED_USER_NAME);
////            userEntity = userService.findByUsername(userName);
//
////            session.setAttribute(SELECTED_USER, userEntity);
//        } else if (session.getAttribute(SELECTED_USER) != null){
//            userEntity = (UserEntity) session.getAttribute(SELECTED_USER);
//        } else {
//            userEntity = (UserEntity) session.getAttribute(USER_ENTITY);
//        }
//
//        return userEntity;
//    }
}