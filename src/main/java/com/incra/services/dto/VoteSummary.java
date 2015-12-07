package com.incra.services.dto;

import com.incra.models.Episode;
import com.incra.models.Season;
import com.incra.models.Series;

import java.util.Date;
import java.util.List;

/**
 * VoteSummary data is shown on the main screen.
 *
 * @author Brandon Risberg
 * @since 11/4/2015
 */
public class VoteSummary {

    private Integer weekIndex;

    private Integer rank;

    private Series series;

    private Integer episodeIndex;

    private Double percentage;

    private String change;

    /**
     * Default Constructor
     */
    public VoteSummary() {
    }

    public Integer getWeekIndex() {
        return weekIndex;
    }

    public void setWeekIndex(Integer weekIndex) {
        this.weekIndex = weekIndex;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public Integer getEpisodeIndex() {
        return episodeIndex;
    }

    public void setEpisodeIndex(Integer episodeIndex) {
        this.episodeIndex = episodeIndex;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }
}