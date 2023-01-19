package com.epam.payments.db.service;

import com.epam.payments.db.dao.IUserDAO;
import com.epam.payments.db.dao.MySQL.UserDAO;

public class UserService {

    private IUserDAO userDAO = new UserDAO();

    public String checkRegisterUser(String username, String password) {
        if (userDAO.checkExistenceByUsername(username)){
            return "error.login.username_taken";
        }

        if (password.length() < 6){
            return "error.password.few_chars";
        }

        return null;
    }

    public String checkLoginUser(String username, String password) {
        if (!userDAO.checkExistenceByUsernameAndPassword(username, password)){
            return "error.login.wrong_data";
        }

        if (!userDAO.checkUserStatusByName(username)) {
            return "error.login.user_blocked";
        }

        return null;
    }

    public IUserDAO getUserDAO() {
        return userDAO;
    }

}
