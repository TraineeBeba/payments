package com.epam.payments.command;

import com.epam.payments.command.constant.ParamNames;
import com.epam.payments.command.constant.SortConstants;
import com.epam.payments.command.constant.WebPathConstants;
import com.epam.payments.command.constant.WebUrlConstants;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.exeption.InternalServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public abstract class Command implements Serializable {

    private static final long serialVersionUID = -3044464540876000223L;

    public abstract CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }

}
