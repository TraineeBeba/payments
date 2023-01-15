package com.epam.payments.web.command;

import com.epam.payments.Path;
import com.epam.payments.db.dao.MySQL.UserDAO;
import com.epam.payments.db.dto.UserDTO;
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

    private static final long serialVersionUID = -7190245479634943129L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing RegisterCommand");

        HttpSession session = request.getSession();
        String username = "", password = "";
//        if ((request.getParameter("username") != null) && (request.getParameter("password") != null)) {
//            login = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
//            password = new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");
//        } else if ((session.getAttribute("username") != null) && (session.getAttribute("password") != null)) {
//            login = String.valueOf(session.getAttribute("username"));
//            password = String.valueOf(session.getAttribute("password"));
//        }

        if ((request.getParameter("username") != null) && (request.getParameter("password") != null)) {
            username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
            password = new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");
        }

        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService();

        UserDTO userDTO = new UserDTO(username, password);
        String registerCheck = userService.registerNewAccount(userDTO);

        if(registerCheck == null) {
            userDAO.createUser(username, password);
        } else {
            request.setAttribute("errorMsg", registerCheck);
        }

        String toMove = "/controller?command="
                + request.getParameter("goto");

        return toMove;
    }

}