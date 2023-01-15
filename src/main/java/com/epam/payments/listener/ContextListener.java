package com.epam.payments.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class ContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    public ContextListener() {
    }

    public void contextInitialized(ServletContextEvent event) {
        LOG.trace("Servlet context initialization starts");

        ServletContext servletContext = event.getServletContext();
        initLog4J(servletContext);
//        initCommandContainer();

        LOG.trace("Servlet context initialization finished");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        LOG.trace("Servlet context destruction starts");
        LOG.trace("Servlet context destruction finished");
    }

    private void initLog4J(ServletContext servletContext) {
        LOG.trace("Log4J initialization started");
        try {
//            PropertyConfigurator.configure("D:\\JavaCourse\\payments\\src\\main\\webapp\\WEB-INF\\log4j.properties");
            PropertyConfigurator.configure(servletContext.getRealPath("WEB-INF/log4j.properties"));
            LOG.debug("Log4j has been initialized");
        } catch (Exception ex) {
            LOG.trace("Cannot configure Log4j");
            ex.printStackTrace();
        }
        LOG.trace("Log4J initialization finished");
    }

    /*private void initCommandContainer() {
        try {
            Class.forName("ua.nure.tatarinov.SummaryTask4.web.command.CommandFactory");
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Cannot initialize Command Container");
        }
    }*/

}

