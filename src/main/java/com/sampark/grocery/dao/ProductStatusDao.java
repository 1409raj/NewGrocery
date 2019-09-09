package com.sampark.grocery.dao;

import com.sampark.grocery.model.ProductStatusEntity;

public interface ProductStatusDao {

	public Boolean updateStatus(ProductStatusEntity pStatus);
	public Boolean createStatus(ProductStatusEntity pStatus);
}
