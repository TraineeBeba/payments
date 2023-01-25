package com.epam.payments.db.service;

import com.epam.payments.db.dao.IUserDAO;
import com.epam.payments.db.dao.MySQL.UserDAO;

public class UserService {

    private IUserDAO userDAO = new UserDAO();

    public String checkRegisterUser(String username, String password) {
        if (!username.equals(username.trim()) || !password.equals(password.trim())
                || username.isBlank()
                || password.isBlank()){
            return "alertError.whitespaces";
        }

        if (userDAO.checkExistenceByUsername(username)){
            return "alertError.username_taken";
        }

        if (password.length() < 6){
            return "alertError.few_chars_in_password";
        }

        if (password.length() > 16){
            return "alertError.many_chars_in_password";
        }

        return null;
    }

    public String checkLoginUser(String username, String password) {
        if (!username.equals(username.trim()) || !password.equals(password.trim())
                || username.isBlank()
                || password.isBlank()){
            return "alertError.whitespaces";
        }

        if (!userDAO.checkExistenceByUsernameAndPassword(username, password)){
            return "alertError.wrong_login_or_password";
        }

        if (!userDAO.checkUserStatusByName(username)) {
            return "alertError.user_blocked";
        }

        return null;
    }

    public IUserDAO getUserDAO() {
        return userDAO;
    }

}
