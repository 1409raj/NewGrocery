package com.sampark.grocery.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "products_image")
public class ProductImageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_id")
	private Integer imageId;

	@Column(name = "image")
	private String image;

	@Column(name = "product_id")
	private Integer productid;

	@Column(name = "status")
	private String status;

	@Column(name = "created_on")
	private Date crearteOn;

	@Column(name = "updated_on")
	private Date updatedOn;
	
	@Transient
	private String imagepath;
	
	@Transient
	private List<ProductPriceEntity> productprice;
	
	@Transient
	private List<ProductUnitsWeightEntity> productweight;

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
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
	

	public List<ProductPriceEntity> getProductprice() {
		return productprice;
	}

	public void setProductprice(List<ProductPriceEntity> productprice) {
		this.productprice = productprice;
	}

	public List<ProductUnitsWeightEntity> getProductweight() {
		return productweight;
	}

	public void setProductweight(List<ProductUnitsWeightEntity> productweight) {
		this.productweight = productweight;
	}
	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	@Override
	public String toString() {
		return "ProductImageEntity [imageId=" + imageId + ", image=" + image + ", productid=" + productid + ", status="
				+ status + ", crearteOn=" + crearteOn + ", updatedOn=" + updatedOn + ", imagepath=" + imagepath
				+ ", productprice=" + productprice + ", productweight=" + productweight + "]";
	}

	
}
