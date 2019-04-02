package com.sampark.grocery.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart_order")
public class OrderCart {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_cart_id")
	private Integer ordercartid;
	
	@Column(name="customer_user_id")
	private Integer customeruserid;
	
	@Column(name="merchant_id")
	private Integer merchantid;
	
	@Column(name="order_status")
	private String orderstatus;
	@Column(name = "created_on")
	private Date crearteOn;
	@Column(name = "updated_on")
	private Date updatedOn;
	public Integer getOrdercartid() {
		return ordercartid;
	}
	public void setOrdercartid(Integer ordercartid) {
		this.ordercartid = ordercartid;
	}
	public Integer getCustomeruserid() {
		return customeruserid;
	}
	public void setCustomeruserid(Integer customeruserid) {
		this.customeruserid = customeruserid;
	}
	public Integer getMerchantid() {
		return merchantid;
	}
	public void setMerchantid(Integer merchantid) {
		this.merchantid = merchantid;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	public Date getCrearteOn() {
		return crearteOn;
	}
	public void setCrearteOn(Date crearteOn) {
		this.crearteOn = crearteOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	@Override
	public String toString() {
		return "OrderCart [ordercartid=" + ordercartid + ", customeruserid=" + customeruserid + ", merchantid="
				+ merchantid + ", orderstatus=" + orderstatus + ", crearteOn=" + crearteOn + ", updatedOn=" + updatedOn
				+ "]";
	}

}
