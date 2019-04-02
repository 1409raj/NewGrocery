package com.sampark.grocery.model;

import java.util.Date;

public class MerchantOrder {
	
	private String orderid;
	private OrderStatusEntity statusid;
	private Integer weight;
	private Integer price;
	private String unit;
	private String quantity;
	
	private Date createtime;
	private Date ordacceptdate;
	private Date ordcanceldate;
	private Date ordoutfordate;
	private Date orddeliverdate;
	
	private Integer productid;
	private String productname;
	private String productdesc;
	private String productimagepath;
	
	private Integer customerid;
	private String customerfirstname;
	private String customerlastname;
	private String customeremailid;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String pincode;
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public OrderStatusEntity getStatusid() {
		return statusid;
	}
	public void setStatusid(OrderStatusEntity statusid) {
		this.statusid = statusid;
	}
	
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getOrdacceptdate() {
		return ordacceptdate;
	}
	public void setOrdacceptdate(Date ordacceptdate) {
		this.ordacceptdate = ordacceptdate;
	}
	public Date getOrdcanceldate() {
		return ordcanceldate;
	}
	public void setOrdcanceldate(Date ordcanceldate) {
		this.ordcanceldate = ordcanceldate;
	}
	public Date getOrdoutfordate() {
		return ordoutfordate;
	}
	public void setOrdoutfordate(Date ordoutfordate) {
		this.ordoutfordate = ordoutfordate;
	}
	public Date getOrddeliverdate() {
		return orddeliverdate;
	}
	public void setOrddeliverdate(Date orddeliverdate) {
		this.orddeliverdate = orddeliverdate;
	}
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductdesc() {
		return productdesc;
	}
	public void setProductdesc(String productdesc) {
		this.productdesc = productdesc;
	}
	public String getProductimagepath() {
		return productimagepath;
	}
	public void setProductimagepath(String productimagepath) {
		this.productimagepath = productimagepath;
	}
	public Integer getCustomerid() {
		return customerid;
	}
	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}
	public String getCustomerfirstname() {
		return customerfirstname;
	}
	public void setCustomerfirstname(String customerfirstname) {
		this.customerfirstname = customerfirstname;
	}
	public String getCustomerlastname() {
		return customerlastname;
	}
	public void setCustomerlastname(String customerlastname) {
		this.customerlastname = customerlastname;
	}
	public String getCustomeremailid() {
		return customeremailid;
	}
	public void setCustomeremailid(String customeremailid) {
		this.customeremailid = customeremailid;
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
	
	

}
