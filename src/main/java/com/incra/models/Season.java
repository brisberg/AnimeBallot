package com.incra.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.incra.database.AbstractDatedDatabaseItem;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
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
        StringBuilder sb = new StringBuilder();

        sb.append("Season[title=");
        sb.append(title);
        sb.append("]");

        return sb.toString();
    }
}