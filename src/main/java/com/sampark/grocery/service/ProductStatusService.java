package com.sampark.grocery.service;


import com.sampark.grocery.model.ProductStatusEntity;

public interface ProductStatusService {
  
//	public Domain<List<ProductStatusEntity>> getRoleForMobile();
	public Boolean updateStatus(ProductStatusEntity pstatus);
	public Boolean createStatus(ProductStatusEntity pstatus);
}
