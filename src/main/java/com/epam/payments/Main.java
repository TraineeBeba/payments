package com.epam.payments;

import com.epam.payments.db.dao.ConnectionPool;
import com.epam.payments.db.dao.MySQL.UserDAO;
import com.epam.payments.db.dto.UserDTO;
import org.apache.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println(get());
    }

    public static String get (){
        LOG.info("Start tracing \"get\" method");

        UserDAO userDAO = new UserDAO();
        List<UserDTO> users= userDAO.getAllUsers();

        String s= "";
        for (UserDTO userDTO : users) {
            s += userDTO.toString();
        }

        return s;
    }
}
