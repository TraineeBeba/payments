package com.epam.payments.web.command;

import com.epam.payments.db.dto.UserDTO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "LoginCommand")
public class LoginCommand extends Command {

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    private static final long serialVersionUID = -7190245479634943129L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing LoginCommand");
        HttpSession session = request.getSession();
        String login = "", password = "";
        if ((request.getParameter("username") != null) && (request.getParameter("password") != null)) {
            login = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
            password = new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");
        } else if ((session.getAttribute("username") != null) && (session.getAttribute("password") != null)) {
            login = String.valueOf(session.getAttribute("username"));
            password = String.valueOf(session.getAttribute("password"));
        }

        String toMove = "/controller?command=" + request.getParameter("goto");

        return toMove;
    }
}