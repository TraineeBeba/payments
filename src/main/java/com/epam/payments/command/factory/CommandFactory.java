package com.epam.payments.command.factory;

import com.epam.payments.command.*;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

/**
 * Storage all commands
 */
public class CommandFactory {

    private static final Logger LOG = Logger.getLogger(CommandFactory.class);

    private static Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put("loginCommand", new LoginCommand());
        commands.put("registerCommand", new RegisterCommand());
        commands.put("languageCommand", new LanguageCommand());
        commands.put("logoutCommand", new LogoutCommand());
        commands.put("createWalletCommand", new CreateWalletCommand());
        commands.put("goCommand", new GoCommand());

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


