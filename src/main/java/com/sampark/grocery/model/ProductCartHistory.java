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
@Table(name="products_cart_history")
public class ProductCartHistory {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cart_history_id")
	private Integer carthistoryid;
	@Column(name="order_cart_id")
	private Integer ordercartid;
	
	@Column(name="order_id")
	private String orderid;
	
	@Column(name="status_id")
	private Integer statusid;

	@Column(name="product_id")
	private Integer productid;
	
	@Column(name="weight")
	private Integer weight;
	@Column(name="price")
	private Integer price;
	@Column(name="unit")
	private String unit;
	
	@Column(name="unit_weight_id")
	private Integer unitweightid;
	@Column(name="customer_user_id")
	private Integer customeruserid;
	@Column(name="merchant_id")
	private Integer merchantid;
	@Column(name="quantity")
	private String quantity;
	@Column(name = "created_on")
	private Date crearteOn;
	@Column(name = "updated_on")
	private Date updatedOn;
	
	@Column(name = "order_accept_date")
	private Date ordacceptdate;
	
	@Column(name = "order_cancel_date")
	private Date ordcanceldate;
	
	@Column(name = "order_outfor_date")
	private Date ordoutfordate;
	
	@Column(name = "order_deliver_date")
	private Date orddeliverdate;
	
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
	
	@ManyToOne(optional = false)
	@JoinColumn(name="status_id",referencedColumnName="status_id",insertable=false,updatable=false)
	private OrderStatusEntity orderStatusEntity;
	
	@Transient
	private ProductPriceEntity ProductPriceEntity;
	
	@Transient
	private ProductImageEntity productImageEntity;
	
	
	public Integer getCarthistoryid() {
		return carthistoryid;
	}
	public void setCarthistoryid(Integer carthistoryid) {
		this.carthistoryid = carthistoryid;
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
	public Integer getStatusid() {
		return statusid;
	}
	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}
	public OrderStatusEntity getOrderStatusEntity() {
		return orderStatusEntity;
	}
	public void setOrderStatusEntity(OrderStatusEntity orderStatusEntity) {
		this.orderStatusEntity = orderStatusEntity;
	}
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
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
	
	public Date getCrearteOn() {
		return crearteOn;
	}
	public void setCrearteOn(Date crearteOn) {
		this.crearteOn = crearteOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public Date getOrdacceptdate() {
		return ordacceptdate;
	}
	public void setOrdacceptdate(Date ordacceptdate) {
		this.ordacceptdate = ordacceptdate;
	}
	public Date getOrdcanceldate() {
		return ordcanceldate;
	}
	public void setOrdcanceldate(Date ordcanceldate) {
		this.ordcanceldate = ordcanceldate;
	}
	public Date getOrdoutfordate() {
		return ordoutfordate;
	}
	public void setOrdoutfordate(Date ordoutfordate) {
		this.ordoutfordate = ordoutfordate;
	}
	public Date getOrddeliverdate() {
		return orddeliverdate;
	}
	public void setOrddeliverdate(Date orddeliverdate) {
		this.orddeliverdate = orddeliverdate;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
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
	@Override
	public String toString() {
		return "ProductCartHistory [carthistoryid=" + carthistoryid + ", ordercartid=" + ordercartid + ", orderid="
				+ orderid + ", statusid=" + statusid + ", productid=" + productid + ", weight=" + weight + ", price="
				+ price + ", unit=" + unit + ", unitweightid=" + unitweightid + ", customeruserid=" + customeruserid
				+ ", merchantid=" + merchantid + ", quantity=" + quantity + ", crearteOn=" + crearteOn + ", updatedOn="
				+ updatedOn + ", ordacceptdate=" + ordacceptdate + ", ordcanceldate=" + ordcanceldate
				+ ", ordoutfordate=" + ordoutfordate + ", orddeliverdate=" + orddeliverdate + ", productsEntity="
				+ productsEntity + ", merchantDetails=" + merchantDetails + ", productUnitsWeightEntity="
				+ productUnitsWeightEntity + ", customerDetails=" + customerDetails + ", orderStatusEntity="
				+ orderStatusEntity + ", ProductPriceEntity=" + ProductPriceEntity + ", productImageEntity="
				+ productImageEntity + "]";
	}
	
	
	
}
