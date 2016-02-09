package com.incra.taglib;

import com.incra.database.AbstractDatabaseItem;
import com.incra.pojo.FilterDisplay;
import com.incra.pojo.FilterType;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The FilterGrid custom tag is used for painting the filter elements at the top of a grid.
 *
 * @author Brandon Risberg
 * @since 01/11/16
 */
public class FilterGridTagHandler extends AbstractTagHandler {
    private String url;
    private List<FilterDisplay> filterDisplays;

    @Override
    public int doStartTag() throws JspException {

        ServletRequest request = pageContext.getRequest();
        Map<String, String[]> parameters = request.getParameterMap();

        try {
            JspWriter out = pageContext.getOut();

            out.println("<form action='" + resolveUrl(url, null, pageContext) + "'>");
            out.println("<input type='submit' class='btn' value='Search' />");

            for (FilterDisplay filterDisplay : filterDisplays) {
                String name = filterDisplay.getName();
                String label = filterDisplay.getLabel();
                FilterType type = filterDisplay.getType();
                Object values = filterDisplay.getValues();
                String curValue = request.getParameter(name);

                out.println("&nbsp;&nbsp;" + label);

                switch (type) {
                    case STRING:
                        if (curValue == null) curValue = "";
                        out.println("<input name='" + name + "' value='" + curValue + "'>");
                        break;

                    case SELECT:
                        out.println("<select name='" + name + "'>");
                        out.println("<option>Any</option>");
                        List<AbstractDatabaseItem> valueList = (List<AbstractDatabaseItem>) values;
                        for (AbstractDatabaseItem value : valueList) {
                            boolean selected = ("" + value.getId()).equals(curValue);

                            out.println("<option value='" + value.getId() + "' " + (selected ? "selected" : "") + ">");
                            out.println(value.toString());
                            out.println("</option>");
                        }
                        out.println("</select>");
                        break;

                    case ENUMERATION:
                        out.println("<select name='" + name + "'>");
                        out.println("<option>Any</option>");
                        Enum[] enumValues = (Enum[]) values;

                        for (Enum e : enumValues) {
                            out.println("<option value='" + e.name() + ">");
                            out.println(e.toString());
                            out.println("</option>");
                        }
                        out.println("</select>");
                        break;

                    default:
                        throw new RuntimeException("Umplemented FilterType");
                }
            }

            for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
                String key = entry.getKey();

                if (key.equals("sort") || key.equals("order")) {
                    String[] value = entry.getValue();

                    out.println("<input type=hidden name='" + key + "' value='" + value[0] + "'/>");
                }
            }

            out.println("</form>");
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

    public List<FilterDisplay> getFilterDisplays() {
        return filterDisplays;
    }

    public void setFilterDisplays(List<FilterDisplay> filterDisplays) {
        this.filterDisplays = filterDisplays;
    }
}