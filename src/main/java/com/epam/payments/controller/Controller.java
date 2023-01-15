package com.epam.payments.controller;

import com.epam.payments.Path;
import com.epam.payments.web.command.Command;
import com.epam.payments.web.command.factory.CommandFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebServlet(name = "Controller")
public class Controller extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Controller.class);

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

        String toMove = Path.PAGE_ERROR_PAGE;
        try {
            toMove = command.execute(request, response);
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
        }

        if(method.equals("post")) {
            LOG.trace("Redirect address --> " + toMove);
            LOG.debug("Controller finished, now redirect to address --> " + toMove);

            response.sendRedirect(toMove);
        } else if (method.equals("get")) {
            HttpSession session = request.getSession();
            LOG.trace("SESSION PARAMETR --> " + session.getAttribute("errorMsg"));

            LOG.trace("Forward address --> " + toMove);
            LOG.debug("Controller finished, now go to forward address --> " + toMove);

            request.getRequestDispatcher(toMove).forward(request, response);
        } else {
            LOG.trace("FUCK!!!");
        }

    }
}
