package com.sampark.grocery.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="products_Adertiesment")
public class ProductAdervtiesment {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="advert_id")
	private Integer advertid;
	@Column(name="merchant_id")
	private Integer merchantid;
	@Column(name="status")
	private String status;
	@Column(name="image_name")
	private String imagename;
	@Column(name = "created_on")
	private Date creartedOn;
	@Column(name = "updated_on")
	private Date updatedOn;
	
	@Transient
	private String imagepath;
	
	public Integer getAdvertid() {
		return advertid;
	}
	public void setAdvertid(Integer advertid) {
		this.advertid = advertid;
	}
	public Integer getMerchantid() {
		return merchantid;
	}
	public void setMerchantid(Integer merchantid) {
		this.merchantid = merchantid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public Date getCreartedOn() {
		return creartedOn;
	}
	public void setCreartedOn(Date creartedOn) {
		this.creartedOn = creartedOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	@Override
	public String toString() {
		return "ProductAdervtiesment [advertid=" + advertid + ", merchantid=" + merchantid + ", status=" + status
				+ ", imagename=" + imagename + ", creartedOn=" + creartedOn + ", updatedOn=" + updatedOn + "]";
	}
	
	

}
