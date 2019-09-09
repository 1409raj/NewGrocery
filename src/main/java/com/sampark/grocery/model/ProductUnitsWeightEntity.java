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
@Table(name="product_units_weight")
public class ProductUnitsWeightEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "row_id")
	private Integer rowId;

	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "product_id")
	private Integer productId;

	@Column(name = "weight")
	private String weight;
	
	@Column(name = "created_on")
	private Date crearteOn;

	@Column(name = "updated_on")
	private Date updatedOn;
	
	@Column(name = "no_of_purchase")
	private Integer noOfpurchase;

	@Transient
	private Boolean unitexist;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="unit_id",referencedColumnName="row_id",insertable=false,updatable=false)
	private UnitsEntity unitentity;
	
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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	public Integer getNoOfpurchase() {
		return noOfpurchase;
	}

	public void setNoOfpurchase(Integer noOfpurchase) {
		this.noOfpurchase = noOfpurchase;
	}

	public UnitsEntity getUnitentity() {
		return unitentity;
	}

	public void setUnitentity(UnitsEntity unitentity) {
		this.unitentity = unitentity;
	}

	public Boolean getUnitexist() {
		return unitexist;
	}

	public void setUnitexist(Boolean unitexist) {
		this.unitexist = unitexist;
	}

	@Override
	public String toString() {
		return "ProductUnitsWeightEntity [rowId=" + rowId + ", unitId=" + unitId + ", productId=" + productId
				+ ", weight=" + weight + ", crearteOn=" + crearteOn + ", updatedOn=" + updatedOn + ", noOfpurchase="
				+ noOfpurchase + ", unitexist=" + unitexist + ", unitentity=" + unitentity + "]";
	}

	
}
