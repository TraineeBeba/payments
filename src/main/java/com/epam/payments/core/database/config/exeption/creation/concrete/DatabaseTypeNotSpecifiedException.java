package com.epam.payments.core.database.config.exeption.creation.concrete;

import com.epam.payments.core.database.config.exeption.creation.DatabaseConfigCreationException;

public class DatabaseTypeNotSpecifiedException extends DatabaseConfigCreationException  {
    public DatabaseTypeNotSpecifiedException (){
        super(DATABASE_TYPE_NOT_SPECIFIED);
    }

    public DatabaseTypeNotSpecifiedException(String message) {
        super(DATABASE_TYPE_NOT_SPECIFIED + message);
    }

    public DatabaseTypeNotSpecifiedException(String message, Throwable cause) {
        super(DATABASE_TYPE_NOT_SPECIFIED + message, cause);
    }
}