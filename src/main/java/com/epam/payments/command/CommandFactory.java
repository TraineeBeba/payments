package com.epam.payments.command;

import com.epam.payments.command.common.authentication.LoginCommand;
import com.epam.payments.command.common.authentication.LogoutCommand;
import com.epam.payments.command.common.authentication.RegisterCommand;
import com.epam.payments.command.common.user.transfer.CancelTransferCommand;
import com.epam.payments.command.common.user.transfer.PrepareTransferCommand;
import com.epam.payments.command.common.user.transfer.SendTransferCommand;
import com.epam.payments.command.common.user.wallet.BlockWalletCommand;
import com.epam.payments.command.common.user.wallet.CreateWalletCommand;
import com.epam.payments.command.common.user.wallet.TopUpBalanceCommand;
import com.epam.payments.command.common.user.wallet.UnblockWalletCommand;
import com.epam.payments.command.navigation.admin.GoAdminUsersCommand;
import com.epam.payments.command.navigation.authentication.GoLoginCommand;
import com.epam.payments.command.navigation.authentication.GoRegisterCommand;
import com.epam.payments.command.i18n.LanguageCommand;
import com.epam.payments.command.navigation.user.transfer.GoPrepareTransferCommand;
import com.epam.payments.command.navigation.user.transfer.GoSendTransferCommand;
import com.epam.payments.command.navigation.user.wallet.GoCreateWalletCommand;
import com.epam.payments.command.navigation.user.wallet.GoTopUpBalanceCommand;
import com.epam.payments.command.navigation.user.wallet.GoUserWalletsCommand;
import com.epam.payments.command.navigation.user.wallet.GoWalletDetailsCommand;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

import static com.epam.payments.command.constant.CommandNames.*;

/**
 * Storage all commands
 */
public class CommandFactory {

    private static final Logger LOG = Logger.getLogger(CommandFactory.class);

    private static Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put(LANGUAGE, new LanguageCommand());
        commands.put(LOGIN, new LoginCommand());
        commands.put(REGISTER, new RegisterCommand());
        commands.put(LOGOUT, new LogoutCommand());
        commands.put(TOP_UP_BALANCE, new TopUpBalanceCommand());
        commands.put(CREATE_WALLET, new CreateWalletCommand());
        commands.put(BLOCK_WALLET, new BlockWalletCommand());
        commands.put(UNBLOCK_WALLET, new UnblockWalletCommand());
        commands.put(PREPARE_TRANSFER, new PrepareTransferCommand());
        commands.put(SEND_TRANSFER, new SendTransferCommand());
        commands.put(CANCEL_TRANSFER, new CancelTransferCommand());

        commands.put(GO_LOGIN_PAGE, new GoLoginCommand());
        commands.put(GO_REGISTER_PAGE, new GoRegisterCommand());
        commands.put(GO_USER_WALLETS_PAGE, new GoUserWalletsCommand());
        commands.put(GO_TOP_UP_BALANCE_PAGE, new GoTopUpBalanceCommand());
        commands.put(GO_WALLET_DETAILS_PAGE, new GoWalletDetailsCommand());
        commands.put(GO_CREATE_WALLET_PAGE, new GoCreateWalletCommand());
        commands.put(GO_PREPARE_TRANSFER_PAGE, new GoPrepareTransferCommand());
        commands.put(GO_SEND_TRANSFER_PAGE, new GoSendTransferCommand());
        commands.put(GO_ADMIN_USERS_PAGE, new GoAdminUsersCommand());

        LOG.debug("Command factory was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return null;
        }
        return commands.get(commandName);
    }
}


