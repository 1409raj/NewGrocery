package com.sampark.grocery.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.sampark.grocery.dao.UnitsDao;
import com.sampark.grocery.model.UnitsEntity;

@Repository
public class UnitsDaoImpl implements UnitsDao {

	@PersistenceContext
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UnitsEntity> getUnitsList() {
		List<UnitsEntity> list= new ArrayList<UnitsEntity>();
		String sqlQuery = "Select * from grocerydb.units";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery,UnitsEntity.class);
			list = (ArrayList<UnitsEntity>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	
	}
}
