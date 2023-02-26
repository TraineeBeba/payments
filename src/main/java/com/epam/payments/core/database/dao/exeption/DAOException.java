package com.epam.payments.core.database.dao.exeption;

import com.epam.payments.core.database.dao.exeption.constant.DAOErrorMessages;

public class DAOException extends Exception implements DAOErrorMessages {
    private static final long serialVersionUID = 8482970915084041351L;

    public DAOException(String message) {
        super(PROCESSING_METHOD_ERROR + message);
    }

    public DAOException(String message, Throwable cause) {
        super(PROCESSING_METHOD_ERROR + message, cause);
    }
}