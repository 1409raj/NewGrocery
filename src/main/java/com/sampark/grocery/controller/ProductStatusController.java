package com.sampark.grocery.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sampark.grocery.model.ProductStatusEntity;
import com.sampark.grocery.service.ProductStatusService;

@Controller
@RequestMapping(value = "/status")
public class ProductStatusController {

	ProductStatusService pService;
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> updateStatus(@RequestBody ProductStatusEntity pStatus){
		pService.updateStatus(pStatus);
		return new ResponseEntity<>("SUCCESS",HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> createStatus(@RequestBody ProductStatusEntity pStatus) {
		pService.createStatus(pStatus);
		return new ResponseEntity<>("SUCCESS",HttpStatus.OK);
	}
}
