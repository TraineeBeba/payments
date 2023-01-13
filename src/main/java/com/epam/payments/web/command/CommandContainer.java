package com.epam.payments.web.command;

import com.epam.payments.web.command.go.GoToLoginCommand;
import com.epam.payments.web.command.go.GoToRegisterCommand;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

/**
 * Storage all commands
 */
public class CommandContainer {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put("loginCommand", new LoginCommand());
        commands.put("registerCommand", new RegisterCommand());
        commands.put("languageCommand", new LanguageCommand());
        commands.put("goToLoginCommand", new GoToLoginCommand());
        commands.put("goToRegisterCommand", new GoToRegisterCommand());

        LOG.debug("Command container was successfully initialized");
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


