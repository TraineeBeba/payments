package com.epam.payments.core.utils;

import com.epam.payments.appcontext.impl.AppContext;
import com.epam.payments.core.utils.exeption.AttributeNotFoundException;
import com.epam.payments.core.utils.exeption.ParameterNotFoundException;
import com.epam.payments.core.service.Service;
import com.epam.payments.exeption.InternalServerException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.function.Function;

import static com.epam.payments.command.constant.ParameterNames.APP_CONTEXT;

public class ServletUtils {
    public static <T> T getParameter(HttpServletRequest request, String paramName, Function<String, T> parser) throws ParameterNotFoundException {
        return Optional.ofNullable(request.getParameter(paramName))
                .filter(paramValue -> !paramValue.trim().isEmpty())
                .map(paramValue -> parser.apply(paramValue.trim()))
                .orElseThrow(() -> new ParameterNotFoundException(paramName));
    }

    public static String getStringParameter(HttpServletRequest request, String paramName) throws ParameterNotFoundException {
        return getParameter(request, paramName, Function.identity());
    }

    public static int getIntParameter(HttpServletRequest request, String paramName) throws ParameterNotFoundException {
        return getParameter(request, paramName, Integer::parseInt);
    }

    public static long getLongParameter(HttpServletRequest request, String paramName) throws ParameterNotFoundException {
        return getParameter(request, paramName, Long::parseLong);
    }

    public static boolean getBooleanParameter(HttpServletRequest request, String paramName) throws ParameterNotFoundException {
        return getParameter(request, paramName, Boolean::parseBoolean);
    }

    public static <T> T getAttribute(HttpSession session, String attrName, Function<Object, T> parser) throws InternalServerException {
        return Optional.ofNullable(session.getAttribute(attrName))
                .map(parser)
                .orElseThrow(() -> new AttributeNotFoundException(attrName));
    }

    public static <T> T getAttribute(ServletContext servletContext, String attributeName, Class<T> attributeClass) throws InternalServerException {
        Object attributeValue = servletContext.getAttribute(attributeName);
        if (!attributeClass.isInstance(attributeValue)) {
            throw new AttributeNotFoundException(attributeName);
        }
        return attributeClass.cast(attributeValue);
    }

    public static <T extends Service> T getService(HttpServletRequest request, Class<T> serviceClass) throws InternalServerException {
        ServletContext servletContext = request.getServletContext();
        AppContext appContext = ServletUtils.getAttribute(servletContext, APP_CONTEXT, AppContext.class);
        return appContext.getService(serviceClass);
    }
}