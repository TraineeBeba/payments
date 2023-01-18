package com.epam.payments.controller;

import com.epam.payments.Path;
import com.epam.payments.web.command.Command;
import com.epam.payments.web.command.factory.CommandFactory;
import com.epam.payments.web.command.result.CommandResult;
import com.epam.payments.web.command.result.ForwardResult;
import com.epam.payments.web.command.result.RedirectResult;
import com.epam.payments.web.command.result.View;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "Controller")
public class Controller extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Controller.class);

    private static Map<Class<?>, View> views = new HashMap<>();

    static {
        views.put(ForwardResult.class, (commandResult, request, response) -> {
            LOG.trace("Forward address --> " + commandResult.getResource());
            LOG.debug("Controller finished, now go to forward address --> " + commandResult.getResource());

            request.getRequestDispatcher(commandResult.getResource()).forward(request, response);

        });
        views.put(RedirectResult.class, (commandResult, request, response) -> {
            LOG.trace("Redirect address --> " + commandResult.getResource());
            LOG.debug("Controller finished, now redirect to address --> " + commandResult.getResource());

            response.sendRedirect(request.getContextPath() + commandResult.getResource());
        });

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.trace("*****************POST*****************");
        process(request, response, "post");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.trace("*****************GET*****************");
        process(request, response, "get");
    }

    private void process(HttpServletRequest request,
                         HttpServletResponse response, String method) throws IOException, ServletException {

        LOG.debug("Controller starts");

        String commandName = request.getParameter("command");
        LOG.trace("Request parameter: command --> " + commandName);

        Command command = CommandFactory.get(commandName);
        LOG.trace("Obtained command --> " + command);

        CommandResult commandResult = new ForwardResult("/controller?command=goToErrorCommand");
        try {
            commandResult = command.execute(request, response);
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
        }

        views.get(commandResult.getClass()).render(commandResult, request, response);

    }
}
