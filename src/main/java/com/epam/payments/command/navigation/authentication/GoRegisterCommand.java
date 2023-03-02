package com.epam.payments.command.navigation.authentication;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.ForwardResult;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.payments.command.constant.WebPathConstants.REGISTER_PATH;


public class GoRegisterCommand extends Command  {
    public static final Logger LOG = Logger.getLogger(GoRegisterCommand.class);
    private static final long serialVersionUID = -2722240160869483558L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Start tracing GetCommand");

        return new ForwardResult(REGISTER_PATH);
    }
}