package com.sampark.grocery.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="track_Order")
public class TrackOrderEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="order_cart_id")
	private Integer ordercartid;
	@Column(name="order_id")
	private String orderid;
	@Column(name="status_id")
	private Integer status_id;
	@Column(name = "created_on")
	private Date crearteOn;
	@Column(name = "updated_on")
	private Date updatedOn;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrdercartid() {
		return ordercartid;
	}
	public void setOrdercartid(Integer ordercartid) {
		this.ordercartid = ordercartid;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public Integer getStatus_id() {
		return status_id;
	}
	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
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
		return "TrackOrderEntity [id=" + id + ", ordercartid=" + ordercartid + ", orderid=" + orderid + ", status_id="
				+ status_id + ", crearteOn=" + crearteOn + ", updatedOn=" + updatedOn + "]";
	}
	
	
}
