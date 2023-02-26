package com.epam.payments.command.navigation.security;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.ForwardResult;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class GoLoginCommand extends Command {
    public static final Logger LOG = Logger.getLogger(GoLoginCommand.class);
    private static final long serialVersionUID = 4879456814775448786L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        LOG.trace("Start tracing GetCommand");

        return new ForwardResult(LOGIN_PATH);
    }
}