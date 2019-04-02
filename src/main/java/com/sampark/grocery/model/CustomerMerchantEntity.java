package com.sampark.grocery.model;

import java.sql.Timestamp;
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
@Table(name="merchant_customer")
public class CustomerMerchantEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="row_id")
	private Integer rowId;
	
	@Column(name="merchant_id")
	private Integer merchantid;
	
	@Column(name="user_id")
	private Integer customerid;
	
	@Column(name="status")
	private String status;
	
	@Column(name="created_at")
	private Date createdAt;
	
	@Column(name="updated_at")
	private Date updatedAt;

	@ManyToOne(optional = false)
	@JoinColumn(name="user_id",referencedColumnName="user_id",insertable=false,updatable=false)
	private UsersEntity usersEntity;
	
	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public Integer getMerchantid() {
		return merchantid;
	}

	public void setMerchantid(Integer merchantid) {
		this.merchantid = merchantid;
	}

	public Integer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public UsersEntity getUsersEntity() {
		return usersEntity;
	}

	public void setUsersEntity(UsersEntity usersEntity) {
		this.usersEntity = usersEntity;
	}

	@Override
	public String toString() {
		return "CustomerMerchantEntity [rowId=" + rowId + ", merchantid=" + merchantid + ", customerid=" + customerid
				+ ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", usersEntity="
				+ usersEntity + "]";
	}
	
	
}
