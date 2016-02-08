package com.incra.taglib;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Map;

/**
 * The Paginate custom tag is used to draw the pagination controls at the bottom of a list grid.
 *
 * @author Brandon Risberg
 * @since 01/11/16
 */
public class PaginateTagHandler extends AbstractTagHandler {

    static final int DEFAULT_ITEMS_PER_PAGE = 12;

    private String url;
    private int totalCount;

    @Override
    public int doStartTag() throws JspException {

        ServletRequest request = pageContext.getRequest();
        Map<String, String[]> parameters = request.getParameterMap();

        String offsetStr = request.getParameter("offset");
        int offset = 0;
        try {
            offset = Integer.parseInt(offsetStr);
        } catch (Exception e) {
            // stay at default
        }
        String maxStr = request.getParameter("max");
        int max = DEFAULT_ITEMS_PER_PAGE;
        try {
            max = Integer.parseInt(maxStr);
        } catch (Exception e) {
            // stay at default
        }

        boolean showPrev = offset > 0;
        boolean showNext = (offset + max) < totalCount;

        try {
            JspWriter out = pageContext.getOut();

            out.println("<div style='background: eee'>");
            out.println(totalCount + " records");
            if (showPrev) {
                out.println("&nbsp;&nbsp;");
                out.println("<a href='" + resolveUrl(url, null, pageContext) + "?offset=" + (offset - max));

                for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
                    String key = entry.getKey();
                    if (key.equals("offset")) continue;

                    String[] value = entry.getValue();
                    out.println("&" + key + "=" + value[0]);
                }

                out.println("'>");
                out.println("Prev page");
                out.println("</a>");
            }
            if (showNext) {
                out.println("&nbsp;&nbsp;");
                out.println("<a href='" + resolveUrl(url, null, pageContext) + "?offset=" + (offset + max));

                for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
                    String key = entry.getKey();
                    if (key.equals("offset")) continue;

                    String[] value = entry.getValue();
                    out.println("&" + key + "=" + value[0]);
                }

                out.println("'>");
                out.println("Next page");
                out.println("</a>");
            }
            out.println("<div>");

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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}