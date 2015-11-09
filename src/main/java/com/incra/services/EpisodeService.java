package com.incra.services;

import com.incra.models.Episode;
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
 * The <i>EpisodeService</i> handles the JPA-based updating of Episode entities.
 *
 * @author Brandon Risberg
 * @since 10/22/2015
 */
@Transactional
@Repository
public class EpisodeService {

    @PersistenceContext
    private EntityManager em;

    public List<Episode> findEntityList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Episode> criteria = cb.createQuery(Episode.class);
        criteria.from(Episode.class);

        return em.createQuery(criteria).getResultList();
    }

    public Episode findEntityById(int id) {
        return em.find(Episode.class, id);
    }

    public Episode findEntityByTitle(String title) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Episode> criteria = builder.createQuery(Episode.class);
        Root<Episode> root = criteria.from(Episode.class);

        Path<String> rootTitle = root.get("title");
        criteria.where(builder.equal(rootTitle, title));

        try {
            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(Episode episode) {
        if (episode.getId() == null || episode.getId() == 0) {
            em.persist(episode);
        } else {
            em.merge(episode);
        }
    }

    public void delete(Episode episode) {
        this.delete(episode.getId());
    }

    public void delete(int episodeId) {
        Episode existingEpisode = this.findEntityById(episodeId);
        if (null != existingEpisode) {
            em.remove(existingEpisode);
        }
    }

    // TODO: add reshuffling and group create functions along with Week object integration
}
