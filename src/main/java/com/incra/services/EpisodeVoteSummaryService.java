package com.incra.services;

import com.incra.models.EpisodeVoteSummary;
import com.incra.models.Season;
import com.incra.models.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The <i>EpisodeVoteSummaryService</i> handles the fetching, saving, and calculating of EpisodeVoteSummary records.
 *
 * @author Brandon Risberg
 * @since 1/23/2016
 */
@Transactional
@Repository
public class EpisodeVoteSummaryService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private SeriesService seriesService;
    @Autowired
    private SeasonService seasonService;

    public List<EpisodeVoteSummary> findEntityListByWeekRange(int startWeekIndex, int endWeekIndex) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<EpisodeVoteSummary> criteria = builder.createQuery(EpisodeVoteSummary.class);
        Root<EpisodeVoteSummary> root = criteria.from(EpisodeVoteSummary.class);

        Path<Integer> rootWeekIndex = root.get("weekIndex");
        criteria.where(builder.and(
                builder.greaterThanOrEqualTo(rootWeekIndex, startWeekIndex),
                builder.lessThanOrEqualTo(rootWeekIndex, endWeekIndex)));

        return em.createQuery(criteria).getResultList();
    }

    public void save(EpisodeVoteSummary voteSummary) {
        if (voteSummary.getId() == null || voteSummary.getId() == 0) {
            em.persist(voteSummary);
        } else {
            em.merge(voteSummary);
        }
    }

    /**
     * Recalc the EpisodeVoteSummaries from the between two week indexes.
     *
     * @param startWeekIndex
     * @param endWeekIndex
     */
    public void recalc(int startWeekIndex, int endWeekIndex) {
        Map<Integer, Integer> priorRanks = new HashMap<>();

        if (startWeekIndex > 1) {
            StringBuilder priorWeekSummarySQL = new StringBuilder();
            priorWeekSummarySQL.append("SELECT series_id,episode_index,rank ");
            priorWeekSummarySQL.append("FROM episode_vote_summaries ");
            priorWeekSummarySQL.append("WHERE week_index=" + (startWeekIndex - 1) + " ");

            List<Object[]> priorWeekSummaryResults = em.createNativeQuery(priorWeekSummarySQL.toString()).getResultList();

            for (Object[] row : priorWeekSummaryResults) {
                priorRanks.put((Integer) row[0], (Integer) row[2]);
            }
        }

        for (int weekIndex = startWeekIndex; weekIndex <= endWeekIndex; weekIndex++) {
            List<EpisodeVoteSummary> episodeVoteSummaryList = new ArrayList<EpisodeVoteSummary>();

            StringBuilder deleteVoteSummariesSQL = new StringBuilder();
            deleteVoteSummariesSQL.append("DELETE from EpisodeVoteSummary ");
            deleteVoteSummariesSQL.append("WHERE weekIndex=" + weekIndex); // this is JPQL, don't add a comma

            int deletedCount = em.createQuery(deleteVoteSummariesSQL.toString()).executeUpdate();
            System.out.println("Deleted " + deletedCount + " records for weekIndex " + weekIndex);

            StringBuilder voteSummarySQL = new StringBuilder();
            voteSummarySQL.append("SELECT e.series_id,e.episode_index,count(b.id),sum(bv.score) ");
            voteSummarySQL.append("FROM ballot_vote as bv ");
            voteSummarySQL.append("LEFT JOIN ballot as b ON bv.ballot_id=b.id ");
            voteSummarySQL.append("LEFT JOIN episode as e ON bv.episode_id=e.id ");
            voteSummarySQL.append("WHERE b.week_index=" + weekIndex + " ");
            voteSummarySQL.append("GROUP BY e.series_id, e.episode_index ");
            voteSummarySQL.append("ORDER BY sum(bv.score) DESC; ");

            List<Object[]> voteSummaryResults = em.createNativeQuery(voteSummarySQL.toString()).getResultList();

            StringBuilder ballotCountSQL = new StringBuilder();
            ballotCountSQL.append("SELECT count(b.id), season_id ");
            ballotCountSQL.append("FROM ballot as b ");
            ballotCountSQL.append("WHERE b.week_index=" + weekIndex + " ");
            ballotCountSQL.append("GROUP BY season_id; ");

            List<Object[]> ballotCountResults = em.createNativeQuery(ballotCountSQL.toString()).getResultList();

            Object[] ballotCountResult = ballotCountResults.get(0);
            int numTotalBallots = ((BigInteger) ballotCountResult[0]).intValue();
            int seasonId = ((Integer) ballotCountResult[1]).intValue();

            Season season = seasonService.findEntityById(seasonId);

            try {
                int rank = 1;
                for (Object[] foobar : voteSummaryResults) {
                    Series series = seriesService.findEntityById((Integer) foobar[0]);

                    EpisodeVoteSummary summary = new EpisodeVoteSummary();
                    summary.setWeekIndex(weekIndex);
                    summary.setSeason(season);
                    summary.setSeries(series);
                    summary.setSeriesTitle(series.getTitle());
                    summary.setEpisodeIndex((Integer) foobar[1]);

                    Integer previousRank = priorRanks.get(series.getId());

                    summary.setRank(rank);
                    summary.setRawScore(((BigInteger) foobar[3]).intValue());
                    summary.setNormScore(((BigInteger) foobar[3]).doubleValue() / numTotalBallots);
                    summary.setPercentage(((BigInteger) foobar[2]).doubleValue() / numTotalBallots);
                    if (previousRank != null) {
                        summary.setChange(previousRank.intValue() - rank);
                    } else {
                        summary.setChange(null);
                    }

                    episodeVoteSummaryList.add(summary);
                    priorRanks.put(series.getId(), new Integer(rank));
                    rank++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (EpisodeVoteSummary episodeVoteSummary : episodeVoteSummaryList) {
                save(episodeVoteSummary);
            }
        }
    }
}
