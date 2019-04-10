package com.sampark.grocery.dao;

import java.sql.Timestamp;
import java.util.List;

import com.sampark.grocery.model.CustomerMerchantEntity;
import com.sampark.grocery.model.ProductAdervtiesment;
import com.sampark.grocery.model.UsersEntity;

public interface UserDao {
	public UsersEntity getUserByPhone(String phone);

	public UsersEntity getuserByEmail(String emailId);

	public List<UsersEntity> getCustomerByName(String name);

	public Boolean isUserExist(String phone);

	public Boolean updateUser(UsersEntity user);

	public Boolean createUser(UsersEntity user);

	public UsersEntity getallmerchhantdetails(Integer merchantuserid);

	public Boolean updateCustomerDetails(Integer userid, String address1, String address2, String city, String state,
			String pincode, String fname, String lname, String phone1, String email, String storename, Double lat,
			Double lng,String startshoptime,String endshoptime,String sponsorby);

	public Boolean saveCustomerforMerchant(CustomerMerchantEntity customerMerchantEntity);
	
	public Boolean updateCustomerMerchantSatus(Integer id, String status);
	
	public Boolean updateWallet(Integer id, String wallet);

	public Boolean ismerchantcustomersaveExist(Integer merchantid, Integer customerid);
	
	public CustomerMerchantEntity ismerchantcustomer(Integer merchantid, Integer customerid);

	public List<CustomerMerchantEntity> getCustomerMerchnat(Integer merchantid);
	
	public List<CustomerMerchantEntity> getPendingCustomerMerchnat(Integer merchantid);
	
    public List<CustomerMerchantEntity> getMerchnatCustomer(Integer customerid);
	
	public List<CustomerMerchantEntity> getPendingMerchnatCustomer(Integer customerid);

	public Boolean updateUserImage(Integer userid, String imagename);
	
	public Boolean deleteUserImage(Integer userid);

	public List<UsersEntity> getNearestmerchantDetail(UsersEntity user, Float radius);

	public List<UsersEntity> getAllMerchnatsforStorename();
	
	public List<ProductAdervtiesment> getNearestmerchantAdvertiesment(Integer merchnatid);
	
	public List<UsersEntity> getUserBysearch(String name, Integer roleid);
	public List<UsersEntity> getUserBysearchemail(String emailid, Integer roleid);
	
}
