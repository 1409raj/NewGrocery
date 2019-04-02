package com.sampark.grocery.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sampark.grocery.dao.RoleDao;
import com.sampark.grocery.model.Domain;
import com.sampark.grocery.model.RolesEntity;
import com.sampark.grocery.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDao dao;
	
	@Override
	public Domain<List<RolesEntity>> getRoleForMobile() {
		List<RolesEntity> list = new ArrayList<RolesEntity>();
		Domain<List<RolesEntity>> response = new Domain<List<RolesEntity>>();
		list = dao.getRoleForMobile();
		response.setObject(list);
		response.setHasError(false);
		response.setMessage("Valid");
		return response;
	}

	@Override
	public Boolean updateRole(RolesEntity Role) {
		
		return dao.updateRole(Role);
	}

	@Override
	public Boolean createRole(RolesEntity Role) {
		
		return dao.createRole(Role);
	}
	
}