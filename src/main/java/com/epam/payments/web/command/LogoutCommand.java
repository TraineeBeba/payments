package com.epam.payments.web.command;

import com.epam.payments.db.dto.UserDTO;
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


@WebServlet(name = "LoginCommand")
public class LogoutCommand extends Command {

    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);
    private static final long serialVersionUID = -7190245479634943129L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing LogoutCommand");

        HttpSession session = request.getSession();
        session.removeAttribute("id");
        session.removeAttribute("role_id");
        session.removeAttribute("state_id");
        session.removeAttribute("username");
        session.removeAttribute("password");
        session.invalidate();

        return new RedirectResult(request.getParameter("redirect"));
    }


}