package com.epam.payments;

import com.epam.payments.command.Command;
import com.epam.payments.command.CommandFactory;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.ForwardResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.command.result.View;
import com.epam.payments.core.utils.ServletUtils;
import com.epam.payments.exeption.InternalServerException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.epam.payments.command.constant.ParamNames.COMMAND;

public class Controller extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Controller.class);

    private static Map<Class<?>, View> views = new HashMap<>();

    static {
        views.put(ForwardResult.class, (commandResult, request, response) -> {
            LOG.trace("Forward address --> " + commandResult.getResource());
            LOG.debug("Controller finished, now navigation to forward address --> " + commandResult.getResource());

            request.getRequestDispatcher(commandResult.getResource()).forward(request, response);

        });
        views.put(RedirectResult.class, (commandResult, request, response) -> {
            LOG.trace("Redirect address --> " + commandResult.getResource());
            LOG.debug("Controller finished, now redirect to address --> " + commandResult.getResource());

            response.sendRedirect(/*request.getContextPath() + */commandResult.getResource());
        });

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        LOG.trace("*****************POST*****************");
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        LOG.trace("*****************GET*****************");
        process(request, response);
    }

    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        try {
            LOG.debug("Controller starts");

            String commandName = ServletUtils.getStringParameter(request, COMMAND);
            LOG.trace("Request parameter: command --> " + commandName);

            Command command = CommandFactory.get(commandName);
            LOG.trace("Obtained command --> " + command);

            CommandResult commandResult = command.execute(request, response);
            views.get(commandResult.getClass()).render(commandResult, request, response);
        } catch (InternalServerException ex) {
            throw new ServletException(ex.getMessage(), ex);
        }
    }
}