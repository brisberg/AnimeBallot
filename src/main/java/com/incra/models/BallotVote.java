package com.incra.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.incra.database.AbstractDatedDatabaseItem;

import javax.persistence.*;
import java.util.Date;

/**
 * BallotVote has a ballot_id, an episode_id, and a score.
 *
 * @author Brandon Risberg
 * @since 10/23/2015
 */
@Entity
@Table(name = "ballot_vote")
public class BallotVote extends AbstractDatedDatabaseItem {

    @ManyToOne
    @JoinColumn(name = "episode_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Episode episode;

    @ManyToOne
    @JoinColumn(name = "ballot_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Ballot ballot;

    @Basic
    private int score;

    public BallotVote() {
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public Ballot getBallot() {
        return ballot;
    }

    public void setBallot(Ballot ballot) {
        this.ballot = ballot;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BallotVote)) return false;

        BallotVote ballotVote = (BallotVote) o;

        if (!ballot.equals(ballotVote.getBallot()) || !episode.equals(ballotVote.getEpisode())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return ballot.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("BallotVote[ballot=");
        sb.append(ballot.getId());
        sb.append(",episode=");
        sb.append(episode.getTitle());
        sb.append(",score=");
        sb.append(score);
        sb.append("]");

        return sb.toString();
    }
}