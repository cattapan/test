package com.cattapan.form3.persistence;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cattapan.form3.pojo.Payment;

public class PaymentDAO extends Form3AbstractDAO {

    private static final Logger logger = LogManager.getLogger(PaymentDAO.class);

    // TODO throw DAO exceptions to the business layer
    public Payment create(Payment p) {
	logger.debug("Creating payment");
	EntityManager em = this.getEntityManager();
	try {
	    em.getTransaction().begin();
	    em.persist(p);
	    em.flush();
	    em.getTransaction().commit();
	    return p;
	} catch (Exception e) {
	    logger.error("Error creating Payment", e);
	    return null;
	} finally {
	    try {
		em.close();
	    } catch (Exception e) {
		logger.warn("Error closing entity manager", e);
	    }
	}
    }

    public boolean delete(Payment p) {
	logger.debug("Deleting payment " + p);
	EntityManager em = this.getEntityManager();
	try {
	    em.getTransaction().begin();
	    em.remove(em.contains(p) ? p : em.merge(p));
	    em.flush();
	    em.getTransaction().commit();
	    return true;
	} catch (Exception e) {
	    logger.error("Error creating Payment", e);
	} finally {
	    try {
		em.close();
	    } catch (Exception e) {
		logger.warn("Error closing entity manager", e);
	    }
	}
	return false;
    }

    public Payment update(Payment p) {
	logger.debug("updating payment");
	String id = p.getId();

	if (null != id && !id.isEmpty()) {

	    EntityManager em = this.getEntityManager();
	    try {
		em.getTransaction().begin();
		 
		if (this.find(id) == null) {
		    logger.info("Update failed. Payment not found. Id: " + id);
		    return null;
		}
		em.merge(p);
		em.flush();
		em.getTransaction().commit();
		return p;
	    } catch (Exception e) {
		logger.error("Error updating Payment", e);
	    } finally {
		try {
		    em.close();
		} catch (Exception e) {
		    logger.warn("Error closing entity manager", e);
		}
	    }
	}
	return null;
    }

    public Payment find(String id) {
	logger.debug("Loading payment with id " + id);
	EntityManager em = this.getEntityManager();
	try {
	    return em.find(Payment.class, id);
	} catch (Exception e) {
	    logger.error("Error finding Payment with id", e);
	} finally {
	    try {
		em.close();
	    } catch (Exception e) {
		logger.warn("Error closing entity manager", e);
	    }
	}
	return null;

    }

    @SuppressWarnings("unchecked")
    public Collection<Payment> findAll() {
	logger.debug("Loading all payments from DB");
	EntityManager em = this.getEntityManager();
	try {
	    Query query = em.createQuery("SELECT p FROM Payment p");
	    return (Collection<Payment>) query.getResultList();
	} catch (Exception e) {
	    logger.error("Error finding Payments by Org", e);
	} finally {
	    try {
		em.close();
	    } catch (Exception e) {
		logger.warn("Error closing entity manager", e);
	    }
	}
	return Collections.EMPTY_LIST;
    }
    @SuppressWarnings("unchecked")
    public Collection<Payment> findByOrganisation(String orgId, int maxResult, int startPosition) {

	logger.debug("Loading payments with criteria like");
	EntityManager em = this.getEntityManager();
	try {

	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Payment> q = cb.createQuery(Payment.class);
	    Root<Payment> c = q.from(Payment.class);
	    CriteriaQuery<Payment> q2 = q.select(c).where(cb.equal(c.get("organisationId"), orgId));
	    TypedQuery<Payment> query = em.createQuery(q2).setMaxResults(maxResult).setFirstResult(startPosition);
	    return query.getResultList();
	} catch (Exception e) {
	    logger.error("Error finding Payments by Org", e);
	} finally {
	    try {
		em.close();
	    } catch (Exception e) {
		logger.warn("Error closing entity manager", e);
	    }
	}
	return Collections.EMPTY_LIST;

    }
}