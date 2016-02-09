package com.incra.pojo;

/**
 * Value is the number of seconds from 0000 hours on Sunday to 0000 hours on the selected day.
 *
 * @author Brandon Risberg
 * @since 1/30/2016
 */
public enum DayIndex {
    D00("Sunday", 0 * 60 * 60 * 24L),
    D01("Monday", 1 * 60 * 60 * 24L),
    D02("Tuesday", 2 * 60 * 60 * 24L),
    D03("Wednesday", 3 * 60 * 60 * 24L),
    D04("Thursday", 4 * 60 * 60 * 24L),
    D05("Friday", 5 * 60 * 60 * 24L),
    D06("Saturday", 6 * 60 * 60 * 24L);

    protected String label;
    protected long value;

    DayIndex(String label, long value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public long getValue() {
        return value;
    }
}
