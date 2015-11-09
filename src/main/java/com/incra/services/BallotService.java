package com.incra.services;

import com.incra.models.Ballot;
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

    public Ballot findEntityByTitle(String title) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Ballot> criteria = builder.createQuery(Ballot.class);
        Root<Ballot> root = criteria.from(Ballot.class);

        Path<String> rootTitle = root.get("title");
        criteria.where(builder.equal(rootTitle, title));

        try {
            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
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
