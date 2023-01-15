package com.epam.payments.web.service;

import com.epam.payments.db.dao.IUserDAO;
import com.epam.payments.db.dao.MySQL.UserDAO;
import com.epam.payments.db.dto.UserDTO;

public class UserService {

    private IUserDAO userDAO = new UserDAO();

    public String registerNewAccount(UserDTO user) {
        if (userDAO.checkExistenceByUsername(user.getUsername())){
            return "error.login.username_taken";
        }

        if (user.getPassword().length() < 6){
            return "error.password.few_chars";
        }

        userDAO.createUser(user.getUsername(), user.getPassword());
        return null;
    }
}
