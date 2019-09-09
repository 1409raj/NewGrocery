package com.sampark.grocery.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sampark.grocery.dao.VendorDao;
import com.sampark.grocery.model.Domain;
import com.sampark.grocery.model.ProductCategoryEntity;
import com.sampark.grocery.model.VendorEntity;
import com.sampark.grocery.model.VendorMerchantEntity;
import com.sampark.grocery.service.VendorService;
@Service
public class VendorServiceImpl implements VendorService {
	
	@Autowired
	VendorDao vdao;
	
	@Override
	public Domain<String> createVendor(VendorEntity vendor) {
		Domain<String> s = new Domain<String>();
		
		if(vdao.isvendorExist(vendor.getPhone())) {
			s.setMessage("Mobile number already exist.");
			s.setHasError(true);
		} else {
			vdao.createVendor(vendor);
			s.setMessage("vendor saved successfully.");
			s.setHasError(false);			
		}
		return s;
	}
	@Override
	public Domain<List<VendorEntity>> getVendorList(Integer merchantId) {
		List<VendorEntity> list= new ArrayList<VendorEntity>();
		Domain<List<VendorEntity>> domain = new Domain<List<VendorEntity>>();
		list=vdao.getVendorList(merchantId);
		if (list.size()>0) {
			domain.setObject(list);
			domain.setMessage("vendor list is not empty.");
			domain.setHasError(false);
		} else {
			domain.setMessage(" vendor list not exist.");
			domain.setHasError(true);
		}
		// TODO Auto-generated method stub
		return domain;
	}
	@Override
	public Domain<List<VendorEntity>> getVendorListByMerchantId(Integer merchantId) {
		List<VendorMerchantEntity> list= new ArrayList<VendorMerchantEntity>();
		Domain<List<VendorEntity>> domain = new Domain<List<VendorEntity>>();
		VendorMerchantEntity bean=new VendorMerchantEntity();
		list=vdao.getVendorListByMerchantId(merchantId);
		VendorEntity vendor= new VendorEntity();
		List<VendorEntity> list1=new ArrayList<VendorEntity>();
		Iterator<VendorMerchantEntity> it=list.iterator();
		while (it.hasNext()) {
			bean=it.next();
			vendor=vdao.getVendorDetails(bean.getVendorid());
			list1.add(vendor);
		}
		domain.setObject(list1);
		domain.setMessage("vendor list is not empty.");
		domain.setHasError(false);
		return domain;
	}
	@Override
	public Domain<String> saveVendorforMerchant(VendorMerchantEntity vendormerchant) {
		     Domain<String> s = new Domain<String>();
			if(vdao.saveVendorforMerchant(vendormerchant))
			{
				s.setMessage("vendor saved successfully.");
				s.setHasError(false);		
			}
			else
			{
				s.setMessage("vendor not saved successfully.");
				s.setHasError(true);		
			}
		return s;
	}
}
