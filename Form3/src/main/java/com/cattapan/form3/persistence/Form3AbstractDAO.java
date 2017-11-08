package com.cattapan.form3.persistence;

import javax.persistence.EntityManager;

public class Form3AbstractDAO {

    private PersistenceManager pm = new PersistenceManager();

    protected EntityManager getEntityManager() {
	return pm.getEntityManager();
    }

}
