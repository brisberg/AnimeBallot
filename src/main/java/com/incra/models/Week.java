package com.incra.models;

import com.incra.database.AbstractDatedDatabaseItem;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * Weeks have an index number, a parent season, start and end dates. All episodes and ballots will be assigned to
 * a particular week.
 *
 * @author Brandon Risberg
 * @since 11/4/2015
 */
public class Week {

    private int index;

    private Date startDate;

    private Date endDate;

    private Season season;

    private List<Episode> episodeList;

    public Week() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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

    public List<Episode> getEpisodeList() {
        return episodeList;
    }

    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Week[index=");
        sb.append(index);
        sb.append(", season=");
        sb.append(season.toString());
        sb.append("]");

        return sb.toString();
    }
}