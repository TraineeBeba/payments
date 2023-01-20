package com.epam.payments.command;

import com.epam.payments.db.dto.UserDTO;
import com.epam.payments.db.dto.WalletDTO;
import com.epam.payments.db.service.WalletService;
import com.epam.payments.utils.Utils;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.db.service.UserService;
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
    private UserService userService = new UserService();

    private static final long serialVersionUID = -7190245479634943129L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response, String forward) throws IOException, ServletException {
        LOG.trace("Start tracing LoginCommand");
        HttpSession session = request.getSession();
        RedirectResult redirect;
        String username = "", password = "";

        if ((request.getParameter("username") != null) && (request.getParameter("password") != null)) {
            username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
            password = new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");
        }

        LOG.info("USERNAME --> " + username);
        LOG.info("PASSWORD --> " + password);
        LOG.info("ENCRYPT PASSWORD --> " + Utils.encrypt(password));

        String loginCheck = userService.checkLoginUser(username, Utils.encrypt(password));
        if(loginCheck != null) {
            session.setAttribute("wrongData", loginCheck);
            redirect = new RedirectResult("?command=goLoginCommand");
        } else {
            UserDTO userDTO = userService.getUserDAO().getUserByName(username);

            setSessionParametrs(session, username, password, userDTO);

            redirect = new RedirectResult(request.getParameter("redirect"));
        }

        return redirect;
    }

    private void setSessionParametrs(HttpSession session, String username, String password, UserDTO userDTO) {
        session.setAttribute("user_id", userDTO.getId());
        session.setAttribute("role_id", userDTO.getRole_id());
        session.setAttribute("state_id", userDTO.getRole_id());
        session.setAttribute("username", username);
        session.setAttribute("password", password);

    }
}