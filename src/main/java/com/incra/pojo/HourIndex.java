package com.incra.pojo;

/**
 * Value is the number of seconds from 0000 hours to the selected hour.
 *
 * @author Brandon Risberg
 * @since 1/30/2016
 */
public enum HourIndex {
    H00("12 Midnight", 0 * 60 * 60L),
    H01("1:00 AM", 1 * 60 * 60L),
    H02("2:00 AM", 2 * 60 * 60L),
    H03("3:00 AM", 3 * 60 * 60L),
    H04("4:00 AM", 4 * 60 * 60L),
    H05("5:00 AM", 5 * 60 * 60L),
    H06("6:00 AM", 6 * 60 * 60L),
    H07("7:00 AM", 7 * 60 * 60L),
    H08("8:00 AM", 8 * 60 * 60L),
    H09("9:00 AM", 9 * 60 * 60L),
    H10("10:00 AM", 10 * 60 * 60L),
    H11("11:00 AM", 11 * 60 * 60L),
    H12("12:00 PM", 12 * 60 * 60L),
    H13("1:00 PM", 13 * 60 * 60L),
    H14("2:00 PM", 14 * 60 * 60L),
    H15("3:00 PM", 15 * 60 * 60L),
    H16("4:00 PM", 16 * 60 * 60L),
    H17("5:00 PM", 17 * 60 * 60L),
    H18("6:00 PM", 18 * 60 * 60L),
    H19("7:00 PM", 19 * 60 * 60L),
    H20("8:00 PM", 20 * 60 * 60L),
    H21("9:00 PM", 21 * 60 * 60L),
    H22("10:00 PM", 22 * 60 * 60L),
    H23("11:00 PM", 23 * 60 * 60L);

    protected String label;
    protected long value;

    HourIndex(String label, long value) {
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
