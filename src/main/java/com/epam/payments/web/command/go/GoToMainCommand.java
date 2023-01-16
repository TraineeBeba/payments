package com.epam.payments.web.command.go;

import com.epam.payments.Path;
import com.epam.payments.web.command.Command;
import com.epam.payments.web.command.result.CommandResult;
import com.epam.payments.web.command.result.ForwardResult;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToMainCommand extends Command {
    public static final Logger LOG = Logger.getLogger(GoToMainCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing GoToMainCommand");
        return new ForwardResult(Path.PAGE_MAIN);
    }
}
