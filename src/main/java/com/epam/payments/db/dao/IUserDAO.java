package com.epam.payments.db.dao;

import com.epam.payments.db.dto.UserDTO;

import java.util.List;

public interface IUserDAO {
    List<UserDTO> getAllUsers();
}
