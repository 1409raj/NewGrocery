package com.sampark.grocery.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_status")
public class OrderStatusEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="status_id")
	private Integer statusid;
	@Column(name="order_status")
	private String orderstatus;
	@Column(name = "created_on")
	private Date creartedOn;
	@Column(name = "updated_on")
	private Date updatedOn;
	public Integer getStatusid() {
		return statusid;
	}
	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
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
	@Override
	public String toString() {
		return "OrderStatusEntity [statusid=" + statusid + ", orderstatus=" + orderstatus + ", creartedOn=" + creartedOn
				+ ", updatedOn=" + updatedOn + "]";
	}
	
	

}
