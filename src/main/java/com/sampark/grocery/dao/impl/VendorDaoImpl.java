package com.sampark.grocery.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sampark.grocery.dao.VendorDao;
import com.sampark.grocery.model.UsersEntity;
import com.sampark.grocery.model.VendorEntity;
import com.sampark.grocery.model.VendorMerchantEntity;
import com.sampark.grocery.util.CommonUtil;
@Repository
public class VendorDaoImpl  implements VendorDao{
	
	
	@PersistenceContext
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	@Transactional
	public Boolean createVendor(VendorEntity vendor) {
		entityManager.persist(vendor);
		if(vendor.getVendorId()>0)
		{
		  return true;	
		}
		else
		{
			return false;
		}	
	}
	@Override
	public List<VendorEntity> getVendorList(Integer merchantId) {
		List<VendorEntity> list= new ArrayList<VendorEntity>();
		String sqlQuery = "SELECT * from vendor WHERE vendor_id Not in (SELECT vendor_id FROM merchant_vendor where merchant_id =:merchantId)";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery,VendorEntity.class);
			query.setParameter("merchantId",merchantId);
			list = (ArrayList<VendorEntity>) query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	
	}
	@Override
	public List<VendorMerchantEntity> getVendorListByMerchantId(Integer merchantId) {
		List<VendorMerchantEntity> list= new ArrayList<VendorMerchantEntity>();
		String sqlQuery = "Select * from grocerydb.merchant_vendor where merchant_id=:merchantId";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery,VendorMerchantEntity.class);
			query.setParameter("merchantId",merchantId);
			list = (ArrayList<VendorMerchantEntity>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public Boolean isvendorExist(String phone) {
		Boolean found= false;
		String sqlQuery = "Select * from grocerydb.vendor where phone=:phone";
		Query query = null;
		try {
			try {
				VendorEntity bean = new VendorEntity();
				query = getEntityManager().createNativeQuery(sqlQuery,VendorEntity.class);
				query.setParameter("phone", phone);
				bean = (VendorEntity) query.getSingleResult();			
				if(!CommonUtil.isNull(bean.getPhone())) {
					found = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return found;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return found;
	}

	@Override
	@Transactional
	public Boolean saveVendorforMerchant(VendorMerchantEntity vendormerchant) {
		entityManager.persist(vendormerchant);
		if(vendormerchant.getRowId()>0)
		{
		  return true;	
		}
		else
		{
			return false;
		}	
	}

	@Override
	public VendorEntity getVendorDetails(Integer vendorid) {
		VendorEntity bean= new VendorEntity();
		String sqlQuery = "Select * from grocerydb.vendor where vendor_id=:vendorid";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery,VendorEntity.class);
			query.setParameter("vendorid", vendorid);
			bean = (VendorEntity) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;	
	}
}
