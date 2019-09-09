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
@Table(name="products_cart")
public class ProductsCartEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cart_id")
	private Integer cartid;
	@Column(name="order_cart_id")
	private Integer ordercartid;
	@Column(name="product_id")
	private Integer productid;
	@Column(name="unit_weight_id")
	private Integer unitweightid;
	@Column(name="customer_user_id")
	private Integer customeruserid;
	@Column(name="merchant_id")
	private Integer merchantid;
	@Column(name="quantity")
	private String quantity;
	@Column(name="status")
	private String status;
	@Column(name = "created_on")
	private Date crearteOn;
	@Column(name = "updated_on")
	private Date updatedOn;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="product_id",referencedColumnName="product_id",insertable=false,updatable=false)
	private ProductsEntity productsEntity;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="merchant_id",referencedColumnName="user_id",insertable=false,updatable=false)
	private UsersEntity merchantDetails;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="unit_weight_id",referencedColumnName="row_id",insertable=false,updatable=false)
	private ProductUnitsWeightEntity productUnitsWeightEntity;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="customer_user_id",referencedColumnName="user_id",insertable=false,updatable=false)
	private UsersEntity customerDetails;
	
	@Transient
	private ProductPriceEntity ProductPriceEntity;
	
	@Transient
	private ProductImageEntity productImageEntity;
	
	public Integer getCartid() {
		return cartid;
	}
	public void setCartid(Integer cartid) {
		this.cartid = cartid;
	}
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	public Integer getUnitweightid() {
		return unitweightid;
	}
	public void setUnitweightid(Integer unitweightid) {
		this.unitweightid = unitweightid;
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
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public ProductPriceEntity getProductPriceEntity() {
		return ProductPriceEntity;
	}
	public void setProductPriceEntity(ProductPriceEntity productPriceEntity) {
		ProductPriceEntity = productPriceEntity;
	}
	public ProductImageEntity getProductImageEntity() {
		return productImageEntity;
	}
	public void setProductImageEntity(ProductImageEntity productImageEntity) {
		this.productImageEntity = productImageEntity;
	}
	public ProductsEntity getProductsEntity() {
		return productsEntity;
	}
	public void setProductsEntity(ProductsEntity productsEntity) {
		this.productsEntity = productsEntity;
	}
	public UsersEntity getMerchantDetails() {
		return merchantDetails;
	}
	public void setMerchantDetails(UsersEntity merchantDetails) {
		this.merchantDetails = merchantDetails;
	}
	public ProductUnitsWeightEntity getProductUnitsWeightEntity() {
		return productUnitsWeightEntity;
	}
	public void setProductUnitsWeightEntity(ProductUnitsWeightEntity productUnitsWeightEntity) {
		this.productUnitsWeightEntity = productUnitsWeightEntity;
	}
	public UsersEntity getCustomerDetails() {
		return customerDetails;
	}
	public void setCustomerDetails(UsersEntity customerDetails) {
		this.customerDetails = customerDetails;
	}
	public Integer getOrdercartid() {
		return ordercartid;
	}
	public void setOrdercartid(Integer ordercartid) {
		this.ordercartid = ordercartid;
	}
	@Override
	public String toString() {
		return "ProductsCartEntity [cartid=" + cartid + ", ordercartid=" + ordercartid + ", productid=" + productid
				+ ", unitweightid=" + unitweightid + ", customeruserid=" + customeruserid + ", merchantid=" + merchantid
				+ ", quantity=" + quantity + ", status=" + status + ", crearteOn=" + crearteOn + ", updatedOn="
				+ updatedOn + ", productsEntity=" + productsEntity + ", merchantDetails=" + merchantDetails
				+ ", productUnitsWeightEntity=" + productUnitsWeightEntity + ", customerDetails=" + customerDetails
				+ ", ProductPriceEntity=" + ProductPriceEntity + ", productImageEntity=" + productImageEntity + "]";
	}
	
}
