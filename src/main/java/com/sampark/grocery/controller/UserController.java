package com.sampark.grocery.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sampark.grocery.constant.ApiConstants;
import com.sampark.grocery.constant.ResponseObject;
import com.sampark.grocery.model.CustomerMerchantEntity;
import com.sampark.grocery.model.Domain;
import com.sampark.grocery.model.UsersEntity;
import com.sampark.grocery.model.userAddressDetails;
import com.sampark.grocery.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	
	@RequestMapping(value = "/authenticatebyphone", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<UsersEntity>> getUserByPhone(@RequestParam("phone") String phone, 
			@RequestParam("password") String password) {
		System.out.println(phone+""+password);
		
		
		Domain<UsersEntity> entity = userService.getUserByPhone(phone,password);
		
		logger.error(phone);
		logger.info("In login");
		
	
		return new ResponseEntity<>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/authenticatebyemail", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<UsersEntity>> getuserByEmail(@RequestParam("email") String email,
			@RequestParam("password") String password){
		System.out.println("email:"+email);
		Domain<UsersEntity> entity = userService.getuserByEmail(email,password);
		return new ResponseEntity<>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/generatetoken", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<String>> generateToken(@RequestParam("phone") String phone){
		Domain<String> domain = new Domain<String>();
		domain = userService.generateToken(phone);
		return new ResponseEntity<>(domain,HttpStatus.OK);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<String>> createUser(@RequestBody UsersEntity user) {
		Domain<String> users= userService.createUser(user);
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<String>> updateUser(@RequestBody UsersEntity user){
		userService.updateUser(user);
		Domain<String> s = new Domain<String>();
		s.setObject(new String("Success"));
		s.setMessage("Data saved successfully");
		s.setHasError(false);
		
		
		return new ResponseEntity<>(s,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateUserDetail", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<String>> updateUser(@RequestBody userAddressDetails userAddressDetails){
		
		Domain<String> domain = userService.updateUserDetails(userAddressDetails);
		return new ResponseEntity<>(domain,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCustomerlistbyname", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<UsersEntity>>> getProductByCategoryName(@RequestParam("name") String name) {
		Domain<List<UsersEntity>> entity = userService.getCustomerByName(name);
		return new ResponseEntity<Domain<List<UsersEntity>>>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/savecustomerformerchant",method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<String>> savecustomerForMerchant(@RequestParam("merchantid") Integer merchantId,
			@RequestParam("customerid") Integer customerid,
			@RequestParam("sentuserid") Integer senduserid) {
		Date date = new Date();
		CustomerMerchantEntity customerMerchantEntity = new CustomerMerchantEntity();
		customerMerchantEntity.setMerchantid(merchantId);
		customerMerchantEntity.setCustomerid(customerid);
		customerMerchantEntity.setSenduserid(senduserid);
		customerMerchantEntity.setStatus("P");
		customerMerchantEntity.setCreatedAt(date);
		
		Domain<String> customers= userService.saveCustomerforMerchant(customerMerchantEntity);
		return new ResponseEntity<>(customers,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getConnectedCustomerlistforMerchant", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<CustomerMerchantEntity>>> getConnectedCustomerlistforMerchant(@RequestParam("merchantid") String merchantid) {
		Domain<List<CustomerMerchantEntity>> entity = userService.getCustomerMerchnat(Integer.parseInt(merchantid));
		return new ResponseEntity<Domain<List<CustomerMerchantEntity>>>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getPendingCustomerlistforMerchant", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<CustomerMerchantEntity>>> getPendingCustomerlistforMerchant(@RequestParam("merchantid") String merchantid) {
		Domain<List<CustomerMerchantEntity>> entity = userService.getPendingCustomerMerchnat(Integer.parseInt(merchantid));
		return new ResponseEntity<Domain<List<CustomerMerchantEntity>>>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getConnectedMerchantlistforCustomer", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<CustomerMerchantEntity>>> getConnectedMerchantlistforCustomer(@RequestParam("customerid") String customerid) {
		Domain<List<CustomerMerchantEntity>> entity = userService.getMerchnatCustomer(Integer.parseInt(customerid));
		return new ResponseEntity<Domain<List<CustomerMerchantEntity>>>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getPendingMerchantlistforCustomer", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<CustomerMerchantEntity>>> getCustomerlistforMerchant(@RequestParam("customerid") String customerid) {
		Domain<List<CustomerMerchantEntity>> entity = userService.getPendingMerchnatCustomer(Integer.parseInt(customerid));
		return new ResponseEntity<Domain<List<CustomerMerchantEntity>>>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getuserAlldetails", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<userAddressDetails>> getAlldetails(@RequestParam("id") String id) {
		int ids=Integer.parseInt(id);  
		Domain<userAddressDetails> entity = userService.getalluserdetails(ids);
		return new ResponseEntity<Domain<userAddressDetails>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value="/userImage",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<String>> saveProduct(
			@RequestParam(value="userid",required=false) String userid,
			@RequestParam(value="image",required=false) MultipartFile files)
	
	
	{
		Domain<String> domain = new Domain<String>();
		Date date = new Date();
		String fname = null;
		 fname = UUID.randomUUID().toString()
					+ files.getOriginalFilename().substring(files.getOriginalFilename().lastIndexOf('.'));
		
		try {
		byte[] bytes11 = files.getBytes();
		File dir11 = new File(ApiConstants.profile_image_path);
		if (!dir11.exists()){
			dir11.mkdirs();
		}						
		File serverFile11 = new File(dir11.getAbsolutePath()
				+ File.separator + fname);						
		
		 domain = userService.updateUserImage(userid, fname);
	     
		if(domain.getHasError().equals(false))
		{
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(serverFile11));
			stream.write(bytes11);
			
			stream.close();                                                  
			
        System.out.println("files uploaded successfully");
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		
		}
		return new ResponseEntity<>(domain,HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value="/userImageDelete",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<String>> deleteImage(
			@RequestParam("userid") Integer userid,
			@RequestParam("imagename") String imagename)
	
	
	{
		Domain<String> domain = new Domain<String>();
		Date date = new Date();
		
		try {
			  File file = new File(ApiConstants.profile_image_path+"/"+ imagename);
	             if(file.delete()) { 
	                System.out.println(file.getName() + " is deleted!");
	             } else {
	                System.out.println("Delete operation is failed.");
	                }
	             
	             domain = userService.deleteUserImage(userid);
	    	     
	     		if(domain.getHasError().equals(false))
	     		{
	             System.out.println("files Delete successfully");
	     		}

		}
		catch (Exception e) {
			e.printStackTrace();
		
		}
		return new ResponseEntity<>(domain,HttpStatus.OK);
		
		
	}
	
	
	@RequestMapping(value = "/getMerchantStorelist", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<UsersEntity>>> getCategoryList() {
	
		Domain<List<UsersEntity>> entity = userService.getAllMerchnatsforStorename();
		return new ResponseEntity<Domain<List<UsersEntity>>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value="/authentication/failed",method=RequestMethod.POST)
	public @ResponseBody  ResponseEntity<Object>   error()
	{
		ResponseObject rsObject=new ResponseObject();
		rsObject.setHasError(true);;
		rsObject.setObject("Token Expired or Invalid Token");
		return new ResponseEntity<Object>(rsObject,HttpStatus.UNAUTHORIZED);
		
	}
	
	
	@RequestMapping(value="/authentication/notfound",method=RequestMethod.POST)
	public @ResponseBody  ResponseEntity<Object>   notFound()
	{
		ResponseObject rsObject=new ResponseObject();
		rsObject.setHasError(true);;
		rsObject.setObject("Header Not Found");
		return new ResponseEntity<Object>(rsObject,HttpStatus.UNAUTHORIZED);
		
	}
	
	@RequestMapping(value = "/getUsersearch", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<UsersEntity>>> getUsersearch(
			@RequestParam("usersearch") String usersearch,
			@RequestParam("roleid") String roleid,
			@RequestParam("id") String id
			) {
		int rid=Integer.parseInt(roleid);
		int cmid=Integer.parseInt(id);
		Domain<List<UsersEntity>> entity = userService.getuserbysearch(usersearch, rid,cmid);
		return new ResponseEntity<Domain<List<UsersEntity>>>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateCMrelation", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<String>> updateCustomerMerchantSatus(
			@RequestParam("rowid") String id,
			@RequestParam("status") String status){
		int rid=Integer.parseInt(id);
		Domain<String> domain = userService.updateCustomerMerchantSatus(rid, status);
		return new ResponseEntity<>(domain,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateWallet", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<String>> updateCMWallet(
			@RequestParam("rowid") String id,
			@RequestParam("wallet") String wallet){
		int rid=Integer.parseInt(id);
		Domain<String> domain = userService.updateWallet(rid, wallet);
		return new ResponseEntity<>(domain,HttpStatus.OK);
	}
}
