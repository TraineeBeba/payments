package com.epam.payments.tag;

import com.epam.payments.utils.Utils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Properties;

public class NameTag extends TagSupport {
    private static final Logger LOG = Logger.getLogger(NameTag.class);
    private static final long serialVersionUID = 823688264936004139L;

    private String value;

    public void setValue(String value){
        this.value= value;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();

        if (value!=null){
            Properties name = Utils.getNameProperties();

            JspWriter out = pageContext.getOut();
            try {
                out.println(name.getProperty(value).trim());
            } catch (IOException e) {
                LOG.error("Not find this key!");
            }
        }

        return SKIP_BODY;
    }
}
