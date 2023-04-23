package com.epam.payments.filter;

import com.epam.payments.command.constant.ParamNames;
import com.epam.payments.command.i18n.constant.Locale;
import com.epam.payments.core.utils.ServletUtils;
import com.epam.payments.core.utils.exeption.AttributeNotFoundException;
import com.epam.payments.core.utils.exeption.ParameterNotFoundException;
import com.epam.payments.exeption.InternalServerException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionLocaleFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String language;
        try {
            language = ServletUtils.getAttribute(session, ParamNames.LANGUAGE, String.class);
        } catch (AttributeNotFoundException e) {
            language = Locale.UK;
        }

        session.setAttribute(ParamNames.LANGUAGE, language);

        chain.doFilter(request, response);

    }

    public void destroy() {}

    public void init(FilterConfig arg0) throws ServletException {}

}