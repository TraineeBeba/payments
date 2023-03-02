package com.epam.payments.command.navigation.authentication;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.ForwardResult;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.payments.command.constant.WebPathConstants.LOGIN_PATH;


public class GoLoginCommand extends Command {
    public static final Logger LOG = Logger.getLogger(GoLoginCommand.class);
    private static final long serialVersionUID = 4879456814775448786L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        LOG.trace("Start tracing GetCommand");

        return new ForwardResult(LOGIN_PATH);
    }
}