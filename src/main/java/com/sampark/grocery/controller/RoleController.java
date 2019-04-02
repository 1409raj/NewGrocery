package com.sampark.grocery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sampark.grocery.model.Domain;
import com.sampark.grocery.model.RolesEntity;
import com.sampark.grocery.service.RoleService;

@Controller
@RequestMapping(value = "/role")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value = "/getroleformobile", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<RolesEntity>>> getRoleForMobile(){
		Domain<List<RolesEntity>> role = roleService.getRoleForMobile();
		
		return new ResponseEntity<>(role,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> updateUser(@RequestBody RolesEntity user){
		roleService.updateRole(user);
		return new ResponseEntity<>("SUCCESS",HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> createUser(@RequestBody RolesEntity role) {
		roleService.createRole(role);
		return new ResponseEntity<>("SUCCESS",HttpStatus.OK);
	}
	
}
