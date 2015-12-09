package com.incra.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.incra.database.AbstractDatedDatabaseItem;

import javax.persistence.*;
import java.util.Date;

/**
 * Stores the global settings for running the application. These will be set by admins.
 */
@Entity
@Table(name = "configuration")
public class Configuration extends AbstractDatedDatabaseItem {

    // Rarely changes
    @Column(name = "week_start_time")
    private Date weekStartTime;

    // Sometimes changes
    @OneToOne
    @JoinColumn(name = "current_season_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Season currentSeason;

    // Weekly changes
    @Column(name = "current_week_index")
    private int currentWeekIndex;

    /**
     * Default Constructor
     */
    public Configuration() {
    }

    public Date getWeekStartTime() {
        return weekStartTime;
    }

    public void setWeekStartTime(Date weekStartTime) {
        this.weekStartTime = weekStartTime;
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
}