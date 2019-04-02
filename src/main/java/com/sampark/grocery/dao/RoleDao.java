package com.sampark.grocery.dao;

import java.util.List;

import com.sampark.grocery.model.RolesEntity;

public interface RoleDao {
	public List<RolesEntity> getRoleForMobile();
	public Boolean updateRole(RolesEntity Role);
	public Boolean createRole(RolesEntity Role);
}
