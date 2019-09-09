package com.sampark.grocery.model;

import java.util.List;

public class MerchantUpdateQuantity {

	private List<MerchantPurchaseProductDetailEList> merchantPurchaseProductDetailEList;

	public List<MerchantPurchaseProductDetailEList> getMerchantPurchaseProductDetailEList() {
		return merchantPurchaseProductDetailEList;
	}

	public void setMerchantPurchaseProductDetailEList(
			List<MerchantPurchaseProductDetailEList> merchantPurchaseProductDetailEList) {
		this.merchantPurchaseProductDetailEList = merchantPurchaseProductDetailEList;
	}

	public static class MerchantPurchaseProductDetailEList {

		private Boolean checked;

		private Integer id;

		private String merchantID;

		private String price;

		private String productID;

		private Integer productPrimary;

		private String quantity;

		private String unitName;

		private String weight;
		
		private String vendorId;

		private String weightID;

		public Boolean getChecked() {
			return checked;
		}

		public void setChecked(Boolean checked) {
			this.checked = checked;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getMerchantID() {
			return merchantID;
		}

		public void setMerchantID(String merchantID) {
			this.merchantID = merchantID;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getProductID() {
			return productID;
		}

		public void setProductID(String productID) {
			this.productID = productID;
		}

		public Integer getProductPrimary() {
			return productPrimary;
		}

		public void setProductPrimary(Integer productPrimary) {
			this.productPrimary = productPrimary;
		}

		public String getQuantity() {
			return quantity;
		}

		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}

		public String getUnitName() {
			return unitName;
		}

		public void setUnitName(String unitName) {
			this.unitName = unitName;
		}

		public String getWeight() {
			return weight;
		}

		public void setWeight(String weight) {
			this.weight = weight;
		}

		public String getWeightID() {
			return weightID;
		}

		public void setWeightID(String weightID) {
			this.weightID = weightID;
		}
		public String getVendorId() {
			return vendorId;
		}

		public void setVendorId(String vendorId) {
			this.vendorId = vendorId;
		}

		@Override
		public String toString() {
			return "MerchantPurchaseProductDetailEList [checked=" + checked + ", id=" + id + ", merchantID="
					+ merchantID + ", price=" + price + ", productID=" + productID + ", productPrimary="
					+ productPrimary + ", quantity=" + quantity + ", unitName=" + unitName + ", weight=" + weight
					+ ", vendorId=" + vendorId + ", weightID=" + weightID + "]";
		}


	}
	
	

}
