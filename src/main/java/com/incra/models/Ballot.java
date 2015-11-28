package com.incra.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.incra.database.AbstractDatedDatabaseItem;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Ballots represent a particular user's votes for a week. They  have a user_id, week_id, and other user comment data.
 *
 * @author Brandon Risberg
 * @since 10/23/2015
 */
@Entity
@Table(name = "ballot")
public class Ballot extends AbstractDatedDatabaseItem {

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Season season;

    @Column(name = "week_index")
    private int weekIndex;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    @OneToMany(mappedBy = "ballot", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<BallotVote> ballotVotes = new ArrayList<BallotVote>();

    @Column(name = "comment")
    private String comment;

    @Column(name = "submit_date")
    private Date submitDate;

    public Ballot() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BallotVote> getBallotVotes() {
        return ballotVotes;
    }

    public void setBallotVotes(List<BallotVote> ballotVotes) {
        this.ballotVotes = ballotVotes;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ballot ballot = (Ballot) o;

        if (weekIndex != ballot.weekIndex) return false;
        if (!season.equals(ballot.season)) return false;
        if (!user.equals(ballot.user)) return false;
        return submitDate.equals(ballot.submitDate);
    }

    @Override
    public int hashCode() {
        int result = season.hashCode();
        result = 31 * result + weekIndex;
        result = 31 * result + user.hashCode();
        result = 31 * result + submitDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Ballot[user=");
        sb.append(user.getFirstName());
        sb.append(",season=");
        sb.append(season.getTitle());
        sb.append("]");

        return sb.toString();
    }
}