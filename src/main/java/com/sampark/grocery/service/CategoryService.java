package com.sampark.grocery.service;

import com.sampark.grocery.model.Domain;
import com.sampark.grocery.model.ProductsEntity;

public interface CategoryService {
	public Domain<ProductsEntity> getProductByPhone(String phone,String password);
	public Domain<ProductsEntity> getProductByEmail(String email,String password);
	public String generateToken(String phone);
	public Domain<String> updateProduct(ProductsEntity Product);
	public Domain<String> createProduct(ProductsEntity Product);
}
