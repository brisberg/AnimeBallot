package com.incra.services;

import com.incra.models.Ballot;
import com.incra.models.BallotVote;
import com.incra.models.Series;
import com.incra.models.User;
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
 * The <i>BallotVoteService</i> handles the JPA-based updating of BallotVote entities.
 *
 * @author Brandon Risberg
 * @since 10/30/2015
 */
@Transactional
@Repository
public class BallotVoteService {

    @PersistenceContext
    private EntityManager em;

    public List<BallotVote> findEntityList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BallotVote> criteria = cb.createQuery(BallotVote.class);
        criteria.from(BallotVote.class);

        return em.createQuery(criteria).getResultList();
    }

    public BallotVote findEntityById(int id) {
        return em.find(BallotVote.class, id);
    }

    public List<BallotVote> findEntityListByUserAndWeekIndex(User user, Integer weekIndex) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BallotVote> criteria = builder.createQuery(BallotVote.class);
        Root<BallotVote> root = criteria.from(BallotVote.class);

        Path<User> pathUser = root.get("ballot").get("user");
        Path<Integer> pathWeekIndex = root.get("ballot").get("weekIndex");

        criteria.where(builder.and(
                builder.equal(pathUser, user),
                builder.equal(pathWeekIndex, weekIndex)));

        return em.createQuery(criteria).getResultList();
    }

    public void save(BallotVote ballotVote) {
        if (ballotVote.getId() == null || ballotVote.getId() == 0) {
            em.persist(ballotVote);
        } else {
            em.merge(ballotVote);
        }
    }

    public void delete(Ballot ballotVote) {
        this.delete(ballotVote.getId());
    }

    public void delete(int ballotVoteId) {
        BallotVote existingBallotVote = this.findEntityById(ballotVoteId);
        if (null != existingBallotVote) {
            em.remove(existingBallotVote);
        }
    }
}
