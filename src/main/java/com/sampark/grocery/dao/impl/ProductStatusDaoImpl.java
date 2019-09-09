package com.sampark.grocery.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.sampark.grocery.dao.ProductStatusDao;
import com.sampark.grocery.model.ProductStatusEntity;

public class ProductStatusDaoImpl implements ProductStatusDao{

	@PersistenceContext
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	
	@Override
	@Transactional
	public Boolean updateStatus(ProductStatusEntity pStatus) {
		getEntityManager().merge(pStatus);
		return true;
	}

	@Override
	public Boolean createStatus(ProductStatusEntity pStatus) {
		getEntityManager().persist(pStatus);
		return true;
	}

	
}
