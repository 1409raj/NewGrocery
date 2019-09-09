package com.sampark.grocery.model;

import java.sql.Timestamp;

public class ProductsModel {

private Integer productid;

private String productname;

private String desccription;

private String status;

private String imageid;

private String add_status;

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

public String getDesccription() {
	return desccription;
}

public void setDesccription(String desccription) {
	this.desccription = desccription;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getImageid() {
	return imageid;
}

public void setImageid(String imageid) {
	this.imageid = imageid;
}

public String getAdd_status() {
	return add_status;
}

public void setAdd_status(String add_status) {
	this.add_status = add_status;
}

@Override
public String toString() {
	return "ProductsModel [productid=" + productid + ", productname=" + productname + ", desccription=" + desccription
			+ ", status=" + status + ", imageid=" + imageid + ", add_status=" + add_status + "]";
}



}