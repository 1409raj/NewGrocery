package com.sampark.grocery.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer productId;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "status")
	private String status;
	@Column(name = "category_id")
	private Integer categoryId;
	@Column(name = "created_on")
	private Date createdOn;
	@Column(name = "updated_on")
	private Date updatedOn;
	@Column(name = "price_id")
	private Integer priceid;
	@Column(name = "unit_id")
	private Integer unitid;
	@Column(name = "image_id")
	private Integer imageid;
	@Column(name = "no_of_purchase")
	private Integer noOfpurchase;

	@ManyToOne(optional = false)
	@JoinColumn(name = "image_id", referencedColumnName = "image_id", insertable = false, updatable = false)
	private ProductImageEntity AllproImageEntity;

	@ManyToOne(optional = false)
	@JoinColumn(name = "unit_id", referencedColumnName = "row_id", insertable = false, updatable = false)
	private ProductUnitsWeightEntity AllprWeightEntity;

	@ManyToOne(optional = false)
	@JoinColumn(name = "price_id", referencedColumnName = "price_id", insertable = false, updatable = false)
	private ProductPriceEntity AllproPriceEntity;

	@Transient
	private UsersEntity usersentity;

	@Transient
	private ProductPriceEntity productPrice;

	@Transient
	private ProductImageEntity proimage;

	@Transient
	private String categoryName;

	@Transient
	private List<ProductPriceEntity> productPrices;

	@Transient
	private List<ProductImageEntity> productimage;

	@Transient
	private List<ProductUnitsWeightEntity> productweight;

	@Transient
	private List<Integer> quantity;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public ProductPriceEntity getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(ProductPriceEntity productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getPriceid() {
		return priceid;
	}

	public void setPriceid(Integer priceid) {
		this.priceid = priceid;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	public List<ProductPriceEntity> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(List<ProductPriceEntity> productPrices) {
		this.productPrices = productPrices;
	}

	public List<ProductImageEntity> getProductimage() {
		return productimage;
	}

	public void setProductimage(List<ProductImageEntity> productimage) {
		this.productimage = productimage;
	}

	public List<ProductUnitsWeightEntity> getProductweight() {
		return productweight;
	}

	public void setProductweight(List<ProductUnitsWeightEntity> productweight) {
		this.productweight = productweight;
	}

	public ProductImageEntity getProimage() {
		return proimage;
	}

	public void setProimage(ProductImageEntity proimage) {
		this.proimage = proimage;
	}

	public Integer getUnitid() {
		return unitid;
	}

	public void setUnitid(Integer unitid) {
		this.unitid = unitid;
	}

	public ProductUnitsWeightEntity getAllprWeightEntity() {
		return AllprWeightEntity;
	}

	public void setAllprWeightEntity(ProductUnitsWeightEntity allprWeightEntity) {
		AllprWeightEntity = allprWeightEntity;
	}

	public Integer getImageid() {
		return imageid;
	}

	public void setImageid(Integer imageid) {
		this.imageid = imageid;
	}

	public ProductImageEntity getAllproImageEntity() {
		return AllproImageEntity;
	}

	public void setAllproImageEntity(ProductImageEntity allproImageEntity) {
		AllproImageEntity = allproImageEntity;
	}

	public List<Integer> getQuantity() {
		return quantity;
	}

	public void setQuantity(List<Integer> quantity) {
		this.quantity = quantity;
	}

	public Integer getNoOfpurchase() {
		return noOfpurchase;
	}

	public void setNoOfpurchase(Integer noOfpurchase) {
		this.noOfpurchase = noOfpurchase;
	}

	public ProductPriceEntity getAllproPriceEntity() {
		return AllproPriceEntity;
	}

	public void setAllproPriceEntity(ProductPriceEntity allproPriceEntity) {
		AllproPriceEntity = allproPriceEntity;
	}

	public UsersEntity getUsersentity() {
		return usersentity;
	}

	public void setUsersentity(UsersEntity usersentity) {
		this.usersentity = usersentity;
	}

	@Override
	public String toString() {
		return "ProductsEntity [productId=" + productId + ", name=" + name + ", description=" + description
				+ ", status=" + status + ", categoryId=" + categoryId + ", createdOn=" + createdOn + ", updatedOn="
				+ updatedOn + ", priceid=" + priceid + ", unitid=" + unitid + ", imageid=" + imageid + ", noOfpurchase="
				+ noOfpurchase + ", AllproImageEntity=" + AllproImageEntity + ", AllprWeightEntity=" + AllprWeightEntity
				+ ", AllproPriceEntity=" + AllproPriceEntity + ", usersentity=" + usersentity + ", productPrice="
				+ productPrice + ", proimage=" + proimage + ", categoryName=" + categoryName + ", productPrices="
				+ productPrices + ", productimage=" + productimage + ", productweight=" + productweight + ", quantity="
				+ quantity + "]";
	}

}