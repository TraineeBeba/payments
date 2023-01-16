package com.epam.payments.web.command;

import com.epam.payments.utils.Utils;
import com.epam.payments.web.command.result.CommandResult;
import com.epam.payments.web.command.result.RedirectResult;
import com.epam.payments.web.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "RegisterCommand")
public class RegisterCommand extends Command {

    private static final Logger LOG = Logger.getLogger(RegisterCommand.class);
    private UserService userService = new UserService();
    private static final long serialVersionUID = -7190245479634943129L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing RegisterCommand");

        HttpSession session = request.getSession();
        RedirectResult redirect;
        String username = "", password = "";

        if ((request.getParameter("username") != null) && (request.getParameter("password") != null)) {
            username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
            password = new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");
        }


        String registerCheck = userService.checkRegisterUser(username, password);
        if(registerCheck == null) {
            userService.getUserDAO().createUser(username, Utils.encrypt(password));
            session.setAttribute("register", true);
            redirect = new RedirectResult(request.getParameter("redirect"));
        } else {
            session.setAttribute("wrongData", registerCheck);
            redirect = new RedirectResult("?command=goToRegisterCommand");
        }

        return redirect;
    }

}