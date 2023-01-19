package com.epam.payments.tag;

import com.epam.payments.i18n.LocaleTag;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class CommandTag extends TagSupport {
    private static final Logger LOG = Logger.getLogger(CommandTag.class);
    private static final long serialVersionUID = 823688264936004139L;

    private String value;

    public void setValue(String value){
        this.value= value;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();

        if (value!=null){
            Properties commands = new Properties();
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("/commands.properties");
            try {
                commands.load(stream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            JspWriter out = pageContext.getOut();
            try {
                out.println(commands.getProperty(value));
            } catch (IOException e) {
                LOG.error("Not find this key!");
            }
        }

        return SKIP_BODY;
    }
}
