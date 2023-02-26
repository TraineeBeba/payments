package com.epam.payments.filter;

import com.epam.payments.core.model.enums.role.Role;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


public class CommandAccessFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(CommandAccessFilter.class);

    // commands access
    private Map<Role, List<String>> accessMap = new HashMap<>();
    private List<String> commons = new ArrayList<>();
    private List<String> outOfControl = new ArrayList<>();

    public void destroy() {
        LOG.debug("Filter destruction starts");
        // do nothing
        LOG.debug("Filter destruction finished");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.debug("Filter starts");
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;
        HttpSession session = httpReq.getSession();
        if (accessAllowed(request)) {
            LOG.debug("Filter finished");
            chain.doFilter(request, response);
        } else {
            String errorMessage = "alertError.forbidden_access";

            session.setAttribute("wrongData", errorMessage);
            LOG.trace("Set the request attribute: errorMessage --> " + errorMessage);

            httpResp.sendRedirect(httpReq.getContextPath() + "?command=goLoginCommand");
        }

    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("command").trim();
        LOG.info("COMMAND " + commandName);
        LOG.info("COMMONS " + commons.contains(commandName));
        LOG.info("OUT_OF_CONTROL " + outOfControl.contains(commandName));

        if (commandName.isEmpty()) {
            LOG.info("COMMAND");
            return false;
        }

        if (outOfControl.contains(commandName)) return true;

        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            LOG.info("SESSION");
            return false;
        }

        Role userRole;
        Long role_id = (Long) (session.getAttribute("role_id"));
        if (Objects.equals(role_id, Role.ROLE_USER.getId())) {
            userRole = Role.ROLE_USER;
        } else if (Objects.equals(role_id, Role.ROLE_ADMIN.getId())) {
            userRole = Role.ROLE_ADMIN;
        } else return false;

        return accessMap.get(userRole).contains(commandName)
                || commons.contains(commandName);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        LOG.debug("Filter initialization starts");

        // roles
        accessMap.put(Role.ROLE_ADMIN, asList(fConfig.getInitParameter("admin")));
        accessMap.put(Role.ROLE_USER, asList(fConfig.getInitParameter("user")));
        LOG.trace("Access map --> " + accessMap);

        // commons
        commons = asList(fConfig.getInitParameter("common"));
        LOG.trace("Common commands --> " + commons);

        // out of control
        outOfControl = asList(fConfig.getInitParameter("out-of-control"));
        LOG.trace("Out of control commands --> " + outOfControl);

        LOG.debug("Filter initialization finished");
    }

    private List<String> asList(String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }

}