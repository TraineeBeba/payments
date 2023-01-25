package com.epam.payments.command;

import com.epam.payments.utils.Utils;
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
        Properties p = Utils.getNameProperties();

        commands.put(p.getProperty("command.login"), new LoginCommand());
        commands.put(p.getProperty("command.register"), new RegisterCommand());
        commands.put(p.getProperty("command.language"), new LanguageCommand());
        commands.put(p.getProperty("command.logout"), new LogoutCommand());
        commands.put(p.getProperty("command.create-wallet"), new CreateWalletCommand());
        commands.put(p.getProperty("command.prepare-transfer"), new PrepareTransferCommand());
        commands.put(p.getProperty("command.send-transfer"), new SendTransferCommand());
        commands.put(p.getProperty("command.cancel-transfer"), new CancelTransferCommand());
        commands.put(p.getProperty("command.sort-wallets"), new SortWalletsCommand());
        commands.put(p.getProperty("command.sort-transfers"), new SortTransfersCommand());
        commands.put(p.getProperty("command.go"), new GoCommand());

        LOG.debug("Command factory was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }
}


