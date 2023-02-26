package com.epam.payments.core.service.exeption.user;

import com.epam.payments.core.service.exeption.ServiceException;

public class UserServiceException extends ServiceException {
    public UserServiceException(String message) {
        super(message);
    }

    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
