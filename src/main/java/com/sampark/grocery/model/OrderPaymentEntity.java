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
@Table(name="Order_Payment")
public class OrderPaymentEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name = "order_id")
	private String orderid;
	
	@Column(name = "merchant_id")
	private Integer merchantid;
	
	@Column(name = "customer_user_id")
	private Integer customeruserid;
	
	@Column(name = "order_type")
	private String ordertype;
	
	@Column(name = "payment_transaction_id")
	private String transactionid;
	
	@Column(name = "order_price")
	private String orderprice;
	
	@Column(name = "created_on")
	private Date createdOn;
	
	@Column(name = "updated_on")
	private Date updatedOn;

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderid() {
		return orderid;
	}


	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}


	public Integer getMerchantid() {
		return merchantid;
	}


	public void setMerchantid(Integer merchantid) {
		this.merchantid = merchantid;
	}


	public Integer getCustomeruserid() {
		return customeruserid;
	}


	public void setCustomeruserid(Integer customeruserid) {
		this.customeruserid = customeruserid;
	}


	public String getOrdertype() {
		return ordertype;
	}


	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}


	public String getTransactionid() {
		return transactionid;
	}


	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}


	public String getOrderprice() {
		return orderprice;
	}


	public void setOrderprice(String orderprice) {
		this.orderprice = orderprice;
	}


	public Date getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}


	public Date getUpdatedOn() {
		return updatedOn;
	}


	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}


	@Override
	public String toString() {
		return "OrderPaymentEntity [id=" + id + ", orderid=" + orderid + ", merchantid=" + merchantid
				+ ", customeruserid=" + customeruserid + ", ordertype=" + ordertype + ", transactionid=" + transactionid
				+ ", orderprice=" + orderprice + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + "]";
	}


	
	
	
	/*@ManyToOne(optional = false)
	@JoinColumn(name="merchant_id",referencedColumnName="user_id",insertable=false,updatable=false)
	private UsersEntity usersEntity;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="customer_user_id",referencedColumnName="user_id",insertable=false,updatable=false)
	private UsersEntity userscustomerEntity;*/
	
	
	
	

}
