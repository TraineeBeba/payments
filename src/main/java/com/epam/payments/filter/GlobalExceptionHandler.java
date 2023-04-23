package com.epam.payments.filter;

import com.epam.payments.command.navigation.user.wallet.GoCreateWalletCommand;
import com.epam.payments.core.database.dao.exeption.DAOException;
import com.epam.payments.exeption.InternalServerException;
import org.apache.log4j.Logger;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

import static com.epam.payments.command.constant.WebPathConstants.ERROR_PATH;

@WebFilter(filterName = "GlobalExceptionHandler")
public class GlobalExceptionHandler implements Filter {
    public static final Logger LOG = Logger.getLogger(GlobalExceptionHandler.class);

    public void init(FilterConfig config) {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try {
            chain.doFilter(req, resp);
        } catch (Exception e) {
            // Handle other exceptions
            req.setAttribute("errorMessage", e.getCause());
            LOG.trace(e.getCause());
            req.getRequestDispatcher(ERROR_PATH).forward(req, resp);
        }
    }

    public void destroy() {}
}