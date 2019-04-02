package com.sampark.grocery.service;

import java.util.List;

import com.sampark.grocery.model.Domain;
import com.sampark.grocery.model.RolesEntity;

public interface RoleService {
	public Domain<List<RolesEntity>> getRoleForMobile();
	public Boolean updateRole(RolesEntity Role);
	public Boolean createRole(RolesEntity Role);
}
