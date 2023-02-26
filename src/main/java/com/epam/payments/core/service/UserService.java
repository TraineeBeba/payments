package com.epam.payments.core.service;

import com.epam.payments.core.service.exeption.user.AuthenticationException;
import com.epam.payments.core.service.exeption.user.RegistrationException;
import com.epam.payments.core.service.exeption.user.UserNotFoundException;
import com.epam.payments.core.service.exeption.user.UserValidationException;
import com.epam.payments.core.model.entity.UserEntity;

public interface UserService extends Service{
    UserEntity authenticateUser(String username, String password) throws AuthenticationException, UserValidationException;
    void registerUser(String username, String password) throws RegistrationException, UserValidationException;
    void blockUser(Long userId) throws UserNotFoundException;
    void unblockUser(Long userId) throws UserNotFoundException;
}
