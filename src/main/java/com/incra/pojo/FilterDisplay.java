package com.incra.pojo;

/**
 * The <i>DisplayFilterPojo</i> describes a filter element of a display, and is used by the
 * filter rendering tag to paint the filters
 *
 * @author Brandon Risberg
 * @since 10/22/15
 */
public class FilterDisplay {
    private String name;
    private String label;
    private FilterType type;
    private int size;
    private Object values;

    public FilterDisplay(String name, String label, FilterType type, int size, Object values) {
        this.name = name;
        this.label = label;
        this.type = type;
        this.size = size;
        this.values = values;
    }

    public FilterDisplay(String name, String label, FilterType type, Object values) {
        this(name, label, type, 12, values);
    }

    public String getName() { return name; }

    public String getLabel() {
        return label;
    }

    public FilterType getType() {
        return type;
    }

    public int getSize() { return size; }

    public Object getValues() {
        return values;
    }
}
