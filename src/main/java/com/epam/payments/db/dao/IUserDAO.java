package com.epam.payments.db.dao;

import com.epam.payments.db.dto.UserDTO;

import java.util.List;

public interface IUserDAO {
    List<UserDTO> getAllUsers();
    UserDTO createUser(String username, String password);
    boolean checkExistenceByUsername(String username);
    boolean checkExistenceByUsernameAndPassword(String username, String password);

    UserDTO getUserByName(String username);

    boolean checkUserStatusByName(String username);
}
