package com.epam.payments.appcontext;

import com.epam.payments.appcontext.constant.ContextConstants;
import com.epam.payments.appcontext.impl.AppContext;
import com.epam.payments.command.constant.ParamNames;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static com.epam.payments.command.constant.ParamNames.*;

@WebListener()
public class AppContextListener implements ServletContextListener, ContextConstants {
    private static final Logger LOG = Logger.getLogger(AppContextListener.class);

    public AppContextListener() {
    }

    public void contextInitialized(ServletContextEvent event) {
        LOG.trace("Servlet appContext initialization starts");

        ServletContext servletContext = event.getServletContext();

        initLog4J(servletContext);
//        initCommandContainer();

        try {
            AppContext appContext = AppContextManager.getAppContext(CONFIG_FILE);
            servletContext.setAttribute(APP_CONTEXT, appContext);
        } catch (Exception e) {
            Throwable rootCause = e.getCause();
            if (rootCause != null) {
                LOG.error("Failed to initialize program components. Root cause: ", rootCause);
//                LOG.error(rootCause);
            } else {
                LOG.error("Failed to initialize program components", e);
            }

            throw new RuntimeException("Failed to initialize program components", e);
        }

        LOG.trace("Servlet appContext initialization finished");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        LOG.trace("Servlet appContext destruction starts");
        LOG.trace("Servlet appContext destruction finished");
    }

    private void initLog4J(ServletContext servletContext) {
        LOG.trace("Log4J initialization started");
        try {
            PropertyConfigurator.configure(servletContext.getRealPath("WEB-INF/log4j.properties"));
            LOG.debug("Log4j has been initialized");
        } catch (Exception ex) {
            LOG.trace("Cannot configure Log4j");
            ex.printStackTrace();
        }
        LOG.trace("Log4J initialization finished");
    }
}

