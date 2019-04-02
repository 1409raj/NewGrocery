package com.sampark.grocery.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="products_units")
public class ProductUnitsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "row_id")
	private Integer rowId;

	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "product_id")
	private Integer productId;

	@Column(name = "created_on")
	private Date crearteOn;

	@Column(name = "updated_on")
	private Date updatedOn;

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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
		return "ProductUnitsEntity [rowId=" + rowId + ", unitId=" + unitId + ", productId=" + productId + ", crearteOn="
				+ crearteOn + ", updatedOn=" + updatedOn + "]";
	}
}
