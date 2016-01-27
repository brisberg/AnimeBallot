package com.incra.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.incra.database.AbstractDatedDatabaseItem;
import com.incra.models.Episode;
import com.incra.models.Season;
import com.incra.models.Series;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * EpisodeVoteSummary data is shown on the main screen.
 *
 * @author Brandon Risberg
 * @since 1/2/2016
 */
@Entity
@Table(name = "episode_vote_summaries")
public class EpisodeVoteSummary extends AbstractDatedDatabaseItem {

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIgnore
    private Season season;

    @Column(name = "week_index")
    private int weekIndex;

    @ManyToOne
    @JoinColumn(name = "series_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIgnore
    private Series series;

    @Column(name = "episode_index")
    private int episodeIndex;

    @Column(name = "rank")
    private int rank; // 1 - n

    @Column(name = "score_raw")
    private int rawScore;

    @Column(name = "score_norm")
    private double normScore;

    @Column(name = "percentage")
    private double percentage;

    @Column(name = "change")
    private int change;

    public Date getWeekIndexDate() {
        return weekIndexDate;
    }

    public void setWeekIndexDate(Date weekIndexDate) {
        this.weekIndexDate = weekIndexDate;
    }

    @Transient
    private Date weekIndexDate;

    @Transient
    private String seriesTitle;

    @Transient
    private Date weekStartDate;
    /**
     * Default Constructor
     */
    public EpisodeVoteSummary() {
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

    public int getEpisodeIndex() {
        return episodeIndex;
    }

    public void setEpisodeIndex(int episodeIndex) {
        this.episodeIndex = episodeIndex;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRawScore() {
        return rawScore;
    }

    public void setRawScore(int rawScore) {
        this.rawScore = rawScore;
    }

    public double getNormScore() {
        return normScore;
    }

    public void setNormScore(double normScore) {
        this.normScore = normScore;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    public Date getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(Date weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    @Override
    public String toString() {
        return "EpisodeVoteSummary{" +
                "season=" + season +
                ", weekIndex=" + weekIndex +
                ", series=" + series +
                ", episodeIndex=" + episodeIndex +
                ", rawScore=" + rawScore +
                '}';
    }
}