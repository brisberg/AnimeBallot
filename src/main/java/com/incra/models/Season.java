package com.incra.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.incra.database.AbstractDatedDatabaseItem;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

/**
 * Surveys have a title, and a start and end date. They have a one-to-many with series that aired during this season.
 *
 * @author Brandon Risberg
 * @since 10/22/2015
 */
@Entity
@Table(name = "season")
public class Season extends AbstractDatedDatabaseItem {

    @Basic
    @Size(min = 3)
    private String title;

    @Column(name = "year")
    private int year;

    @Column(name = "quarter")
    private String quarter; // TODO: make this an enum for ("summer", "fall, "winter", "spring")

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @OneToMany(mappedBy = "season")
    private List<Series> seriesList;

    /**
     * Constructor
     */
    public Season() {
        seriesList = new ArrayList<Series>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @JsonIgnore
    public List<Series> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<Series> seriesList) {
        this.seriesList = seriesList;
    }

    public Date getStartDateByWeekIndex(int weekIndex) {
        Date startDate = getStartDate();

        Calendar c = Calendar.getInstance();

        for (int i = 1; i < weekIndex; i++) {
            c.setTime(startDate);
            c.add(Calendar.WEEK_OF_YEAR, 1);
            startDate = c.getTime();
        }

        return startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Season)) return false;

        Season season = (Season) o;

        if (!title.equals(season.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public String toString() {
        return title; // must be just title, because this is used in FilterGrid drop-down
    }
}