package com.sampark.grocery.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sampark.grocery.constant.ApiConstants;
import com.sampark.grocery.dao.UserDao;
import com.sampark.grocery.model.CustomerMerchant;
import com.sampark.grocery.model.CustomerMerchantEntity;
import com.sampark.grocery.model.Domain;
import com.sampark.grocery.model.SearchCustomer;
import com.sampark.grocery.model.StorNameModel;
import com.sampark.grocery.model.UsersEntity;
import com.sampark.grocery.model.userAddressDetails;
import com.sampark.grocery.service.UserService;
import com.sampark.grocery.util.CommonUtil;
import com.sampark.grocery.util.EncryptionUtil;
import com.sampark.grocery.util.RandomText;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao dao;

	@Override
	public Domain<UsersEntity> getUserByPhone(String phone, String password) {
		UsersEntity user = new UsersEntity();
		Domain<UsersEntity> response = new Domain<UsersEntity>();
		user = dao.getUserByPhone(phone);
		if (CommonUtil.isNull(user.getPhone1())) {
			response.setMessage("User Id or password is invalid");
			response.setHasError(true);
		} else {
			String decryptedPass = EncryptionUtil.decrypt(user.getPasswd());
			if (decryptedPass.equals(password)) {
				user.setImagepath(ApiConstants.server_url+"images?image="+user.getImagename()+"&folder=profileimage");
				 String jwtToken = Jwts.builder().setSubject(phone).claim("roles", "user").setIssuedAt(new Date())
			                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
				 user.setAuthToken(jwtToken);
				response.setObject(user);
				response.getObject().setPasswd("");
				response.setMessage("Login is successful.");
				response.setHasError(false);
				
				
			} else {
				response.setMessage("User Id or password is invalid.");
				response.setHasError(true);
			}
		}

		return response;
	}

	@Override
	public Domain<UsersEntity> getuserByEmail(String emailId, String password) {
		UsersEntity user = new UsersEntity();
		Domain<UsersEntity> response = new Domain<UsersEntity>();
		user = dao.getuserByEmail(emailId);
		if (CommonUtil.isNull(user.getPhone1())) {
			response.setMessage("User Id or password is invalid");
			response.setHasError(true);
		} else {
			String decryptedPass = EncryptionUtil.decrypt(user.getPasswd());
			if (decryptedPass.equals(password)) {
				response.setObject(user);
				response.getObject().setPasswd("");
				response.setMessage("Login is successful.");
				response.setHasError(false);
			} else {
				response.setMessage("User Id or password is invalid.");
				response.setHasError(true);
			}
		}
		return response;
	}

	@Override
	public Domain<String> updateUser(UsersEntity user) {
		String password = EncryptionUtil.encrypt(user.getPasswd());
		user.setPasswd(password);
		Domain<String> s = new Domain<String>();
		
		if(dao.isUserExist(user.getPhone1())) {
			s.setMessage("Mobile number already exist.");
			s.setHasError(true);
		} else {
			dao.updateUser(user);
			s.setMessage("Data saved successfully.");
			s.setHasError(false);			
		}
		return s;
		
	}

	@Override
	public Domain<String> createUser(UsersEntity user) {
		String password = EncryptionUtil.encrypt(user.getPasswd());
		user.setPasswd(password);
		Domain<String> s = new Domain<String>();
		
		if(dao.isUserExist(user.getPhone1())) {
			s.setMessage("Mobile number already exist.");
			s.setHasError(true);
		} else {
			dao.createUser(user);
			s.setMessage("Data saved successfully.");
			s.setHasError(false);			
		}
		return s;
	}

	@Override
	public Domain<String> generateToken(String phone) {
		String random = RandomText.generateRND();
		String message = "The one time password genereted is " + random + ". This will be valid for 10 minutes.";
		/*String resourceUrl = "http://125.63.102.130:9099/SMSWeb/outbound.do?mobile=" + phone + "&message=" + message;*/
		RestTemplate restTemplate = new RestTemplate();
		
		Domain<String> domain = new Domain<String>();
		
		if(dao.isUserExist(phone)) {
			domain.setMessage("Mobile number already exist.");
			domain.setHasError(true);
		} else {
			/*ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
			if (response.getStatusCodeValue() == 200) {
				domain.setObject(random);
				domain.setMessage("OTP sent to mobile number " + phone +". This will be valid for 10 minutes.");
				domain.setHasError(false);
			} else {
				domain.setMessage("Message could not be sent");
				domain.setHasError(true);
			}*/
			domain.setObject(random);
			domain.setMessage("OTP sent to mobile number " + phone +". This will be valid for 10 minutes.");
			domain.setHasError(true);
		}
		return domain;
	}


	@Override
	public Domain<List<UsersEntity>> getCustomerByName(String name) {
		
		List<UsersEntity> list = new ArrayList<UsersEntity>();
		UsersEntity usersEntity = new UsersEntity();
		Domain<List<UsersEntity>> domain = new Domain<List<UsersEntity>>();
		
		List customerList = new ArrayList<>();
	
		list = dao.getCustomerByName(name);

//		list.stream().forEach((UsersEntity) ->{System.out.println(UsersEntity);
//		});
		
		Iterator<UsersEntity> it = list.iterator();
		while (it.hasNext()) {
			usersEntity =  it.next();
			SearchCustomer searchCustomer = new SearchCustomer();
			searchCustomer.setCustomerid(usersEntity.getUserId());
			searchCustomer.setCustomerfirstname(usersEntity.getFirstName());
			searchCustomer.setCustomerlastname(usersEntity.getLastName());
			searchCustomer.setCustomeremailid(usersEntity.getEmailId());
			searchCustomer.setAddressLine1(usersEntity.getAddressLine1());
			searchCustomer.setAddressLine2(usersEntity.getAddressLine2());
			searchCustomer.setCity(usersEntity.getCity());
			searchCustomer.setState(usersEntity.getState());
			searchCustomer.setPincode(usersEntity.getPincode());
			searchCustomer.setImagepath(ApiConstants.server_url+"images?image="+usersEntity.getImagename()+"&folder=profileimage");
			customerList.add(searchCustomer);
			
		}
		
		if (list.size()>0) {
			
			domain.setObject(customerList);
			domain.setMessage("Customer list");
			domain.setHasError(false);
		} else {
			domain.setMessage("customer does not exist");
			domain.setHasError(true);
		}

		return domain;
		}

	@Override
	public Domain<String> saveCustomerforMerchant(CustomerMerchantEntity customerMerchantEntity) {
		  Domain<String> domain = new Domain<String>();
		   
		  if(dao.ismerchantcustomersaveExist(customerMerchantEntity.getMerchantid(), customerMerchantEntity.getCustomerid())) {
				domain.setMessage("Customer already add.");
				domain.setHasError(true);
			} else {
			         if(dao.saveCustomerforMerchant(customerMerchantEntity))
			        {
				    domain.setMessage("Customer saved successfully.");
				    domain.setHasError(false);		
			       }
			      else
			    {
				   domain.setMessage("customer not saved");
				   domain.setHasError(true);		
			   }
			}
		return domain;
	}

	@Override
	public Domain<List<CustomerMerchantEntity>> getCustomerMerchnat(Integer merchantid) {
		
		List<CustomerMerchantEntity> list = new ArrayList<CustomerMerchantEntity>();
		CustomerMerchantEntity customerList = new CustomerMerchantEntity();
		Domain<List<CustomerMerchantEntity>> domain = new Domain<List<CustomerMerchantEntity>>();
		
		List customerMerchantList = new ArrayList<>();
	
		list = dao.getCustomerMerchnat(merchantid);
		Iterator<CustomerMerchantEntity> it = list.iterator();
		while (it.hasNext()) {
			customerList =  it.next();
			CustomerMerchant customerMerchant = new CustomerMerchant();
			customerMerchant.setRowid(customerList.getRowId());
			customerMerchant.setCustomerid(customerList.getUsersEntity().getUserId());
			customerMerchant.setStatus(customerList.getStatus());
			customerMerchant.setCustomerfirstname(customerList.getUsersEntity().getFirstName());
			customerMerchant.setCustomerlastname(customerList.getUsersEntity().getLastName());
			customerMerchant.setCustomeremailid(customerList.getUsersEntity().getEmailId());
			customerMerchant.setAddressLine1(customerList.getUsersEntity().getAddressLine1());
			customerMerchant.setAddressLine2(customerList.getUsersEntity().getAddressLine2());
			customerMerchant.setCity(customerList.getUsersEntity().getCity());
			customerMerchant.setState(customerList.getUsersEntity().getState());
			customerMerchant.setPincode(customerList.getUsersEntity().getPincode());
			customerMerchantList.add(customerMerchant);
		}
		
		if (list.size()>0) {
			
			domain.setObject(customerMerchantList);
			domain.setMessage("Customer list");
			domain.setHasError(false);
		} else {
			domain.setMessage("customer does not exist");
			domain.setHasError(true);
		}

		return domain;
		}

	@Override
	public Domain<userAddressDetails> getalluserdetails(Integer merchantuserid) {
		UsersEntity user = new UsersEntity();
		Domain<userAddressDetails> domain = new Domain<userAddressDetails>();
		
		user = dao.getallmerchhantdetails(merchantuserid);
		userAddressDetails userAddressDetails = new userAddressDetails();
		
		userAddressDetails.setUserId(user.getUserId());
		userAddressDetails.setFirstName(user.getFirstName());
		userAddressDetails.setLastName(user.getLastName());
		userAddressDetails.setAddressLine1(user.getAddressLine1());
		userAddressDetails.setAddressLine2(user.getAddressLine2());
		userAddressDetails.setEmailId(user.getEmailId());
		userAddressDetails.setStorename(user.getStorename());
		
		userAddressDetails.setPhone1(user.getPhone1());
		userAddressDetails.setState(user.getState());
		userAddressDetails.setCity(user.getCity());
		userAddressDetails.setPincode(user.getPincode());
		userAddressDetails.setLat(user.getLat());
		userAddressDetails.setLng(user.getLng());
		userAddressDetails.setStartshoptime(user.getStartshoptime());
		userAddressDetails.setEndshoptime(user.getEndshoptime());
		userAddressDetails.setSponsorby(user.getSponsorby());
		userAddressDetails.setImagename(user.getImagename());
		userAddressDetails.setImagepath(ApiConstants.server_url+"images?image="+user.getImagename()+"&folder=profileimage");
		
        if (user.getUserId()!=null) {
        	
			domain.setObject(userAddressDetails);
			domain.setMessage("users list");
			domain.setHasError(false);
			
		} else {
			
			domain.setMessage("user does not exist");
			domain.setHasError(true);
			
		}

		return domain;
		}

	@Override
	public Domain<String> updateUserDetails(userAddressDetails userAddressDetails) {
		
         Domain<String> domain = new Domain<String>();
 
         if(dao.updateCustomerDetails(userAddressDetails.getUserId(), userAddressDetails.getAddressLine1(), userAddressDetails.getAddressLine2(),
        		 userAddressDetails.getCity(), userAddressDetails.getState(), userAddressDetails.getPincode(),
        		 userAddressDetails.getFirstName(),userAddressDetails.getLastName(),userAddressDetails.getPhone1(),
        		 userAddressDetails.getEmailId(),userAddressDetails.getStorename(),userAddressDetails.getLat(),userAddressDetails.getLng(),userAddressDetails.getStartshoptime(),userAddressDetails.getEndshoptime(),userAddressDetails.getSponsorby())) {
 			domain.setMessage("Profile Updated Successfully");
 			domain.setHasError(false);
 		} else {
 			domain.setMessage("Profile not updated");
 			domain.setHasError(true);			
 		}
 					
		return domain;

	}

	@Override
	public Domain<String> updateUserImage(String userid, String imagename) {
		 Domain<String> domain = new Domain<String>();
		 Domain<userAddressDetails> domain2 = new Domain<userAddressDetails>();
         if(dao.updateUserImage(Integer.parseInt(userid), imagename)) {
        	 domain2=getalluserdetails(Integer.parseInt(userid));
        	 domain2.getObject().getImagepath();
        	 domain.setObject(domain2.getObject().getImagepath());
        	 domain.setMessage("Image Updated Successfully");
 			domain.setHasError(false);
 		} else {
 			domain.setMessage("Image not updated");
 			domain.setHasError(true);			
 		}
 					
		return domain;
	}
	
	@Override
	public Domain<String> deleteUserImage(Integer userid) {
		Domain<String> domain = new Domain<String>();
        if(dao.deleteUserImage(userid)) {
       	
       	    domain.setMessage("Image Delete Successfully");
			domain.setHasError(false);
		} else {
			domain.setMessage("not Delete");
			domain.setHasError(true);			
		}
					
		return domain;
	}
		

	@Override
	public Domain<List<UsersEntity>> getAllMerchnatsforStorename() {
		List<UsersEntity> list= new ArrayList<UsersEntity>();
		Domain<List<UsersEntity>> domain = new Domain<List<UsersEntity>>();
		UsersEntity usersEntity = new UsersEntity();
		List Storelist = new ArrayList<>();
		list=dao.getAllMerchnatsforStorename();
		Iterator<UsersEntity> it = list.iterator();
		while (it.hasNext()) {
			usersEntity =  it.next();
			StorNameModel storNameModel = new StorNameModel();
			
			storNameModel.setMerchantid(usersEntity.getUserId());
			storNameModel.setMerchantfirstname(usersEntity.getFirstName());
			storNameModel.setMerchantlastname(usersEntity.getLastName());
			storNameModel.setMerchantemailid(usersEntity.getEmailId());
			storNameModel.setStorename(usersEntity.getStorename());
			storNameModel.setStartshoptime(usersEntity.getStartshoptime());
			storNameModel.setEndshoptime(usersEntity.getEndshoptime());
			storNameModel.setLat(usersEntity.getLat());
			storNameModel.setLng(usersEntity.getLng());
			storNameModel.setSponsorby(usersEntity.getSponsorby());
			storNameModel.setImagepath(ApiConstants.server_url+"images?image="+usersEntity.getImagename()+"&folder=profileimage");
			Storelist.add(storNameModel);
		}
		if (list.size()>0) {
			domain.setObject(Storelist);
			domain.setMessage("list is not empty.");
			domain.setHasError(false);
		} else {
			domain.setMessage("does not exist.");
			domain.setHasError(true);
		}
		// TODO Auto-generated method stub
		return domain;
	}

	@Override
	public Domain<List<UsersEntity>> getuserbysearch(String usersearch, Integer roleid) {
		
		        List<UsersEntity> list = new ArrayList<UsersEntity>();
		        Domain<List<UsersEntity>> domain = new Domain<List<UsersEntity>>();
		        UsersEntity user = new UsersEntity();
		        List usersearchlist = new ArrayList<>();
		        if(usersearch.contains("@")) {
		        	list = dao.getUserBysearchemail(usersearch,roleid);
		        	Iterator<UsersEntity> it = list.iterator();
		    		while (it.hasNext()) {
		    			user =  it.next();
		    			userAddressDetails userAddressDetails = new userAddressDetails();
		    			
		    			userAddressDetails.setUserId(user.getUserId());
		    			userAddressDetails.setFirstName(user.getFirstName());
		    			userAddressDetails.setLastName(user.getLastName());
		    			userAddressDetails.setAddressLine1(user.getAddressLine1());
		    			userAddressDetails.setAddressLine2(user.getAddressLine2());
		    			userAddressDetails.setEmailId(user.getEmailId());
		    			userAddressDetails.setStorename(user.getStorename());
		    			
		    			userAddressDetails.setPhone1(user.getPhone1());
		    			userAddressDetails.setState(user.getState());
		    			userAddressDetails.setCity(user.getCity());
		    			userAddressDetails.setPincode(user.getPincode());
		    			userAddressDetails.setLat(user.getLat());
		    			userAddressDetails.setLng(user.getLng());
		    			userAddressDetails.setStartshoptime(user.getStartshoptime());
		    			userAddressDetails.setEndshoptime(user.getEndshoptime());
		    			userAddressDetails.setSponsorby(user.getSponsorby());
		    			userAddressDetails.setImagename(user.getImagename());
		    			userAddressDetails.setImagepath(ApiConstants.server_url+"images?image="+user.getImagename()+"&folder=profileimage");
		    			usersearchlist.add(userAddressDetails);
		    		}
                    if (list.size()>0) {
		    			
		    			domain.setObject(usersearchlist);
		    			domain.setMessage("success");
		    			domain.setHasError(false);
		    		} else {
		    			domain.setMessage("does not exist");
		    			domain.setHasError(true);
		    		}
                    
		        }
		        else {
		        	
		    		list = dao.getUserBysearch(usersearch,roleid);
		    		Iterator<UsersEntity> it = list.iterator();
		    		while (it.hasNext()) {
		    			user =  it.next();
		    			userAddressDetails userAddressDetails = new userAddressDetails();
		    			
		    			userAddressDetails.setUserId(user.getUserId());
		    			userAddressDetails.setFirstName(user.getFirstName());
		    			userAddressDetails.setLastName(user.getLastName());
		    			userAddressDetails.setAddressLine1(user.getAddressLine1());
		    			userAddressDetails.setAddressLine2(user.getAddressLine2());
		    			userAddressDetails.setEmailId(user.getEmailId());
		    			userAddressDetails.setStorename(user.getStorename());
		    			
		    			userAddressDetails.setPhone1(user.getPhone1());
		    			userAddressDetails.setState(user.getState());
		    			userAddressDetails.setCity(user.getCity());
		    			userAddressDetails.setPincode(user.getPincode());
		    			userAddressDetails.setLat(user.getLat());
		    			userAddressDetails.setLng(user.getLng());
		    			userAddressDetails.setStartshoptime(user.getStartshoptime());
		    			userAddressDetails.setEndshoptime(user.getEndshoptime());
		    			userAddressDetails.setSponsorby(user.getSponsorby());
		    			userAddressDetails.setImagename(user.getImagename());
		    			userAddressDetails.setImagepath(ApiConstants.server_url+"images?image="+user.getImagename()+"&folder=profileimage");
		    			usersearchlist.add(userAddressDetails);
		    		}
		    		if (list.size()>0) {
		    			
		    			domain.setObject(usersearchlist);
		    			domain.setMessage("success");
		    			domain.setHasError(false);
		    		} else {
		    			domain.setMessage("does not exist");
		    			domain.setHasError(true);
		    		}

		    		 
		        }
		        return domain;
	}



}