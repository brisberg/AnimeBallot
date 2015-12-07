package com.incra.services;

import com.incra.models.Ballot;
import com.incra.models.BallotVote;
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
 * The <i>BallotService</i> handles the JPA-based updating of Ballot entities.
 *
 * @author Brandon Risberg
 * @since 10/30/2015
 */
@Transactional
@Repository
public class BallotService {

    @PersistenceContext
    private EntityManager em;

    public List<Ballot> findEntityList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ballot> criteria = cb.createQuery(Ballot.class);
        criteria.from(Ballot.class);

        return em.createQuery(criteria).getResultList();
    }

    public Ballot findEntityById(int id) {
        return em.find(Ballot.class, id);
    }

    public List<Ballot> findEntityListByUserAndWeekIndex(User user, Integer weekIndex) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Ballot> criteria = builder.createQuery(Ballot.class);
        Root<Ballot> root = criteria.from(Ballot.class);

        Path<User> pathUser = root.get("user");
        Path<Integer> pathWeekIndex = root.get("weekIndex");

        criteria.where(builder.and(
                builder.equal(pathUser, user),
                builder.equal(pathWeekIndex, weekIndex)));

        return em.createQuery(criteria).getResultList();
    }

    public void save(Ballot ballot) {
        if (ballot.getId() == null || ballot.getId() == 0) {
            em.persist(ballot);
        } else {
            em.merge(ballot);
        }
    }

    public void delete(Ballot ballot) {
        this.delete(ballot.getId());
    }

    public void delete(int ballotId) {
        Ballot existingBallot = this.findEntityById(ballotId);
        if (null != existingBallot) {
            em.remove(existingBallot);
        }
    }
}
