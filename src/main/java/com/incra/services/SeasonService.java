package com.incra.services;

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
 * The <i>SeasonService</i> handles the JPA-based updating of Season entities.
 *
 * @author Brandon Risberg
 * @since 10/22/2015
 */
@Transactional
@Repository
public class SeasonService {

    @PersistenceContext
    private EntityManager em;

    public List<Season> findEntityList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Season> criteria = cb.createQuery(Season.class);
        criteria.from(Season.class);

        return em.createQuery(criteria).getResultList();
    }

    public Season findEntityById(int id) {
        return em.find(Season.class, id);
    }

    public Season findEntityByTitle(String title) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Season> criteria = builder.createQuery(Season.class);
        Root<Season> root = criteria.from(Season.class);

        Path<String> rootTitle = root.get("title");
        criteria.where(builder.equal(rootTitle, title));

        try {
            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(Season season) {
        if (season.getId() == null || season.getId() == 0) {
            em.persist(season);
        } else {
            em.merge(season);
        }
    }

    public void delete(Season season) {
        this.delete(season.getId());
    }

    public void delete(int seasonId) {
        Season existingSurvey = this.findEntityById(seasonId);
        if (null != existingSurvey) {
            em.remove(existingSurvey);
        }
    }
}
