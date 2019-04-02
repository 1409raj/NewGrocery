package com.sampark.grocery.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="products_price")
public class ProductPriceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "price_id")
	private Integer priceId;

	@Column(name = "price")
	private String price;

	@Column(name = "product_id")
	private Integer productid;

	@Column(name = "status")
	private String status;

	@Column(name = "created_on")
	private Date crearteOn;

	@Column(name = "updated_on")
	private Date updatedOn;

	public Integer getPriceId() {
		return priceId;
	}

	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	@Override
	public String toString() {
		return "ProductPriceEntity [priceId=" + priceId + ", price=" + price + ", productid=" + productid + ", status="
				+ status + ", crearteOn=" + crearteOn + ", updatedOn=" + updatedOn + "]";
	}

	


}
