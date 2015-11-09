package com.incra.services;

import com.incra.models.Series;
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
 * The <i>SeriesService</i> handles the JPA-based updating of Series entities.
 *
 * @author Brandon Risberg
 * @since 10/22/2015
 */
@Transactional
@Repository
public class SeriesService {

    @PersistenceContext
    private EntityManager em;

    public List<Series> findEntityList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Series> criteria = cb.createQuery(Series.class);
        criteria.from(Series.class);

        return em.createQuery(criteria).getResultList();
    }

    public Series findEntityById(int id) {
        return em.find(Series.class, id);
    }

    public Series findEntityByTitle(String title) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Series> criteria = builder.createQuery(Series.class);
        Root<Series> root = criteria.from(Series.class);

        Path<String> rootTitle = root.get("title");
        criteria.where(builder.equal(rootTitle, title));

        try {
            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(Series series) {
        if (series.getId() == null || series.getId() == 0) {
            em.persist(series);
        } else {
            em.merge(series);
        }
    }

    public void delete(Series series) {
        this.delete(series.getId());
    }

    public void delete(int seriesId) {
        Series existingSeries = this.findEntityById(seriesId);
        if (null != existingSeries) {
            em.remove(existingSeries);
        }
    }

    // TODO: add episode by id lookup and other episode related operations.
}
