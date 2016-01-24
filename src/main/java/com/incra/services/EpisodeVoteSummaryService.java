package com.incra.services;

import com.incra.models.EpisodeVoteSummary;
import com.incra.models.Season;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * The <i>EpisodeVoteSummaryService</i> handles the fetching of EpisodeVoteSummary records.
 *
 * @author Brandon Risberg
 * @since 1/23/2016
 */
@Transactional
@Repository
public class EpisodeVoteSummaryService {

    @PersistenceContext
    private EntityManager em;

    public List<EpisodeVoteSummary> findEntityListByWeekIndex(int weekIndex) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<EpisodeVoteSummary> criteria = builder.createQuery(EpisodeVoteSummary.class);
        Root<EpisodeVoteSummary> root = criteria.from(EpisodeVoteSummary.class);

        Path<Integer> rootWeekIndex = root.get("weekIndex");
        criteria.where(builder.equal(rootWeekIndex, weekIndex));

        return em.createQuery(criteria).getResultList();
    }

    public List<EpisodeVoteSummary> findEntityListByWeekRange(int start_weekIndex, int end_weekIndex) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<EpisodeVoteSummary> criteria = builder.createQuery(EpisodeVoteSummary.class);
        Root<EpisodeVoteSummary> root = criteria.from(EpisodeVoteSummary.class);

        Path<Integer> rootWeekIndex = root.get("weekIndex");
        criteria.where(builder.and(
                builder.greaterThanOrEqualTo(rootWeekIndex, start_weekIndex),
                builder.lessThanOrEqualTo(rootWeekIndex, end_weekIndex)));

        return em.createQuery(criteria).getResultList();
    }
}
