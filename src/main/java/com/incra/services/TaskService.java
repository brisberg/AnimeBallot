package com.incra.services;

import com.incra.models.Season;
import com.incra.models.Task;
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
 * The <i>TaskService</i> handles the JPA-based updating of Task entities.
 *
 * @author Brandon Risberg
 * @since 10/22/2015
 */
@Transactional
@Repository
public class TaskService {

    @PersistenceContext
    private EntityManager em;

    public List<Task> findEntityList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = cb.createQuery(Task.class);
        criteria.from(Task.class);

        return em.createQuery(criteria).getResultList();
    }

    public List<Task> findEntityListByUser(User user) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = cb.createQuery(Task.class);
        Root<Task> root = criteria.from(Task.class);

        Path<User> rootUser = root.get("user");
        criteria.where(cb.equal(rootUser, user));
        return em.createQuery(criteria).getResultList();
    }

    public Task findEntityById(int id) {
        return em.find(Task.class, id);
    }

    public void save(Task task) {
        if (task.getId() == null || task.getId() == 0) {
            em.persist(task);
        } else {
            em.merge(task);
        }
    }

    public void delete(Task task) {
        this.delete(task.getId());
    }

    public void delete(int taskId) {
        Task existingTask = this.findEntityById(taskId);
        if (null != existingTask) {
            em.remove(existingTask);
        }
    }
}
