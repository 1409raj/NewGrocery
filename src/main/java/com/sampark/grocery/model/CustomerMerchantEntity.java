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
	
	@Column(name="send_user_id")
	private Integer senduserid;
	
	@Column(name="recive_user_id")
	private Integer reciveuserid;
	
	@Column(name="status")
	private String status;
	
	@Column(name="send_userid_status")
	private String sendstatus;
	
	@Column(name="recive_userid_status")
	private String recivestatus;
	
	@Column(name="created_at")
	private Date createdAt;
	
	@Column(name="updated_at")
	private Date updatedAt;

	@ManyToOne(optional = false)
	@JoinColumn(name="user_id",referencedColumnName="user_id",insertable=false,updatable=false)
	private UsersEntity usersEntity;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="merchant_id",referencedColumnName="user_id",insertable=false,updatable=false)
	private UsersEntity usersEntityM;

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

	public Integer getSenduserid() {
		return senduserid;
	}

	public void setSenduserid(Integer senduserid) {
		this.senduserid = senduserid;
	}

	public Integer getReciveuserid() {
		return reciveuserid;
	}

	public void setReciveuserid(Integer reciveuserid) {
		this.reciveuserid = reciveuserid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSendstatus() {
		return sendstatus;
	}

	public void setSendstatus(String sendstatus) {
		this.sendstatus = sendstatus;
	}

	public String getRecivestatus() {
		return recivestatus;
	}

	public void setRecivestatus(String recivestatus) {
		this.recivestatus = recivestatus;
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

	public UsersEntity getUsersEntityM() {
		return usersEntityM;
	}

	public void setUsersEntityM(UsersEntity usersEntityM) {
		this.usersEntityM = usersEntityM;
	}

	@Override
	public String toString() {
		return "CustomerMerchantEntity [rowId=" + rowId + ", merchantid=" + merchantid + ", customerid=" + customerid
				+ ", senduserid=" + senduserid + ", reciveuserid=" + reciveuserid + ", status=" + status
				+ ", sendstatus=" + sendstatus + ", recivestatus=" + recivestatus + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", usersEntity=" + usersEntity + ", usersEntityM=" + usersEntityM + "]";
	}

	
	
}
