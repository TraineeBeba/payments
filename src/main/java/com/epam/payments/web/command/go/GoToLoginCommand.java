package com.epam.payments.web.command.go;

import com.epam.payments.Path;
import com.epam.payments.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToLoginCommand extends Command {
    public static final Logger LOG = Logger.getLogger(GoToLoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing GoToLoginCommand");
        return Path.PAGE_LOGIN;

    }
}
