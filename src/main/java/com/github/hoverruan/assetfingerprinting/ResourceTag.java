package com.github.hoverruan.assetfingerprinting;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import static com.github.hoverruan.assetfingerprinting.ResourceFunctions.resource;

/**
 * @author Hover Ruan
 */
public class ResourceTag extends TagSupport {
    private String var;
    private String path;

    @Override
    public int doStartTag() throws JspException {
        try {
            String assetPath = resource(pageContext, path);

            if (var == null) {
                pageContext.getOut().print(assetPath);
            } else {
                pageContext.setAttribute(var, assetPath);
            }
        } catch (Exception e) {
            throw new JspException(e);
        }

        return super.doStartTag();
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setVar(String var) {
        this.var = var;
    }
}
