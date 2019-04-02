package com.sampark.grocery.service;

import java.util.List;

import com.sampark.grocery.model.CustomerMerchantEntity;
import com.sampark.grocery.model.Domain;
import com.sampark.grocery.model.UsersEntity;
import com.sampark.grocery.model.userAddressDetails;

public interface UserService {
	public Domain<UsersEntity> getUserByPhone(String phone,String password);
	public Domain<UsersEntity> getuserByEmail(String email,String password);
	public Domain<String> generateToken(String phone);
	public Domain<String> updateUser(UsersEntity user);
	public Domain<String> createUser(UsersEntity user);
	public Domain<List<UsersEntity>> getCustomerByName(String name);
	public Domain<String> saveCustomerforMerchant(CustomerMerchantEntity customerMerchantEntity);
	public Domain<List<CustomerMerchantEntity>> getCustomerMerchnat(Integer merchantid);
	
	public Domain<userAddressDetails> getalluserdetails(Integer merchantuserid);
	public Domain<String> updateUserDetails(userAddressDetails userAddressDetails);
	
	public Domain<String> updateUserImage(String userid,String imagename);
	public Domain<String> deleteUserImage(Integer userid);
	public Domain<List<UsersEntity>> getAllMerchnatsforStorename();
	
	public Domain<List<UsersEntity>> getuserbysearch(String usersearch,Integer roleid);
}
