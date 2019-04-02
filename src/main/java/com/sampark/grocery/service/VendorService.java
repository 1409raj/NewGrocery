package com.sampark.grocery.service;

import java.util.List;

import com.sampark.grocery.model.Domain;
import com.sampark.grocery.model.VendorEntity;
import com.sampark.grocery.model.VendorMerchantEntity;

public interface VendorService {
	
	public Domain<String> createVendor(VendorEntity vendor);
	public Domain<List<VendorEntity>> getVendorList(Integer merchantId);
	public Domain<String> saveVendorforMerchant(VendorMerchantEntity vendormerchant);
	public Domain<List<VendorEntity>> getVendorListByMerchantId(Integer merchantId);


}
