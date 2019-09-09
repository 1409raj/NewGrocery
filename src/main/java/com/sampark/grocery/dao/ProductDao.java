package com.sampark.grocery.dao;

import java.util.List;

import com.sampark.grocery.model.Domain;
import com.sampark.grocery.model.MerchantCategory;
import com.sampark.grocery.model.MerchantDetails;
import com.sampark.grocery.model.MerchantProductDetails;
import com.sampark.grocery.model.MerchantProducts;
import com.sampark.grocery.model.OrderCart;
import com.sampark.grocery.model.OrderPaymentEntity;
import com.sampark.grocery.model.OrderStatusEntity;
import com.sampark.grocery.model.ProductCartHistory;
import com.sampark.grocery.model.ProductCategoryEntity;
import com.sampark.grocery.model.ProductImageEntity;
import com.sampark.grocery.model.ProductPriceEntity;
import com.sampark.grocery.model.ProductUnitsWeightEntity;
import com.sampark.grocery.model.ProductsCartEntity;
import com.sampark.grocery.model.ProductsEntity;
import com.sampark.grocery.model.TrackOrderEntity;
import com.sampark.grocery.model.UsersEntity;
import com.sampark.grocery.model.VendorMerchantEntity;

public interface ProductDao {
	public List<ProductsEntity> getProductListByCategory(Integer id);

	public List<ProductsEntity> getProductsByName(String name);

	public Boolean isProductExist(String phone);

	public Boolean createProduct(ProductsEntity Product);

	public Boolean updateProduct(ProductsEntity Product);

	public List<ProductCategoryEntity> getCategoryList();

	public Boolean createCategory(ProductCategoryEntity ProductCategory);

	public Boolean isCategoryExist(String categoryName);

	public ProductsEntity getProductDetails(Integer id);

	public ProductPriceEntity getProductPrice(Integer priceid);

	public ProductCategoryEntity getCategoryDetails(Integer categoryid);

	public ProductImageEntity getProductsImageById(Integer id);

	public ProductImageEntity getProductImageByproId(Integer id);

	public Boolean updateAllProductidaftersave(Integer pid, Integer propriceid, Integer proweightid, Integer proimgid);

	public ProductsEntity getProductListByproductid(Integer id);

	public List<ProductPriceEntity> getpricebyproductid(Integer id);

	public List<ProductUnitsWeightEntity> getproductweightbyproductid(Integer id);
	
	public ProductUnitsWeightEntity getUnitweightbyid(Integer id);

	public List<ProductImageEntity> getProductsImageByproductId(Integer id);

	public Boolean saveproductsforMerchant(MerchantProducts merchnatproducts) throws Exception;

	public Boolean isProductIdExist(Integer id) throws Exception;

	public List<MerchantProducts> getallmerchhantproducts();

	public Boolean createPrice(ProductPriceEntity ProductpriceEntity);

	public Boolean createunitweight(ProductUnitsWeightEntity ProductsUnitWeightEntity);

	public Boolean saveproductImage(ProductImageEntity productImageEntity);

	public List<MerchantProducts> getMerchantProductList(Integer merchnatid, Integer categoryid);

	public List<MerchantProducts> getMerchantProductListbymerchantid(Integer merchnatid);

	public List<MerchantProducts> getMerchantunitsExits(Integer merchnatid, Integer Productidid);

	// public List<MerchantProducts> getProductQuantity(Integer merchnatid, Integer
	// Productidid);
	public List<Integer> getQuantitybyid(Integer id);

	public Boolean saveCategoryforMerchant(MerchantCategory merchantCategory);
	
	public List getMerchantProductDetail(Integer merchnatid, Integer Productidid);

	public ProductUnitsWeightEntity isProductweightExist(String weight, Integer pid);

	public Boolean updateQuantity(Integer mid, Integer productid, Integer unitid, Integer vendorid, Integer quantity);

	public List getMerchantIds(Integer unitid, Integer productid);

	public List<ProductsEntity> getProductListByaccordingtopurchase(Integer categoryid);

	public List<MerchantProducts> getProductListByaccordingMerchantandcategory(Integer categoryid, Integer merchantid);

	public ProductUnitsWeightEntity getunitbynoOfPurchase(Integer productid);

	public MerchantProducts getMerchantDetails(Integer id);

	public UsersEntity getSingleMerchantDetails(Integer id);

	public ProductPriceEntity getPricebyId(Integer id);

	public List<ProductUnitsWeightEntity> getMerchantProductUnitList(Integer productid);
	// public List<MerchantProducts> getMerchantDetailaccnoOfpurchase(Integer
	// unitid, Integer productid);
	/*
	 * public List<ProductUnitsWeightEntity> getWeightProductListbyproductid(Integer
	 * wproid); public List<ProductPriceEntity>
	 * getPriceProductListbyproductid(Integer pproid);
	 */

	public Boolean createProductCart(ProductsCartEntity productsCartEntity);

	public Boolean IsProductCartExist(Integer customerid, Integer productid, Integer unitid, Integer merchantid,
			String qunatity, Integer orderid);

	public List<ProductsCartEntity> getProductsCartDetail(Integer customerid);

	public MerchantProducts getPricidforcart(Integer productid, Integer unitid, Integer merchantid);

	public MerchantProducts getMerchantids(Integer productid, Integer unitid, Integer priceid);
	
	public Integer createCartOrder(OrderCart orderCart);

	public OrderCart IsCartOrderExist(Integer customerid, Integer merchantid);

	public List<ProductsCartEntity> getCartDetail(Integer id);

	public Boolean createProductCartHistory(ProductCartHistory productCartHistory);

	public Boolean deleteProductCart(Integer cartidid);

	public Boolean trackProductOrder(TrackOrderEntity trackOrderEntity);

	public Boolean IncreaseProductnoOfPurchase(Integer pid);

	public Boolean IncreaseProductweightnoOfPurchase(Integer wid);

	public Boolean IncreaseProductMerchanttnoOfPurchase(Integer pid, Integer wid, Integer mid, Integer quantity);
	
	public Boolean CanceldecreaseProductMerchanttnoOfPurchase(Integer pid, Integer wid, Integer mid, Integer quantity);

	public List<ProductCartHistory> getRecentProductsCartDetail(Integer customerid);

	public List<ProductsEntity> getDailyProductListByaccordingtnoOfpurchase();

	public List<OrderStatusEntity> getOrdersStatus();

	public List<ProductCartHistory> getOrderPlacedMerchantaccept(Integer meid);

	public List<ProductCartHistory> getOrderPlacedCustomer(Integer cuid);

	public Boolean UpdateOrderstatus(String orid, Integer posid);
	
	public List<MerchantCategory> getMerCatList(Integer merchantid);

	public Boolean ProductOrderPayment(OrderPaymentEntity orderPaymentEntity);

	public boolean updateProductStatus(String status);

	public Boolean updateProductStatus(Integer product_id, String newstatus);

	public List<ProductsEntity> getAllProductforAdmin();

	public List<ProductsEntity> getAllProductforUser();

	public boolean updateProductStatusForView(int product_id, Integer newProductstatus);

	public boolean updateUserStatus(int merchant_id, Integer newProductstatus);

}
