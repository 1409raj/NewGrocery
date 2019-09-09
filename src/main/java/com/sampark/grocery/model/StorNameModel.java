package com.sampark.grocery.model;

import java.sql.Timestamp;

public class StorNameModel {

	private Integer merchantid;
	private String merchantfirstname;
	private String merchantlastname;
	private String merchantemailid;
	private String storename;
	private Double lat;
	private Double lng;
	private String sponsorby;
	private String imagepath;
	private String startshoptime;
	private String endshoptime;
	private Integer product_status;
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
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public String getSponsorby() {
		return sponsorby;
	}
	public void setSponsorby(String sponsorby) {
		this.sponsorby = sponsorby;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public String getStartshoptime() {
		return startshoptime;
	}
	public void setStartshoptime(String startshoptime) {
		this.startshoptime = startshoptime;
	}
	public String getEndshoptime() {
		return endshoptime;
	}
	public void setEndshoptime(String endshoptime) {
		this.endshoptime = endshoptime;
	}
	public Integer getProduct_status() {
		return product_status;
	}
	public void setProduct_status(Integer product_status) {
		this.product_status = product_status;
	}
	@Override
	public String toString() {
		return "StorNameModel [merchantid=" + merchantid + ", merchantfirstname=" + merchantfirstname
				+ ", merchantlastname=" + merchantlastname + ", merchantemailid=" + merchantemailid + ", storename="
				+ storename + ", lat=" + lat + ", lng=" + lng + ", sponsorby=" + sponsorby + ", imagepath=" + imagepath
				+ ", startshoptime=" + startshoptime + ", endshoptime=" + endshoptime + ", product_status="
				+ product_status + "]";
	}
	
	
}
