package com.epam.payments.command;

import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.ForwardResult;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoCommand extends Command {
    public static final Logger LOG = Logger.getLogger(GoCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response, String forward) throws IOException, ServletException {
        LOG.trace("Start tracing GetCommand");
        return new ForwardResult("/WEB-INF/jsp/"+forward+".jsp");

    }
}