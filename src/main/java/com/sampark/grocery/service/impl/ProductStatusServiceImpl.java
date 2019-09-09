package com.sampark.grocery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sampark.grocery.dao.ProductStatusDao;
import com.sampark.grocery.model.ProductStatusEntity;
import com.sampark.grocery.service.ProductStatusService;

public class ProductStatusServiceImpl implements ProductStatusService{

	@Autowired
	ProductStatusDao pStatusDao;
	
	@Override
	public Boolean updateStatus(ProductStatusEntity pstatus) {
		// TODO Auto-generated method stub
		return pStatusDao.updateStatus(pstatus);
	}

	@Override
	public Boolean createStatus(ProductStatusEntity pstatus) {
		// TODO Auto-generated method stub
		return pStatusDao.createStatus(pstatus);
	}

}
