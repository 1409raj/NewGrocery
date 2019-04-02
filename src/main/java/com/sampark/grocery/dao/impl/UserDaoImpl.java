package com.sampark.grocery.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sampark.grocery.dao.UserDao;
import com.sampark.grocery.model.CustomerMerchantEntity;
import com.sampark.grocery.model.ProductAdervtiesment;
import com.sampark.grocery.model.UsersEntity;
import com.sampark.grocery.util.CommonUtil;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public UsersEntity getUserByPhone(String phone) {
		UsersEntity bean = new UsersEntity();
		String sqlQuery = "Select * from grocerydb.users where phone1 = :phone";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, UsersEntity.class);
				query.setParameter("phone", phone);
				bean = (UsersEntity) query.getSingleResult();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UsersEntity getuserByEmail(String email) {
		UsersEntity bean = new UsersEntity();
		String sqlQuery = "Select * from grocerydb.users where email_id = :email";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, UsersEntity.class);
				query.setParameter("email", email);
				bean = (UsersEntity) query.getSingleResult();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public Boolean updateUser(UsersEntity user) {
		getEntityManager().merge(user);
		return true;
	}

	@Override
	@Transactional
	public Boolean createUser(UsersEntity user) {
		getEntityManager().persist(user);
		return true;
	}

	@Override
	public Boolean isUserExist(String phone) {
		Boolean found = false;
		String sqlQuery = "Select * from grocerydb.users where phone1 = :phone";
		Query query = null;
		try {
			try {
				UsersEntity bean = new UsersEntity();
				query = getEntityManager().createNativeQuery(sqlQuery, UsersEntity.class);
				query.setParameter("phone", phone);
				bean = (UsersEntity) query.getSingleResult();

				if (!CommonUtil.isNull(bean.getPhone1())) {
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
	public UsersEntity getallmerchhantdetails(Integer merchantuserid) {

		UsersEntity Merchantuserlist = new UsersEntity();
		String sqlQuery = "Select * from grocerydb.users where user_id = :merchantuserid";
		Query query = null;

		try {
			query = getEntityManager().createNativeQuery(sqlQuery, UsersEntity.class);
			query.setParameter("merchantuserid", merchantuserid);
			Merchantuserlist = (UsersEntity) query.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Merchantuserlist;

	}

	@Override
	@Transactional
	public Boolean updateCustomerDetails(Integer userid, String address1, String address2, String city, String state,
			String pincode, String fname, String lname, String phone1, String email, String storename, Double lat,
			Double lng,String startshoptime,String endshoptime,String sponsorby) {
		String sqlQuery = "update  grocerydb.users set address_line_1=:address1, address_line_2=:address2, city=:city, state=:state, pincode=:pincode, phone1=:phone1, first_name=:fname, last_name=:lname, email_id=:email,store_name=:storename, lat=:lat,lng=:lng,startshoptime=:startshoptime,endshoptime=:endshoptime,sponsorby=:sponsorby where user_id=:userid";

		Query query = null;
		query = getEntityManager().createNativeQuery(sqlQuery);
		query.setParameter("userid", userid);
		query.setParameter("address1", address1);
		query.setParameter("address2", address2);
		query.setParameter("city", city);
		query.setParameter("state", state);
		query.setParameter("pincode", pincode);
		query.setParameter("phone1", phone1);
		query.setParameter("fname", fname);
		query.setParameter("lname", lname);
		query.setParameter("email", email);
		query.setParameter("lat", lat);
		query.setParameter("lng", lng);
		query.setParameter("storename", storename);
		query.setParameter("startshoptime", startshoptime);
		query.setParameter("endshoptime", endshoptime);
		query.setParameter("sponsorby", sponsorby);

		int i = query.executeUpdate();
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<UsersEntity> getCustomerByName(String name) {
		List<UsersEntity> list = new ArrayList<UsersEntity>();
		String sqlQuery = "Select * from grocerydb.users where first_name LIKE:name and role_id='3'";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery, UsersEntity.class);
			query.setParameter("name", "%" + name + "%");
			list = (ArrayList<UsersEntity>) query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	@Transactional
	public Boolean saveCustomerforMerchant(CustomerMerchantEntity customerMerchantEntity) {
		entityManager.persist(customerMerchantEntity);
		if (customerMerchantEntity.getRowId() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean ismerchantcustomersaveExist(Integer merchantid, Integer customerid) {
		Boolean found = false;
		String sqlQuery = "Select * from grocerydb.merchant_customer where merchant_id = :merchantid and user_id=:customerid";
		Query query = null;
		try {
			try {
				CustomerMerchantEntity bean = new CustomerMerchantEntity();
				query = getEntityManager().createNativeQuery(sqlQuery, CustomerMerchantEntity.class);
				query.setParameter("merchantid", merchantid);
				query.setParameter("customerid", customerid);
				bean = (CustomerMerchantEntity) query.getSingleResult();

				if (!CommonUtil.isNull(bean.getCustomerid())) {
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
	public List<CustomerMerchantEntity> getCustomerMerchnat(Integer merchantid) {
		List<CustomerMerchantEntity> list = new ArrayList<CustomerMerchantEntity>();
		String sqlQuery = "Select * from grocerydb.merchant_customer where merchant_id =:merchantid";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery, CustomerMerchantEntity.class);
			query.setParameter("merchantid", merchantid);
			list = (ArrayList<CustomerMerchantEntity>) query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	@Transactional
	public Boolean updateUserImage(Integer userid, String imagename) {
		String sqlQuery = "update  grocerydb.users set image_name=:imagename where user_id=:userid";

		Query query = null;
		query = getEntityManager().createNativeQuery(sqlQuery);
		query.setParameter("userid", userid);
		query.setParameter("imagename", imagename);

		int i = query.executeUpdate();
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	@Transactional
	public Boolean deleteUserImage(Integer userid) {
		String sqlQuery = "update  grocerydb.users set image_name= NULL where user_id=:userid";

		Query query = null;
		query = getEntityManager().createNativeQuery(sqlQuery);
		query.setParameter("userid", userid);
		int i = query.executeUpdate();
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<UsersEntity> getNearestmerchantDetail(UsersEntity user, Float radius) {
		Double lat = user.getLat();
		Double lng = user.getLng();
		Float rad = radius;
		ArrayList<UsersEntity> NearMerchant = new ArrayList<UsersEntity>();
		String sqlQuery = "SELECT * FROM users WHERE acos(sin(" + lat + ") * sin(lat) + cos(" + lat
				+ ") * cos(lat) * cos(lng - (" + lng + "))) * 6371 <= "+ rad +" and role_id='2'";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, UsersEntity.class);
				// query.setParameter("lat", lat);
				// query.setParameter("lng", lng);
				NearMerchant = (ArrayList<UsersEntity>) query.getResultList();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return NearMerchant;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UsersEntity> getAllMerchnatsforStorename() {
		List<UsersEntity> list = new ArrayList<UsersEntity>();
		String sqlQuery = "Select * from grocerydb.users WHERE role_id='2'";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery, UsersEntity.class);
			list = (ArrayList<UsersEntity>) query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProductAdervtiesment> getNearestmerchantAdvertiesment(Integer merchnatid) {
		List<ProductAdervtiesment> list = new ArrayList<ProductAdervtiesment>();
		String sqlQuery = "Select * from grocerydb.products_Adertiesment WHERE status='Y' and merchant_id=:merchnatid";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery, ProductAdervtiesment.class);
			query.setParameter("merchnatid", merchnatid);
			list = (ArrayList<ProductAdervtiesment>) query.getResultList();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<UsersEntity> getUserBysearch(String name, Integer roleid) {
		List<UsersEntity> list = new ArrayList<UsersEntity>();
		String sqlQuery = "Select * from grocerydb.users where first_name LIKE:name and role_id=:roleid ";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery, UsersEntity.class);
			query.setParameter("roleid", roleid);
			query.setParameter("name", "%" + name + "%");
			list = (ArrayList<UsersEntity>) query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<UsersEntity> getUserBysearchemail(String emailid, Integer roleid) {
		List<UsersEntity> list = new ArrayList<UsersEntity>();
		String sqlQuery = "Select * from grocerydb.users where email_id =:emailid and role_id=:roleid ";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery, UsersEntity.class);
			query.setParameter("roleid", roleid);
			query.setParameter("emailid", emailid);
			list = (ArrayList<UsersEntity>) query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	

}
