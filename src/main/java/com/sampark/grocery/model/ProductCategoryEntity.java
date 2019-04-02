package com.sampark.grocery.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Id;
import javax.persistence.Column;
import java.sql.Timestamp;
import java.sql.Date;
/*
* File		: ProductCategoryEntity.java
* Date Created	: Fri Jun 29 10:43:40 IST 2018
*/

@Entity
@Table(name="product_category")
public class ProductCategoryEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="category_id")
	private Integer categoryId;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	@Column(name="created_on")
	private Timestamp createdOn;
	@Column(name="updated_on")
	private Timestamp updatedOn;
	@Column(name="image")
	private String image;
	
	@Transient
	private String catExist;
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getCatExist() {
		return catExist;
	}

	public void setCatExist(String catExist) {
		this.catExist = catExist;
	}

	@Override
	public String toString() {
		return "ProductCategoryEntity [categoryId=" + categoryId + ", name=" + name + ", description=" + description
				+ ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + ", image=" + image + ", catExist="
				+ catExist + "]";
	}

	
}