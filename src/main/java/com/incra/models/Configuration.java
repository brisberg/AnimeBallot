package com.incra.models;

import com.incra.database.AbstractDatedDatabaseItem;

import javax.persistence.*;
import java.util.Date;

/**
 * Stores the global settings for running the application. These will be set by admins.
 */
@Entity
@Table(name = "configuration")
public class Configuration extends AbstractDatedDatabaseItem {

    @OneToOne
    @JoinColumn(name = "current_season_id", nullable = false)
    private Season currentSeason;

    @Basic
    private int currentWeekIndex;

    @Column(name = "week_start_time")
    private Date weekStartTime;


}