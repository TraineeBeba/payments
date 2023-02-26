package com.epam.payments.command;

import com.epam.payments.command.common.user.*;
import com.epam.payments.command.common.wallet.*;
import com.epam.payments.command.navigation.admin.GoAdminUsersCommand;
import com.epam.payments.command.navigation.admin.GoWalletRequestsCommand;
import com.epam.payments.command.navigation.security.GoLoginCommand;
import com.epam.payments.command.navigation.security.GoRegisterCommand;
import com.epam.payments.command.navigation.user.*;
import com.epam.payments.command.i18n.LanguageCommand;
import com.epam.payments.command.common.transfer.CancelTransferCommand;
import com.epam.payments.command.common.transfer.PrepareTransferCommand;
import com.epam.payments.command.common.transfer.SendTransferCommand;
import com.epam.payments.core.utils.Utils;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * Storage all commands
 */
public class CommandFactory {

    private static final Logger LOG = Logger.getLogger(CommandFactory.class);

    private static Map<String, Command> commands = new TreeMap<>();

    static {
        Properties p = Utils.getNamespaceProperties();

        commands.put(p.getProperty("command.login"), new LoginCommand());
        commands.put(p.getProperty("command.register"), new RegisterCommand());
        commands.put(p.getProperty("command.language"), new LanguageCommand());
        commands.put(p.getProperty("command.logout"), new LogoutCommand());
        commands.put(p.getProperty("command.create-wallet"), new CreateWalletCommand());
        commands.put(p.getProperty("command.prepare-transfer"), new PrepareTransferCommand());
        commands.put(p.getProperty("command.send-transfer"), new SendTransferCommand());
        commands.put(p.getProperty("command.cancel-transfer"), new CancelTransferCommand());
        commands.put(p.getProperty("command.block-wallet"), new BlockWalletCommand());
        commands.put(p.getProperty("command.unblock-wallet"), new UnblockWalletCommand());
        commands.put(p.getProperty("command.unblock-user"), new UnblockUserCommand());
        commands.put(p.getProperty("command.block-user"), new BlockUserCommand());
        commands.put(p.getProperty("command.top-up-balance"), new TopUpBalanceCommand());
        commands.put(p.getProperty("command.wallet-request"), new WalletRequestCommand());
        commands.put(p.getProperty("command.answer-wallet-request"), new AnswerWalletRequestCommand());

        commands.put(p.getProperty("command.go.admin-users"), new GoAdminUsersCommand());
        commands.put(p.getProperty("command.go.login"), new GoLoginCommand());
        commands.put(p.getProperty("command.go.register"), new GoRegisterCommand());
        commands.put(p.getProperty("command.go.create-wallet"), new GoCreateWalletCommand());
        commands.put(p.getProperty("command.go.prepare-transfer"), new GoPrepareTransferCommand());
        commands.put(p.getProperty("command.go.send-transfer"), new GoSendTransferCommand());
        commands.put(p.getProperty("command.go.user-wallets"), new GoUserWalletsCommand());
        commands.put(p.getProperty("command.go.wallet-details"), new GoWalletDetailsCommand());
        commands.put(p.getProperty("command.go.top-up-balance"), new GoTopUpBalanceCommand());
        commands.put(p.getProperty("command.go.wallet-requests"), new GoWalletRequestsCommand());

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


