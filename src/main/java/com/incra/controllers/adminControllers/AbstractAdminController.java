package com.incra.controllers.adminControllers;

import com.incra.models.Series;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.servlet.ServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The <i>AbstractAdminController</i> provides utility functions to all
 * controllers which are carrying out admin CRUD operations.
 *
 * @author Jeffrey Risberg
 * @since 12/05/13
 */
//@Secured("ROLE_ADMIN")
public abstract class AbstractAdminController {
    @PersistenceContext
    protected EntityManager em;

    static final int DEFAULT_ITEMS_PER_PAGE = 12;

    protected Date getNow() {
        Calendar now = Calendar.getInstance();
        return now.getTime();
    }

    protected Query buildListQuery
            (CriteriaBuilder cb, CriteriaQuery criteria, Root root, Predicate[] predarray, ServletRequest request) {
        criteria.where(predarray);

        // Apply sorting
        String sortProperty = (request.getParameter("sort") != null) ? request.getParameter("sort").trim() : null;
        if (sortProperty != null) {
            String orderStr = request.getParameter("order");
            Path sortPath = root.get(sortProperty);

            if (orderStr != null && orderStr.equals("desc"))
                criteria.orderBy(cb.desc(sortPath));
            else
                criteria.orderBy(cb.asc(sortPath));
        }

        Query query = em.createQuery(criteria);

        // Apply pagination
        int offset = 0;
        int max = DEFAULT_ITEMS_PER_PAGE;
        try {
            if (request.getParameter("offset") != null)
                offset = Integer.parseInt(request.getParameter("offset"));
        } catch (Exception e) {
            // Stay at default value for offset
        }
        try {
            if (request.getParameter("max") != null)
                max = Integer.parseInt(request.getParameter("max"));
        } catch (Exception e) {
            // Stay at default value for max
        }

        query.setMaxResults(max);
        query.setFirstResult(offset);

        return query;
    }

    protected Query buildCountQuery(CriteriaBuilder cb, CriteriaQuery criteriaCount, Root rootCount, Predicate[] predArray) {
        criteriaCount.select(cb.count(rootCount));
        criteriaCount.where(predArray);

        Query query = em.createQuery(criteriaCount);

        return query;
    }
}
