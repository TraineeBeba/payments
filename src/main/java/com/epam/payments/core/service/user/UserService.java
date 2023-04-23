package com.epam.payments.core.service.user;

import com.epam.payments.core.model.dto.UserDTO;
import com.epam.payments.core.model.entity.UserEntity;
import com.epam.payments.core.service.Service;
import com.epam.payments.core.service.user.exception.AuthenticationException;
import com.epam.payments.core.service.user.exception.RegistrationException;
import com.epam.payments.core.service.user.exception.UserNotFoundException;
import com.epam.payments.core.service.user.exception.UserValidationException;

import java.util.List;

public interface UserService extends Service {
    UserDTO authenticateUser(String username, String password) throws AuthenticationException, UserValidationException;
    void registerUser(String username, String password) throws RegistrationException, UserValidationException;
    void blockUser(String username) throws UserNotFoundException;
    void unblockUser(String username) throws UserNotFoundException;
    List<UserDTO> getSortedList(String userSort, int offset, int recordsPerPage);

    UserDTO findByUsername(String username) throws UserNotFoundException;

    int getNoOfRecords();
}
