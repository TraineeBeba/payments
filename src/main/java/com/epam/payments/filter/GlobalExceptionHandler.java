package com.epam.payments.filter;

import com.epam.payments.core.database.dao.exeption.DAOException;
import com.epam.payments.exeption.InternalServerException;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "GlobalExceptionHandler")
public class GlobalExceptionHandler implements Filter {

    public void init(FilterConfig config) {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try {
            chain.doFilter(req, resp);
        } catch (Exception e) {
            // Handle other exceptions
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    public void destroy() {}
}