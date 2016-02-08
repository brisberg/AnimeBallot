package com.incra.taglib;

import org.apache.taglibs.standard.resources.Resources;
import org.apache.taglibs.standard.tag.common.core.ImportSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * The AbstractTagHandler class supplies utility methods.
 *
 * @author Brandon Risberg
 * @since 01/11/16
 */
public class AbstractTagHandler extends TagSupport {

    // Extracted from c:url of the JSTL library.
    public static String resolveUrl(String url, String context, PageContext pageContext) throws JspException {
        // don't touch absolute URLs
        if (ImportSupport.isAbsoluteUrl(url))
            return url;

        // normalize relative URLs against a context root
        HttpServletRequest request =
                (HttpServletRequest) pageContext.getRequest();
        if (context == null) {
            if (url.startsWith("/"))
                return (request.getContextPath() + url);
            else
                return url;
        } else {
            if (!context.startsWith("/") || !url.startsWith("/")) {
                throw new JspTagException(
                        Resources.getMessage("IMPORT_BAD_RELATIVE"));
            }
            if (context.equals("/")) {
                // Don't produce string starting with '//', many
                // browsers interpret this as host name, not as
                // path on same host.
                return url;
            } else {
                return (context + url);
            }
        }
    }
}
