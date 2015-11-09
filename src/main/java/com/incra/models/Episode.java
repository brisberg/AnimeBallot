package com.incra.models;

import com.incra.database.AbstractDatedDatabaseItem;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Series have a title, start and end dates and the season in which they aired.
 *
 * @author Brandon Risberg
 * @since 10/23/2015
 */
@Entity
@Table(name = "episode")
public class Episode extends AbstractDatedDatabaseItem {

    @Basic
    private String title;

    @Column(name = "episode_index")
    private int episodeIndex;

    @Column(name = "air_date")
    private Date airDate;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @Column(name = "week_index")
    private int weekIndex;

    @ManyToOne
    @JoinColumn(name = "series_id", nullable = false)
    private Series series;

    public Episode() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEpisodeIndex() {
        return episodeIndex;
    }

    public void setEpisodeIndex(int episodeIndex) {
        this.episodeIndex = episodeIndex;
    }

    public Date getAirDate() {
        return airDate;
    }

    public void setAirDate(Date airDate) {
        this.airDate = airDate;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public int getWeekIndex() {
        return weekIndex;
    }

    public void setWeekIndex(int weekIndex) {
        this.weekIndex = weekIndex;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Episode)) return false;

        Episode series = (Episode) o;

        if (!title.equals(series.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Series[title=");
        sb.append(title);
        sb.append("]");

        return sb.toString();
    }
}