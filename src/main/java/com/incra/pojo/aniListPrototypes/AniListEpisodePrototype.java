package com.incra.pojo.aniListPrototypes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.incra.models.Episode;

import java.util.Date;

/**
 * Series have a title, start and end dates and the season in which they aired.
 *
 * @author Brandon Risberg
 * @since 3/12/2016
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AniListEpisodePrototype {

    @JsonProperty("next_episode")
    private int episodeIndex;

    @JsonProperty("time")
    private Date airDate;

    public AniListEpisodePrototype() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AniListEpisodePrototype)) return false;

        AniListEpisodePrototype episodePrototype = (AniListEpisodePrototype) o;

        return airDate.equals(episodePrototype.airDate)
                && episodeIndex == episodePrototype.episodeIndex;
    }

    @Override
    public int hashCode() {
        return airDate.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("AniListEpisodePrototype[index=");
        sb.append(episodeIndex);
        sb.append("]");

        return sb.toString();
    }

    /**
     * Convert this prototype into a new native Episode object
     */
    public Episode convert() {
        // create the native series object we will eventually become
        Episode resultEpisode = new Episode();

        // copy our data over
        updateInto(resultEpisode);

        return resultEpisode;
    }

    /**
     * Copy prototype's data into an existing Episode object
     */
    public void updateInto(Episode episode) {
        // copy our data over
        episode.setAirDate(airDate);
        episode.setEpisodeIndex(episodeIndex);
    }
}