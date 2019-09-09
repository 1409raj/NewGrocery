package com.sampark.grocery.model;

import java.sql.Timestamp;

public class userAddressDetails {
	
	private Integer userId;
	private Integer rowid;
	private String firstName;
	private String lastName;
	private String phone1;
	private String emailId;
	private String storename;
	private String addressLine1;
	private String addressLine2;
	private String frndstatus;
	private String city;
	private String state;
	private String pincode;
	private Double lat;
	private Double lng;
	private String imagename;
	private String imagepath;
	private String sponsorby;
	private String startshoptime;
	private String endshoptime;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getRowid() {
		return rowid;
	}
	public void setRowid(Integer rowid) {
		this.rowid = rowid;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getFrndstatus() {
		return frndstatus;
	}
	public void setFrndstatus(String frndstatus) {
		this.frndstatus = frndstatus;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
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
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public String getSponsorby() {
		return sponsorby;
	}
	public void setSponsorby(String sponsorby) {
		this.sponsorby = sponsorby;
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
	

}
