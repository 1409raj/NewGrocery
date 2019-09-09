package com.sampark.grocery.dao;

import java.util.List;

import com.sampark.grocery.model.VendorEntity;
import com.sampark.grocery.model.VendorMerchantEntity;

public interface VendorDao {
	
	public Boolean createVendor(VendorEntity vendor);
	public List<VendorEntity> getVendorList(Integer merchantId);
	public Boolean isvendorExist(String phone);
	public Boolean saveVendorforMerchant(VendorMerchantEntity vendormerchant);
	public VendorEntity getVendorDetails(Integer vendorid);
	public List<VendorMerchantEntity> getVendorListByMerchantId(Integer merchantId);

}
