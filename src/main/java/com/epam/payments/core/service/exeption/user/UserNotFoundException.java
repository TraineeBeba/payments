package com.epam.payments.core.service.exeption.user;

import com.epam.payments.core.service.exeption.ServiceException;

public class UserNotFoundException extends ServiceException {
    private static final long serialVersionUID = -3508822186508513744L;

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
