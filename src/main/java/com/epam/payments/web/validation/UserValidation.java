package com.epam.payments.web.validation;

import com.epam.payments.db.dao.IUserDAO;
import com.epam.payments.db.dao.MySQL.UserDAO;
import com.epam.payments.db.dto.UserDTO;

import java.util.Collections;

public class UserValidation {

    private IUserDAO userDAO = new UserDAO();

    public String registerNewAccount(UserDTO user) {
        if (userDAO.findByUsername(user.getUsername()) != null){
            return "Користувач з логіном " + user.getUsername() + " вже існує";
        }

        if (user.getPassword().length() < 6){
            return "Менше 6 символів в паролі";
        }

        userDAO.createUser(user.getUsername(), user.getPassword());
        return null;
    }
}
