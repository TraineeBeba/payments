package com.epam.payments.web.command;

import com.epam.payments.Path;
import com.epam.payments.db.dto.UserDTO;
import com.epam.payments.exception.Errors;
import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp2.Utils;

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
//        if ((request.getParameter("username") != null) && (request.getParameter("password") != null)) {
//            login = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
//            password = new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");
//        } else if ((session.getAttribute("username") != null) && (session.getAttribute("password") != null)) {
//            login = String.valueOf(session.getAttribute("username"));
//            password = String.valueOf(session.getAttribute("password"));
//        }
//
//        if ((!login.isEmpty()) && (!password.isEmpty())) {
//            user = new MySQLUserDAO().findUserByLogin(login);
//            if (user != null) {
//                if (user.getPassword().equals(Utils.encrypt(password))) {
//                    switch (user.getRoleId()) {
//                        case 0:
//                            role = "admin";
//                            break;
//                        case 1:
//                            role = "student";
//                            break;
//                        case 2:
//                            role = "lecturer";
//                            break;
//                    }
//                    String stateId = String.valueOf(user.getStateId());
//                    request.setAttribute("username", login);
//                    request.setAttribute("role", role);
//                    request.setAttribute("password", password);
//                    session.setAttribute("state", stateId);
//                    session.setAttribute("username", login);
//                    session.setAttribute("role", role);
//                    session.setAttribute("password", password);
//                } else {
//                    request.setAttribute("errorMessage", Errors.ERR_INVALID_PASSWORD);
//                    return Path.PAGE_ERROR_PAGE;
//                }
//            } else {
//                request.setAttribute("errorMessage", Errors.ERR_CANNOT_FIND_USER_NAME);
//                return Path.PAGE_ERROR_PAGE;
//            }
//            session.setAttribute("user", user);
//            if (user.getStateId() == 0) {
//                request.setAttribute("errorMessage", Errors.ERR_LOCKED);
//                return Path.PAGE_ERROR_PAGE;
//            }
//        }
//        if (role != null) {
//            switch (role) {
//                case "lecturer":
//                    session.setAttribute("updatecourseid", -1);
//                    forward = Path.PAGE_LECTURER;
//                    break;
//
//                case "admin":
//                    forward = Path.PAGE_ADMIN;
//                    String page = new MySQLAdminDAO().selectAvgMark();
//                    String pageTop = new MySQLAdminDAO().selectTopMark();
//                    request.setAttribute("list",page);
//                    request.setAttribute("listTop", pageTop);
//                    //System.out.println(list);
//                    break;
//            }
//        }

        String toMove = "/controller?command=" + request.getParameter("goto");

        return toMove;
    }
}