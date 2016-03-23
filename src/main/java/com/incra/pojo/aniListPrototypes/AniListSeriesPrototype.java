package com.incra.pojo.aniListPrototypes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.incra.models.Series;

import java.util.Date;

/**
 * AniListSeriesPrototype is a transient shell object to catch the deserialized results from the AniList.co api responses.
 * It should be quickly converted into a proper Series object.
 *
 * @author Brandon Risberg
 * @since 3/9/2016
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AniListSeriesPrototype {

    private int seriesId;

    @JsonProperty("id")
    private int aniListId;

    @JsonProperty("title_english")
    private String title;

    @JsonProperty("total_episodes")
    private int episodeCount;

    @JsonProperty("start_date")
    private Date startDate;

    @JsonProperty("end_date")
    private Date endDate;

    public AniListSeriesPrototype() {
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public int getAniListId() {
        return aniListId;
    }

    public void setAniListId(int aniListId) {
        this.aniListId = aniListId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AniListSeriesPrototype)) return false;

        AniListSeriesPrototype series = (AniListSeriesPrototype) o;

        return title.equals(series.title);

    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("AniListSeriesPrototype[title=");
        sb.append(title);
        sb.append("]");

        return sb.toString();
    }

    /**
     * Convert this prototype into a new native Series object
     */
    public Series convert() {
        // create the native series object we will eventually become
        Series resultSeries = new Series();

        // copy our data over
        updateInto(resultSeries);

        return resultSeries;
    }

    /**
     * Copy prototype's data into an existing Series object
     */
    public void updateInto(Series series) {
        // copy our data over
        series.setTitle(title);
        series.setAniListId(aniListId);
        series.setEpisodeCount(episodeCount);
        series.setStartDate(startDate);
        series.setEndDate(endDate);
    }
}