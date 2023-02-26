package com.epam.payments.appcontext.exeption;

import com.epam.payments.appcontext.exeption.constant.AppContextErrorMessages;
import com.epam.payments.exeption.InternalServerException;

public class GetAppContextException extends InternalServerException implements AppContextErrorMessages {
    public GetAppContextException(String message) {
        super(GET_APP_CONTEXT_ERROR + message);
    }

    public GetAppContextException(String message, Throwable cause) {
        super(GET_APP_CONTEXT_ERROR + message, cause);
    }
}
