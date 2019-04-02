package com.sampark.grocery.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="merchant_vendor")
public class VendorMerchantEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="row_id")
	private Integer rowId;
	
	@Column(name="merchant_id")
	private Integer merchantid;
	
	@Column(name="vendor_id")
	private Integer vendorid;
	
	@Column(name="status")
	private String status;
	
	@Column(name="created_at")
	private Timestamp createdAt;
	
	@Column(name="updated_at")
	private Timestamp updatedAt;
	
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
	public Integer getVendorid() {
		return vendorid;
	}
	public void setVendorid(Integer vendorid) {
		this.vendorid = vendorid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	public String toString() {
		return "VendorMerchantEntity [rowId=" + rowId + ", merchantid=" + merchantid + ", vendorid=" + vendorid
				+ ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	
	
}
