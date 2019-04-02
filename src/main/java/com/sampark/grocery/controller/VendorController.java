package com.sampark.grocery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sampark.grocery.model.Domain;
import com.sampark.grocery.model.ProductsEntity;
import com.sampark.grocery.model.VendorEntity;
import com.sampark.grocery.model.VendorMerchantEntity;
import com.sampark.grocery.service.VendorService;

@Controller
@RequestMapping(value="/vendor")
public class VendorController {
	@Autowired
	VendorService vservice;
	
	@RequestMapping(value = "/createvendor", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<String>> createVendor(@RequestBody VendorEntity  vendor) {
		Domain<String> vendors= vservice.createVendor(vendor);
		return new ResponseEntity<>(vendors,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getvendorlist", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<VendorEntity>>> getVendorList(@RequestParam("merchantid") String merchantId) {
		int ids=Integer.parseInt(merchantId); 
		Domain<List<VendorEntity>> entity = vservice.getVendorList(ids);
		return new ResponseEntity<Domain<List<VendorEntity>>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getvendorlistbyid", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<VendorEntity>>> getVendorListByMerchantId(@RequestParam("merchantid") String merchantId) {
		int ids=Integer.parseInt(merchantId);  
		Domain<List<VendorEntity>> entity = vservice.getVendorListByMerchantId(ids);
		return new ResponseEntity<Domain<List<VendorEntity>>>(entity,HttpStatus.OK);
	}
	@RequestMapping(value = "/savevendorformerchant",method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<String>> saveVendorForMerchant(@RequestParam("merchantid") Integer merchantId,@RequestParam("vendorid") Integer vendorId) {
		
		VendorMerchantEntity vendormerchant = new VendorMerchantEntity();
		vendormerchant.setMerchantid(merchantId);
		vendormerchant.setVendorid(vendorId);
		vendormerchant.setStatus("Y");
		Domain<String> vendors= vservice.saveVendorforMerchant(vendormerchant);
		return new ResponseEntity<>(vendors,HttpStatus.OK);
	}
}
