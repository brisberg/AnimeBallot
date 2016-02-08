package com.incra.taglib;

import org.apache.taglibs.standard.resources.Resources;
import org.apache.taglibs.standard.tag.common.core.ImportSupport;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Map;

/**
 * The SortableHeader custom tag is used in place of the <th> for the header of a grid column which is to be
 * sortable.  Relies on Twitter Bootstrap to provide the glyphs used to indicate sort status and direction.
 *
 * @author Brandon Risberg
 * @since 01/11/16
 */
public class SortableHeaderTagHandler extends AbstractTagHandler {
    private String url;
    private String property;
    private String title;

    @Override
    public int doStartTag() throws JspException {

        ServletRequest request = pageContext.getRequest();
        Map<String, String[]> parameters = request.getParameterMap();

        String sortProperty = request.getParameter("sort");
        boolean sortActive = sortProperty != null && sortProperty.equals(property);
        String order = request.getParameter("order");
        boolean orderAsc = order != null && order.equals("asc");

        try {
            JspWriter out = pageContext.getOut();

            out.println("<th>");
            out.println("<a href='");
            out.println(resolveUrl(url, null, pageContext));
            out.println("?sort=" + property + "&order=" + (!orderAsc ? "asc" : "desc"));

            for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
                String key = entry.getKey();
                if (key.equals("sort") || key.equals("order")) continue;

                String[] value = entry.getValue();
                out.println("&" + key + "=" + value[0]);
            }

            out.println("'>");
            out.println(title);
            if (sortActive) {
                if (orderAsc) {
                    out.println("<span class='glyphicon glyphicon-chevron-down' aria-hidden='true'></span>");
                } else {
                    out.println("<span class='glyphicon glyphicon-chevron-up' aria-hidden='true'></span>");
                }
            }
            out.println("</a>");
            out.println("</th>");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}