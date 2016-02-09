package com.incra.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.incra.database.AbstractDatedDatabaseItem;
import com.incra.pojo.DayIndex;
import com.incra.pojo.HourIndex;

import javax.persistence.*;

/**
 * Stores the global settings for running the application. These will be set by admins.
 */
@Entity
@Table(name = "configuration")
public class Configuration extends AbstractDatedDatabaseItem {

    // Rarely changes
    @Column(name = "week_start_day")
    private DayIndex weekStartDay;

    @Column(name = "week_start_hour")
    private HourIndex weekStartHour;

    // Sometimes changes
    @OneToOne
    @JoinColumn(name = "current_season_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Season currentSeason;

    // Changes each week
    @Column(name = "current_week_index")
    private int currentWeekIndex;

    @Transient
    private int priorCurrentWeekIndex;

    /**
     * Default Constructor
     */
    public Configuration() {
    }

    public DayIndex getWeekStartDay() {
        return weekStartDay;
    }

    public void setWeekStartDay(DayIndex weekStartDay) {
        this.weekStartDay = weekStartDay;
    }

    public HourIndex getWeekStartHour() {
        return weekStartHour;
    }

    public void setWeekStartHour(HourIndex weekStartHour) {
        this.weekStartHour = weekStartHour;
    }

    public Season getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(Season currentSeason) {
        this.currentSeason = currentSeason;
    }

    public int getCurrentWeekIndex() {
        return currentWeekIndex;
    }

    public void setCurrentWeekIndex(int currentWeekIndex) {
        this.currentWeekIndex = currentWeekIndex;
    }

    public int getPriorCurrentWeekIndex() {
        return priorCurrentWeekIndex;
    }

    public void setPriorCurrentWeekIndex(int priorCurrentWeekIndex) {
        this.priorCurrentWeekIndex = priorCurrentWeekIndex;
    }
}