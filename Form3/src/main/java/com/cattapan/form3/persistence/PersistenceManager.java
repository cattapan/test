package com.cattapan.form3.persistence;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ManagedBean(eager = true)
@ApplicationScoped
public class PersistenceManager {

    private static EntityManagerFactory emFactory = null;
    private static final Logger logger = LogManager.getLogger(PersistenceManager.class);

    @PostConstruct
    public void init() {
	emFactory = Persistence.createEntityManagerFactory("Form3PU");
	logger.info("Entity Manager Factory created");
    }

    public PersistenceManager() {

    }

    @PreDestroy
    public void close() {
	logger.info("Closing the Entity Manager Factory");
	emFactory.close();
    }

    public EntityManager getEntityManager() {
	EntityManager entityManager = emFactory.createEntityManager();
	return entityManager;

    }

}
