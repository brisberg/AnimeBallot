package com.incra.models;

import com.incra.database.AbstractDatedDatabaseItem;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * Series have a title, start and end dates and the season in which they aired.
 *
 * @author Brandon Risberg
 * @since 10/23/2015
 */
@Entity
@Table(name = "series")
public class Series extends AbstractDatedDatabaseItem {

    @Basic
    @Size(min = 3)
    private String title;

    @Column(name = "episode_count")
    private int episodeCount;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @OneToMany(mappedBy = "series")
    private List<Episode> episodeList;

    public Series() {
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

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    @JsonIgnore
    public List<Episode> getEpisodeList() {
        return episodeList;
    }

    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Series)) return false;

        Series series = (Series) o;

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