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
import javax.persistence.Transient;

@Entity
@Table(name="merchant_products")
public class MerchantProducts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "row_id")
	private Integer rowid;

	@Column(name = "merchant_id")
	private Integer merchantid;

	@Column(name = "product_id")
	private Integer productid;
	
	@Column(name = "category_id")
	private Integer categoryid;
	
	@Column(name = "image_id")
	private Integer imageid;
	
	@Column(name = "unit_id")
	private Integer unitid;
	
	@Column(name = "price_id")
	private Integer priceid;
	
	@Column(name = "vendor_id")
	private Integer vendorid;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "no_of_purchase")
	private Integer noOfpurchase;
	
	@Column(name="created_at")
	private Date createdOn;
	@Column(name="updated_at")
	private Date updatedOn;
	
	@Transient
	private String imagepath;
	
	@Transient
	private String arraystringweight;
	
	@Transient
	private String arraystringwprice;
	
	@Transient
	private String arraystringwunit;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="merchant_id",referencedColumnName="user_id",insertable=false,updatable=false)
	private UsersEntity merchantDetails;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="product_id",referencedColumnName="product_id",insertable=false,updatable=false)
	private ProductsEntity prEntity;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="unit_id",referencedColumnName="row_id",insertable=false,updatable=false)
	private ProductUnitsWeightEntity prWeightEntity;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="price_id",referencedColumnName="price_id",insertable=false,updatable=false)
	private ProductPriceEntity prPriceEntity;
	
	
	public Integer getRowid() {
		return rowid;
	}

	public void setRowid(Integer rowid) {
		this.rowid = rowid;
	}

	public Integer getMerchantid() {
		return merchantid;
	}

	public void setMerchantid(Integer merchantid) {
		this.merchantid = merchantid;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}
 

	public ProductsEntity getPrEntity() {
		return prEntity;
	}

	public void setPrEntity(ProductsEntity prEntity) {
		this.prEntity = prEntity;
	}

	
	public Integer getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}

	public Integer getImageid() {
		return imageid;
	}

	public void setImageid(Integer imageid) {
		this.imageid = imageid;
	}

	public Integer getUnitid() {
		return unitid;
	}

	public void setUnitid(Integer unitid) {
		this.unitid = unitid;
	}
	
	public ProductUnitsWeightEntity getPrWeightEntity() {
		return prWeightEntity;
	}

	public void setPrWeightEntity(ProductUnitsWeightEntity prWeightEntity) {
		this.prWeightEntity = prWeightEntity;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	
	public Integer getPriceid() {
		return priceid;
	}

	public void setPriceid(Integer priceid) {
		this.priceid = priceid;
	}
	
	public Integer getVendorid() {
		return vendorid;
	}

	public void setVendorid(Integer vendorid) {
		this.vendorid = vendorid;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getNoOfpurchase() {
		return noOfpurchase;
	}

	public void setNoOfpurchase(Integer noOfpurchase) {
		this.noOfpurchase = noOfpurchase;
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

	public ProductPriceEntity getPrPriceEntity() {
		return prPriceEntity;
	}

	public void setPrPriceEntity(ProductPriceEntity prPriceEntity) {
		this.prPriceEntity = prPriceEntity;
	}
	public String getArraystringweight() {
		return arraystringweight;
	}

	public void setArraystringweight(String arraystringweight) {
		this.arraystringweight = arraystringweight;
	}

	public String getArraystringwprice() {
		return arraystringwprice;
	}

	public void setArraystringwprice(String arraystringwprice) {
		this.arraystringwprice = arraystringwprice;
	}

	public String getArraystringwunit() {
		return arraystringwunit;
	}

	public void setArraystringwunit(String arraystringwunit) {
		this.arraystringwunit = arraystringwunit;
	}
	
	public UsersEntity getMerchantDetails() {
		return merchantDetails;
	}

	public void setMerchantDetails(UsersEntity merchantDetails) {
		this.merchantDetails = merchantDetails;
	}

	@Override
	public String toString() {
		return "MerchantProducts [rowid=" + rowid + ", merchantid=" + merchantid + ", productid=" + productid
				+ ", categoryid=" + categoryid + ", imageid=" + imageid + ", unitid=" + unitid + ", priceid=" + priceid
				+ ", vendorid=" + vendorid + ", quantity=" + quantity + ", noOfpurchase=" + noOfpurchase
				+ ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + ", imagepath=" + imagepath
				+ ", arraystringweight=" + arraystringweight + ", arraystringwprice=" + arraystringwprice
				+ ", arraystringwunit=" + arraystringwunit + ", merchantDetails=" + merchantDetails + ", prEntity="
				+ prEntity + ", prWeightEntity=" + prWeightEntity + ", prPriceEntity=" + prPriceEntity + "]";
	}


	
}
