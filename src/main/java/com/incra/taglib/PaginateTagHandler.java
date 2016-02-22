package com.incra.taglib;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Map;

/**
 * The Paginate custom tag is used to draw the pagination controls for a list grid, typically used at the bottom.
 *
 * Mostly based on twitter-bootstrap-grails-plugin /grails-app/taglib/org/groovydev/TwitterBootstrapTagLib.groovy
 *
 * @author Brandon Risberg
 * @since 01/11/2016
 */
public class PaginateTagHandler extends AbstractTagHandler {

    static final int DEFAULT_ITEMS_PER_PAGE = 12;

    private String url;
    private int totalCount = 0;
    private boolean showCount = true;
    private String countMessage = "%,d records total";

    @Override
    public int doStartTag() throws JspException {
        if (url == null) {
            throw new JspException("Invalid URL value");
        }

        ServletRequest request = pageContext.getRequest();
        Map<String, String[]> parameters = request.getParameterMap();
        int maxsteps = 10; // max number of step markers to display, with current step in center

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

        int currentstep = (offset / max) + 1;
        int firststep = 1;
        int laststep = (totalCount + max - 1) / max;

        try {
            JspWriter out = pageContext.getOut();

            out.println("<div style='background: eee;'><ul class='pagination' style='margin: 0;'>");

            if (showCount) {
                out.println("<li><span style='border: 0px solid black;'>");
                out.println(String.format(countMessage, totalCount));
                out.println("</span></li>");
            }

            // Left arrow
            if (currentstep > firststep) {
                emitElement(offset - max, "&laquo;", parameters);
            } else {
                out.println("<li class='disabled'><span>&laquo;</span></li>");
            }

            int beginstep = currentstep - Math.round(maxsteps / 2) + (maxsteps % 2);
            int endstep = currentstep + Math.round(maxsteps / 2) - 1;

            if (beginstep < firststep) {
                beginstep = firststep;
                endstep = maxsteps;
            }
            if (endstep > laststep) {
                beginstep = laststep - maxsteps + 1;
                if (beginstep < firststep) {
                    beginstep = firststep;
                }
                endstep = laststep;
            }

            // Display firststep link when beginstep is not firststep
            if (beginstep > firststep) {
                emitElement(0, "" + firststep, parameters);
                out.println("<li class='disabled'><span>...</span></li>");
            }

            // Display paginate steps
            for (int i = beginstep; i <= endstep; i++) {
                String iStr = String.format("%,d", i);

                if (i == currentstep)
                    out.println("<li class='active'><span>" + iStr + "</span></li>");
                else
                    emitElement((i - 1) * max, iStr, parameters);
            }

            // Display laststep link when endstep is not laststep
            if (endstep < laststep) {
                out.println("<li class='disabled'><span>...</span></li>");
                emitElement((laststep - 1) * max, "" + laststep, parameters);
            }

            // Right arrow
            if (currentstep < laststep) {
                emitElement(offset + max, "&raquo;", parameters);
            } else {
                out.println("<li class='disabled'><span>&raquo;</span></li>");

            }
            out.println("</ul></div>");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setShowCount(boolean showCount) {
        this.showCount = showCount;
    }

    public void setCountMessage(String countMessage) {
        this.countMessage = countMessage;
    }

    protected void emitElement(int offset, String text, Map<String, String[]> parameters)
            throws IOException, JspException {
        JspWriter out = pageContext.getOut();

        out.println("<li><a href='" + resolveUrl(url, null, pageContext) + "?offset=" + offset);

        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            String key = entry.getKey();
            if (key.equals("offset")) continue;

            String[] value = entry.getValue();
            out.println("&" + key + "=" + value[0]);
        }
        out.println("'>");

        out.println(text);
        out.println("</a></li>");
    }
}