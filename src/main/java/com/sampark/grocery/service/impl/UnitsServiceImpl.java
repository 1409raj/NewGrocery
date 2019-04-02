package com.sampark.grocery.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sampark.grocery.dao.ProductDao;
import com.sampark.grocery.dao.UnitsDao;
import com.sampark.grocery.model.Domain;
import com.sampark.grocery.model.ProductCategoryEntity;
import com.sampark.grocery.model.ProductImageEntity;
import com.sampark.grocery.model.ProductPriceEntity;
import com.sampark.grocery.model.ProductsEntity;
import com.sampark.grocery.model.UnitsEntity;
import com.sampark.grocery.service.ProductService;
import com.sampark.grocery.service.UnitsService;

@Service
public class UnitsServiceImpl implements UnitsService {

	@Autowired
	UnitsDao dao;
	
	@Override
	public  Domain<List<UnitsEntity>> getUnitsList() {
		
		List<UnitsEntity> list= new ArrayList<UnitsEntity>();
		Domain<List<UnitsEntity>> domain = new Domain<List<UnitsEntity>>();
		list=dao.getUnitsList();
		if (list.size()>0) {
			domain.setObject(list);
			domain.setMessage("Units list is not empty.");
			domain.setHasError(false);
		} else {
			domain.setMessage("Units does not exist.");
			domain.setHasError(true);
		}
		// TODO Auto-generated method stub
		return domain;
	}


}