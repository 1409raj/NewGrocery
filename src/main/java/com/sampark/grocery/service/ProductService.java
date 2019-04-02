package com.sampark.grocery.service;

import java.util.List;

import org.json.JSONObject;

import com.sampark.grocery.model.CustomerViewPage;
import com.sampark.grocery.model.Domain;
import com.sampark.grocery.model.MerchantCategory;
import com.sampark.grocery.model.MerchantProductDetails;
import com.sampark.grocery.model.MerchantProducts;
import com.sampark.grocery.model.MerchantUpdateQuantity;
import com.sampark.grocery.model.OrderStatusEntity;
import com.sampark.grocery.model.ProductAdervtiesment;
import com.sampark.grocery.model.ProductCartHistory;
import com.sampark.grocery.model.ProductCategoryEntity;
import com.sampark.grocery.model.ProductImageEntity;
import com.sampark.grocery.model.ProductPriceEntity;
import com.sampark.grocery.model.ProductQuantityLimit;
import com.sampark.grocery.model.ProductUnitsWeightEntity;
import com.sampark.grocery.model.ProductsCartEntity;
import com.sampark.grocery.model.ProductsEntity;
import com.sampark.grocery.model.TrackOrderEntity;
import com.sampark.grocery.model.UsersEntity;
import com.sampark.grocery.model.VendorMerchantEntity;

public interface ProductService {
	public Domain<List<ProductsEntity>> getProductListByCategory(Integer id);

	public Domain<List<ProductsEntity>> getProductsByName(String name);

	public Domain<Boolean> createProduct(ProductsEntity Product);

	public Domain<Boolean> updateProduct(ProductsEntity Product);

	public Domain<List<ProductCategoryEntity>> getCategoryList(Integer customerid);
	
	public Domain<String> saveCategoryforMerchant(MerchantCategory merchantCategory);

	public Domain<Boolean> createCategory(ProductCategoryEntity ProductCategory);

	public Domain<ProductsEntity> getProductDetails(Integer id);

	public Domain<Boolean> updateAllProductidaftersave(Integer pid, Integer propriceid, Integer proweightid,
			Integer proimgid);

	public Domain<ProductsEntity> getProductListByproductid(Integer id, Integer mid);

	public List<ProductPriceEntity> getpricebyproductid(Integer id);

	public List<ProductUnitsWeightEntity> getproductweightbyproductid(Integer id);

	public List<ProductImageEntity> getProductsImageByproductId(Integer id);

	public Domain<Boolean> saveproductsforMerchant(MerchantProducts merchnatproducts) throws Exception;

	public Domain<Boolean> createPrice(ProductPriceEntity ProductpriceEntity);

	public Domain<Boolean> createunitweight(ProductUnitsWeightEntity ProductsUnitWeightEntity);

	public Domain<Boolean> saveproductImage(ProductImageEntity productImageEntity);

	public Domain<List<MerchantProducts>> getMerchantProductList(Integer merchnatid, Integer Categoryid);

	public Domain<List<MerchantProducts>> getMerchantProductListbymerchant(Integer merchnatid);

	public List<MerchantProductDetails> getMerchantProductDetail(Integer merchnatid, Integer Productidid);

	public Domain<Boolean> getupdateQuantity(
			List<MerchantUpdateQuantity.MerchantPurchaseProductDetailEList> merchantUpdateQuantity);

	public Domain<List<UsersEntity>> getMerchantIds(Integer unitid, Integer productid);

	public Domain<List<ProductsEntity>> getProductListByaccordingtopurchase(Integer categoryid);

	public Domain<List<ProductsEntity>> getProductListByaccordingMerchantandcategory(Integer categoryid,
			Integer merchantid);

	public Domain<List<ProductUnitsWeightEntity>> getMerchantProductUnitList(Integer productid);

	public Domain<Boolean> createProductCart(ProductsCartEntity productsCartEntity);

	public Domain<List<ProductsCartEntity>> getProductsCartDetail(Integer customerid);

	public Domain<Boolean> createProductCartHistory(ProductCartHistory productCartHistory);

	public Domain<TrackOrderEntity> trackProductOrder(TrackOrderEntity trackOrderEntity);

	public Domain<List<ProductCartHistory>> getRecentProductsCartDetail(Integer customerid);

	public Domain<Object> quantityGreator(ProductQuantityLimit productQuantityLimit);

	public Domain<List<ProductsEntity>> getDailyProductListByaccordingtnoOfpurchase();

	public Domain<List<OrderStatusEntity>> getOrdersStatus();

	public Domain<List<ProductCartHistory>> getOrderPlacedMerchantaccept(Integer meid);

	public Domain<List<ProductCartHistory>> getOrderPlacedCustomer(Integer cuid);

	public Domain<ProductsEntity> getProductListBySearch(Integer pid);

	public Domain<ProductCartHistory> UpdateOrderstatus(String orid, Integer posid);

	public Domain<CustomerViewPage> customerviewpage(Integer customerid,Float radius);

	public Domain<List<UsersEntity>> getNearestMerchant(Integer customerid,Float radius);
	
	public Domain<List<ProductAdervtiesment>> getNearestMerchantAdvertiesment(Integer customerid,Float radius);
	
	public Domain<List<ProductUnitsWeightEntity>> getMerchantProductUnitDetail(Integer merchnatid, Integer Productidid);
}
