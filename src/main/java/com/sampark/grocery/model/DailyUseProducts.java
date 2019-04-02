package com.sampark.grocery.model;

public class DailyUseProducts {

	
	private Integer productid;
	private String name;
	private String description;
	private String productimagepath;
	private ProductUnitsWeightEntity productUnitsWeightEntity;
	private ProductPriceEntity productPriceEntity;
	private Integer merchantid;
	private String merchantfirstname;
	private String merchantlastname;
	private String merchantemailid;
	private String storename;
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProductimagepath() {
		return productimagepath;
	}
	public void setProductimagepath(String productimagepath) {
		this.productimagepath = productimagepath;
	}
	
	public ProductUnitsWeightEntity getProductUnitsWeightEntity() {
		return productUnitsWeightEntity;
	}
	public void setProductUnitsWeightEntity(ProductUnitsWeightEntity productUnitsWeightEntity) {
		this.productUnitsWeightEntity = productUnitsWeightEntity;
	}
	public ProductPriceEntity getProductPriceEntity() {
		return productPriceEntity;
	}
	public void setProductPriceEntity(ProductPriceEntity productPriceEntity) {
		this.productPriceEntity = productPriceEntity;
	}
	public Integer getMerchantid() {
		return merchantid;
	}
	public void setMerchantid(Integer merchantid) {
		this.merchantid = merchantid;
	}
	public String getMerchantfirstname() {
		return merchantfirstname;
	}
	public void setMerchantfirstname(String merchantfirstname) {
		this.merchantfirstname = merchantfirstname;
	}
	public String getMerchantlastname() {
		return merchantlastname;
	}
	public void setMerchantlastname(String merchantlastname) {
		this.merchantlastname = merchantlastname;
	}
	public String getMerchantemailid() {
		return merchantemailid;
	}
	public void setMerchantemailid(String merchantemailid) {
		this.merchantemailid = merchantemailid;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	
	
}
