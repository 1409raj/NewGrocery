package com.sampark.grocery.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sampark.grocery.dao.RoleDao;
import com.sampark.grocery.model.RolesEntity;;

@Repository
public class RoleDaoImpl implements RoleDao {

	@PersistenceContext
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<RolesEntity> getRoleForMobile() {
		List<RolesEntity> list= new ArrayList<RolesEntity>();
		String sqlQuery = "Select * from grocerydb.roles where forMobileApp = true";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery,RolesEntity.class);
				list = (ArrayList<RolesEntity>) query.getResultList();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	@Transactional
	public Boolean updateRole(RolesEntity user) {
		getEntityManager().merge(user);
		return true;
	}

	@Override
	@Transactional
	public Boolean createRole(RolesEntity user) {
		getEntityManager().persist(user);
		return true;
	}

}
