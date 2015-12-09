package com.incra.services;

import com.incra.models.Ballot;
import com.incra.models.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * The <i>ConfigurationService</i> handles the JPA-based updating of Configuration entities.
 *
 * @author Brandon Risberg
 * @since 11/30/2015
 */
@Transactional
@Repository
public class ConfigurationService {

    @PersistenceContext
    private EntityManager em;

    public List<Configuration> findEntityList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Configuration> criteria = cb.createQuery(Configuration.class);
        criteria.from(Configuration.class);

        return em.createQuery(criteria).getResultList();
    }

    public Configuration findEntityById(int id) {
        return em.find(Configuration.class, id);
    }

    /**
     * Returns the first configuration. Presumably we only have 1 configuration in the table and it is the active one.
     */
    public Configuration findActiveEntity() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Configuration> criteria = cb.createQuery(Configuration.class);
        criteria.from(Configuration.class);

        return em.createQuery(criteria).getResultList().get(0);
    }

    public void save(Configuration configuration) {
        if (configuration.getId() == null || configuration.getId() == 0) {
            em.persist(configuration);
        } else {
            em.merge(configuration);
        }
    }

    public void delete(Configuration configuration) {
        this.delete(configuration.getId());
    }

    public void delete(int configurationId) {
        Configuration existingConfiguration = this.findEntityById(configurationId);
        if (null != existingConfiguration) {
            em.remove(existingConfiguration);
        }
    }
}
