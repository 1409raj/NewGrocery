package com.sampark.grocery.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sampark.grocery.dao.Inter;
import com.sampark.grocery.dao.ProductDao;
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
import com.sampark.grocery.util.CommonUtil;

@Repository
public class ProductDaoImpl implements ProductDao {

	@PersistenceContext
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductsEntity> getProductListByCategory(Integer id) {
		List<ProductsEntity> list = new ArrayList<ProductsEntity>();
		String sqlQuery = "Select * from grocerydb.products where category_id = :id";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, ProductsEntity.class);
				query.setParameter("id", id);
				list = (ArrayList<ProductsEntity>) query.getResultList();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductsEntity getProductListByproductid(Integer id) {
		ProductsEntity list = new ProductsEntity();
		String sqlQuery = "Select * from grocerydb.products where product_id = :id";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, ProductsEntity.class);
				query.setParameter("id", id);
				list = (ProductsEntity) query.getSingleResult();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductsEntity> getProductsByName(String name) {
		List<ProductsEntity> list = new ArrayList<ProductsEntity>();
		String sqlQuery = "Select * from grocerydb.products where name LIKE:name limit 8";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery, ProductsEntity.class);
			query.setParameter("name", "%" + name + "%");
			list = (ArrayList<ProductsEntity>) query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Boolean isProductExist(String name) {
		Boolean found = false;
		String sqlQuery = "Select * from grocerydb.products where name = :name";
		Query query = null;
		try {
			try {
				ProductsEntity bean = new ProductsEntity();
				query = getEntityManager().createNativeQuery(sqlQuery, ProductsEntity.class);
				query.setParameter("name", name);
				bean = (ProductsEntity) query.getSingleResult();

				if (!CommonUtil.isNull(bean.getName())) {
					found = true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return found;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return found;
	}

	@Override
	@Transactional
	public Boolean updateProduct(ProductsEntity Product) {
		getEntityManager().merge(Product);
		return true;
	}

	@Override
	@Transactional
	public Boolean createProduct(ProductsEntity Product) {
		getEntityManager().persist(Product);
		if (Product.getProductId() > 0) {
			return true;
		} else {
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCategoryEntity> getCategoryList() {
		List<ProductCategoryEntity> list = new ArrayList<ProductCategoryEntity>();
		String sqlQuery = "Select * from grocerydb.product_category";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery, ProductCategoryEntity.class);
			list = (ArrayList<ProductCategoryEntity>) query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public Boolean isCategoryExist(String categoryName) {
		Boolean found = false;
		String sqlQuery = "Select * from grocerydb.product_category where name=:name";
		Query query = null;
		try {
			try {
				ProductCategoryEntity bean = new ProductCategoryEntity();
				query = getEntityManager().createNativeQuery(sqlQuery, ProductCategoryEntity.class);
				query.setParameter("name", categoryName);
				bean = (ProductCategoryEntity) query.getSingleResult();

				if (!CommonUtil.isNull(bean.getName())) {
					found = true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return found;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return found;
	}

	@Override
	public Boolean isProductIdExist(Integer id) {

		Boolean found = false;
		String sqlQuery = "Select * from grocerydb.merchant_products where product_id = :id";
		Query query = null;
		try {
			MerchantProducts bean = new MerchantProducts();
			query = getEntityManager().createNativeQuery(sqlQuery, MerchantProducts.class);
			query.setParameter("id", id);
			bean = (MerchantProducts) query.getSingleResult();

			if (!CommonUtil.isNull(bean.getProductid())) {
				found = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return found;

	}

	@Override
	public Boolean createCategory(ProductCategoryEntity ProductCategory) {
		getEntityManager().persist(ProductCategory);
		if (ProductCategory.getCategoryId() > 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public ProductsEntity getProductDetails(Integer id) {
		ProductsEntity product = new ProductsEntity();
		String sqlQuery = "Select * from grocerydb.products where product_id=:id";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery, ProductsEntity.class);
			query.setParameter("id", id);
			product = (ProductsEntity) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;

	}

	@Override
	public ProductPriceEntity getProductPrice(Integer priceid) {
		ProductPriceEntity product = new ProductPriceEntity();
		String sqlQuery = "Select * from grocerydb.products_price where price_id=:priceid";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery, ProductPriceEntity.class);
			query.setParameter("priceid", priceid);
			product = (ProductPriceEntity) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;

	}

	@Override
	public ProductCategoryEntity getCategoryDetails(Integer categoryid) {
		ProductCategoryEntity productCategory = new ProductCategoryEntity();
		Query query = null;
		String sqlQuery = "Select * from grocerydb.product_category where category_id=:categoryid";
		query = getEntityManager().createNativeQuery(sqlQuery, ProductCategoryEntity.class);
		query.setParameter("categoryid", categoryid);
		productCategory = (ProductCategoryEntity) query.getSingleResult();

		return productCategory;

	}

	@Override
	public List<ProductPriceEntity> getpricebyproductid(Integer id) {

		List<ProductPriceEntity> pricelist = new ArrayList<ProductPriceEntity>();
		String sqlQuery = "Select * from grocerydb.products_price where product_id=:id";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery, ProductPriceEntity.class);
			query.setParameter("id", id);
			pricelist = (ArrayList<ProductPriceEntity>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pricelist;
	}

	@Override
	public List<ProductUnitsWeightEntity> getproductweightbyproductid(Integer id) {

		List<ProductUnitsWeightEntity> weightlist = new ArrayList<ProductUnitsWeightEntity>();
		String sqlQuery = "Select * from grocerydb.product_units_weight where product_id=:id";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery, ProductUnitsWeightEntity.class);
			query.setParameter("id", id);
			weightlist = (ArrayList<ProductUnitsWeightEntity>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return weightlist;
	}

	@Override
	public ProductUnitsWeightEntity getUnitweightbyid(Integer id) {
		ProductUnitsWeightEntity unitlist = new ProductUnitsWeightEntity();
		String sqlQuery = "Select * from grocerydb.product_units_weight where row_id=:id";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery, ProductUnitsWeightEntity.class);
			query.setParameter("id", id);
			unitlist = (ProductUnitsWeightEntity) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return unitlist;
	}

	@Override
	public List<ProductImageEntity> getProductsImageByproductId(Integer id) {

		List<ProductImageEntity> imagelist = new ArrayList<ProductImageEntity>();
		String sqlQuery = "Select * from grocerydb.products_image where product_id=:id";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery, ProductImageEntity.class);
			query.setParameter("id", id);
			imagelist = (ArrayList<ProductImageEntity>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imagelist;
	}

	@Override
	@Transactional
	public Boolean saveproductsforMerchant(MerchantProducts merchnatproducts) throws Exception {

		entityManager.persist(merchnatproducts);
		if (merchnatproducts.getRowid() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<MerchantProducts> getallmerchhantproducts() {

		List<MerchantProducts> merchantprolist = new ArrayList<MerchantProducts>();
		String sqlQuery = "Select * from grocerydb.merchant_products";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery, MerchantProducts.class);
			merchantprolist = (ArrayList<MerchantProducts>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return merchantprolist;

	}

	@Override
	@Transactional
	public Boolean createPrice(ProductPriceEntity ProductpriceEntity) {
		getEntityManager().persist(ProductpriceEntity);
		if (ProductpriceEntity.getPriceId() > 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	@Transactional
	public Boolean createunitweight(ProductUnitsWeightEntity ProductsUnitWeightEntity) {
		getEntityManager().persist(ProductsUnitWeightEntity);
		if (ProductsUnitWeightEntity.getRowId() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public Boolean saveproductImage(ProductImageEntity productImageEntity) {

		getEntityManager().persist(productImageEntity);
		if (productImageEntity.getImageId() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MerchantProducts> getMerchantProductList(Integer merchnatid, Integer Categoryid) {

		ArrayList<MerchantProducts> Merchantlist = new ArrayList<MerchantProducts>();
		/* SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY','')); */

		String sqlQuery = "Select * from grocerydb.merchant_products where category_id = :Categoryid and merchant_id = :merchnatid GROUP BY product_id";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, MerchantProducts.class);
				query.setParameter("merchnatid", merchnatid);
				query.setParameter("Categoryid", Categoryid);
				Merchantlist = (ArrayList<MerchantProducts>) query.getResultList();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return Merchantlist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductImageEntity getProductsImageById(Integer id) {
		ProductImageEntity productImage = new ProductImageEntity();
		Query query = null;
		String sqlQuery = "Select * from grocerydb.products_image where image_id=:id";
		query = getEntityManager().createNativeQuery(sqlQuery, ProductImageEntity.class);
		query.setParameter("id", id);
		productImage = (ProductImageEntity) query.getSingleResult();

		return productImage;
	}

	@Override
	public ProductImageEntity getProductImageByproId(Integer id) {
		ProductImageEntity productImage = new ProductImageEntity();
		Query query = null;
		String sqlQuery = "Select * from grocerydb.products_image where product_id=:id";
		query = getEntityManager().createNativeQuery(sqlQuery, ProductImageEntity.class);
		query.setParameter("id", id);
		productImage = (ProductImageEntity) query.getSingleResult();

		return productImage;
	}

	@Override
	@Transactional
	public Boolean updateAllProductidaftersave(Integer pid, Integer propriceid, Integer proweightid, Integer proimgid) {
		String sqlQuery = "update  grocerydb.products set price_id=:propriceid, unit_id=:proweightid, image_id=:proimgid where product_id=:pid";

		Query query = null;
		query = getEntityManager().createNativeQuery(sqlQuery);
		query.setParameter("pid", pid);
		query.setParameter("propriceid", propriceid);
		query.setParameter("proweightid", proweightid);
		query.setParameter("proimgid", proimgid);
		int i = query.executeUpdate();
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<MerchantProducts> getMerchantunitsExits(Integer merchnatid, Integer Productidid) {
		ArrayList<MerchantProducts> Merchantlist = new ArrayList<MerchantProducts>();
		String sqlQuery = "Select * from grocerydb.merchant_products where product_id = :Productidid and merchant_id = :merchnatid";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, MerchantProducts.class);
				query.setParameter("merchnatid", merchnatid);
				query.setParameter("Productidid", Productidid);
				Merchantlist = (ArrayList<MerchantProducts>) query.getResultList();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return Merchantlist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getMerchantProductDetail(Integer merchnatid, Integer Productidid) {
		List merchantProduct = new ArrayList();
		String sqlQuery = "Select  gpuw.weight ,gpp.price  from grocerydb.merchant_products as gm,"
				+ " grocerydb.product_units_weight as gpuw,grocerydb.products_price as gpp"
				+ " where gm.product_id = :Productidid and gm.merchant_id = :merchnatid and gm.unit_id = gpuw.row_id and gpp.price_id = gm.price_id";
		Query query = null;

		try {
			query = getEntityManager().createNativeQuery(sqlQuery);
			query.setParameter("merchnatid", merchnatid);
			query.setParameter("Productidid", Productidid);

			merchantProduct = (List) query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return merchantProduct;
	}

	@Override
	public ProductUnitsWeightEntity isProductweightExist(String weight, Integer pid) {
		ProductUnitsWeightEntity bean = new ProductUnitsWeightEntity();
		String sqlQuery = "Select * from grocerydb.product_units_weight where weight = :weight and product_id = :pid ";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, ProductUnitsWeightEntity.class);
				query.setParameter("weight", weight);
				query.setParameter("pid", pid);
				bean = (ProductUnitsWeightEntity) query.getSingleResult();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public List<MerchantProducts> getMerchantProductListbymerchantid(Integer merchnatid) {

		ArrayList<MerchantProducts> Merchantlist = new ArrayList<MerchantProducts>();
		String sqlQuery = "Select * from grocerydb.merchant_products where merchant_id = :merchnatid GROUP BY product_id";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, MerchantProducts.class);
				query.setParameter("merchnatid", merchnatid);
				Merchantlist = (ArrayList<MerchantProducts>) query.getResultList();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return Merchantlist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * @Override public List<ProductUnitsWeightEntity>
	 * getWeightProductListbyproductid(Integer wproid) {
	 * List<ProductUnitsWeightEntity> list= new
	 * ArrayList<ProductUnitsWeightEntity>(); String sqlQuery =
	 * "Select * from grocerydb.product_units_weight where product_id = :wproid";
	 * Query query = null; try { query =
	 * getEntityManager().createNativeQuery(sqlQuery,ProductUnitsWeightEntity.class)
	 * ; query.setParameter("wproid", wproid); list =
	 * (ArrayList<ProductUnitsWeightEntity>) query.getResultList();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return list;
	 * 
	 * }
	 * 
	 * @Override public List<ProductPriceEntity>
	 * getPriceProductListbyproductid(Integer pproid) { List<ProductPriceEntity>
	 * list= new ArrayList<ProductPriceEntity>(); String sqlQuery =
	 * "Select * from grocerydb.products_price where product_id = :pproid"; Query
	 * query = null; try { query =
	 * getEntityManager().createNativeQuery(sqlQuery,ProductPriceEntity.class);
	 * query.setParameter("pproid", pproid); list = (ArrayList<ProductPriceEntity>)
	 * query.getResultList();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return list; }
	 */
	@Override
	@Transactional
	public Boolean updateQuantity(Integer mid, Integer productid, Integer unitid, Integer vendorid, Integer quantity) {
		String sqlQuery = "update  grocerydb.merchant_products set vendor_id=:vendorid, quantity=:quantity where product_id = :productid and merchant_id = :mid and unit_id=:unitid";

		Query query = null;
		query = getEntityManager().createNativeQuery(sqlQuery);
		query.setParameter("productid", productid);
		query.setParameter("mid", mid);
		query.setParameter("unitid", unitid);
		query.setParameter("vendorid", vendorid);
		query.setParameter("quantity", quantity);

		int i = query.executeUpdate();
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List getMerchantIds(Integer unitid, Integer productid) {
		List merchantDetailid = new ArrayList();
		String sqlQuery = "Select * from grocerydb.merchant_products where product_id = :productid and unit_id = :unitid GROUP BY merchant_id";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, MerchantProducts.class);
				query.setParameter("unitid", unitid);
				query.setParameter("productid", productid);
				merchantDetailid = query.getResultList();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return merchantDetailid;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Integer> getQuantitybyid(Integer id) {
		List<Integer> quantitylist = new ArrayList<Integer>();
		String sqlQuery = "Select quantity from grocerydb.merchant_products where product_id=:id";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery);
			query.setParameter("id", id);
			quantitylist = (ArrayList<Integer>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quantitylist;
	}

	@Override
	public List<ProductsEntity> getProductListByaccordingtopurchase(Integer categoryid) {
		List<ProductsEntity> list = new ArrayList<ProductsEntity>();
		String sqlQuery = "Select * from grocerydb.products where category_id = :categoryid order by no_of_purchase DESC";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, ProductsEntity.class);
				query.setParameter("categoryid", categoryid);
				list = (ArrayList<ProductsEntity>) query.getResultList();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductUnitsWeightEntity getunitbynoOfPurchase(Integer productid) {
		ProductUnitsWeightEntity list = new ProductUnitsWeightEntity();
		String sqlQuery = "SELECT * FROM product_units_weight where product_id = :productid order by no_of_purchase desc limit 1;";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, ProductUnitsWeightEntity.class);
				query.setParameter("productid", productid);
				list = (ProductUnitsWeightEntity) query.getSingleResult();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * @Override public List<MerchantProducts>
	 * getMerchantDetailaccnoOfpurchase(Integer unitid, Integer productid) {
	 * ArrayList<MerchantProducts> Merchantlist = new ArrayList<MerchantProducts>();
	 * String sqlQuery =
	 * "Select * from grocerydb.merchant_products where product_id = :productid and unit_id = :unitid"
	 * ; Query query = null; try { try { query =
	 * getEntityManager().createNativeQuery(sqlQuery,MerchantProducts.class);
	 * query.setParameter("productid", productid); query.setParameter("unitid",
	 * unitid); Merchantlist = (ArrayList<MerchantProducts>) query.getResultList();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return Merchantlist; } catch
	 * (Exception e) { e.printStackTrace(); } return null; }
	 */
	@Override
	public MerchantProducts getMerchantDetails(Integer id) {
		MerchantProducts bean = new MerchantProducts();
		String sqlQuery = "Select * from grocerydb.merchant_products where product_id = :id limit 1";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, MerchantProducts.class);
				query.setParameter("id", id);
				bean = (MerchantProducts) query.getSingleResult();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public UsersEntity getSingleMerchantDetails(Integer id) {
		UsersEntity bean = new UsersEntity();
		String sqlQuery = "Select * from users where user_id = :id";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, UsersEntity.class);
				query.setParameter("id", id);
				bean = (UsersEntity) query.getSingleResult();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public List<ProductUnitsWeightEntity> getMerchantProductUnitList(Integer productid) {
		ArrayList<ProductUnitsWeightEntity> Merchantlist = new ArrayList<ProductUnitsWeightEntity>();
		String sqlQuery = "Select * from product_units_weight where product_id = :productid";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, ProductUnitsWeightEntity.class);
				query.setParameter("productid", productid);
				Merchantlist = (ArrayList<ProductUnitsWeightEntity>) query.getResultList();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return Merchantlist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductPriceEntity getPricebyId(Integer id) {
		ProductPriceEntity bean = new ProductPriceEntity();
		String sqlQuery = "Select * from products_price where price_id = :id";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, ProductPriceEntity.class);
				query.setParameter("id", id);
				bean = (ProductPriceEntity) query.getSingleResult();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	@Transactional
	public Boolean createProductCart(ProductsCartEntity productsCartEntity) {
		getEntityManager().persist(productsCartEntity);
		if (productsCartEntity.getCartid() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public Boolean IsProductCartExist(Integer customerid, Integer productid, Integer unitid, Integer merchantid,
			String qunatity, Integer orderid) {
		Boolean found = false;
		String sqlQuery = "Select * from grocerydb.products_cart where product_id = :productid and unit_weight_id = :unitid and customer_user_id = :customerid and merchant_id = :merchantid";
		Query query = null;
		try {
			try {
				ProductsCartEntity bean = new ProductsCartEntity();
				query = getEntityManager().createNativeQuery(sqlQuery, ProductsCartEntity.class);
				query.setParameter("productid", productid);
				query.setParameter("unitid", unitid);
				query.setParameter("customerid", customerid);
				query.setParameter("merchantid", merchantid);
				bean = (ProductsCartEntity) query.getSingleResult();

				if (!CommonUtil.isNull(bean.getQuantity())) {

					if (qunatity.equalsIgnoreCase("0")) {
						found = true;
						String sqlQuery1 = "DELETE FROM grocerydb.products_cart where product_id = :productid and unit_weight_id = :unitid and customer_user_id = :customerid and merchant_id = :merchantid";
						Query query1 = null;
						query1 = getEntityManager().createNativeQuery(sqlQuery1);
						query1.setParameter("productid", productid);
						query1.setParameter("unitid", unitid);
						query1.setParameter("merchantid", merchantid);
						query1.setParameter("customerid", customerid);
						int i = query1.executeUpdate();
						if (i > 0) {
							found = true;
						} else {
							found = false;
						}

					} else {

						found = true;
						String sqlQuery2 = "update  grocerydb.products_cart set quantity=:qunatity, status='Y',order_cart_id=:orderid where product_id = :productid and unit_weight_id = :unitid and merchant_id=:merchantid and customer_user_id = :customerid";

						Query query2 = null;
						query2 = getEntityManager().createNativeQuery(sqlQuery2);
						query2.setParameter("productid", productid);
						query2.setParameter("unitid", unitid);
						query2.setParameter("merchantid", merchantid);
						query2.setParameter("customerid", customerid);
						query2.setParameter("qunatity", qunatity);
						query2.setParameter("orderid", orderid);

						int i = query2.executeUpdate();
						if (i > 0) {
							found = true;
						} else {
							found = false;
						}
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return found;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return found;
	}

	@Override
	public List<ProductsCartEntity> getProductsCartDetail(Integer customerid) {
		ArrayList<ProductsCartEntity> Cartlist = new ArrayList<ProductsCartEntity>();
		String sqlQuery = "Select * from products_cart where customer_user_id = :customerid and status='Y'";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, ProductsCartEntity.class);
				query.setParameter("customerid", customerid);
				Cartlist = (ArrayList<ProductsCartEntity>) query.getResultList();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return Cartlist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MerchantProducts getPricidforcart(Integer productid, Integer unitid, Integer merchantid) {
		MerchantProducts mproduct = new MerchantProducts();
		String sqlQuery = "Select * from grocerydb.merchant_products where product_id=:productid  and unit_id = :unitid  and merchant_id = :merchantid";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery, MerchantProducts.class);
			query.setParameter("productid", productid);
			query.setParameter("unitid", unitid);
			query.setParameter("merchantid", merchantid);
			mproduct = (MerchantProducts) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mproduct;
	}

	@Override
	public MerchantProducts getMerchantids(Integer productid, Integer unitid, Integer priceid) {
		MerchantProducts mproduct = new MerchantProducts();
		String sqlQuery = "Select * from grocerydb.merchant_products where product_id=:productid  and unit_id = :unitid  and price_id = :priceid";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery, MerchantProducts.class);
			query.setParameter("productid", productid);
			query.setParameter("unitid", unitid);
			query.setParameter("priceid", priceid);
			mproduct = (MerchantProducts) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mproduct;
	}

	@Override
	@Transactional
	public Integer createCartOrder(OrderCart orderCart) {
		getEntityManager().persist(orderCart);
		if (orderCart.getOrdercartid() > 0) {
			return orderCart.getOrdercartid();
		} else {
			return null;
		}
	}

	@Override
	public OrderCart IsCartOrderExist(Integer customerid, Integer merchantid) {

		OrderCart orderbean = new OrderCart();
		String sqlQuery = "Select * from grocerydb.cart_order where merchant_id = :merchantid and customer_user_id = :customerid";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, OrderCart.class);
				query.setParameter("customerid", customerid);
				query.setParameter("merchantid", merchantid);
				orderbean = (OrderCart) query.getSingleResult();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return orderbean;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderbean;

	}

	@Override
	public List<ProductsCartEntity> getCartDetail(Integer id) {
		ArrayList<ProductsCartEntity> cartlist = new ArrayList<ProductsCartEntity>();
		String sqlQuery = "Select * from grocerydb.products_cart where order_cart_id = :id";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, ProductsCartEntity.class);
				query.setParameter("id", id);
				cartlist = (ArrayList<ProductsCartEntity>) query.getResultList();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return cartlist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public Boolean createProductCartHistory(ProductCartHistory productCartHistory) {
		getEntityManager().persist(productCartHistory);
		if (productCartHistory.getCarthistoryid() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public Boolean deleteProductCart(Integer cartid) {

		Boolean found = false;
		Query query = null;

		String sqlQuery = "DELETE FROM products_cart WHERE cart_id=:cartid";

		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery);
				query.setParameter("cartid", cartid);
				int i = query.executeUpdate();

				if (i > 0) {
					found = true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return found;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public Boolean trackProductOrder(TrackOrderEntity trackOrderEntity) {
		getEntityManager().persist(trackOrderEntity);
		if (trackOrderEntity.getId() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public Boolean IncreaseProductnoOfPurchase(Integer pid) {
		Boolean found = false;
		Query query = null;

		String sqlQuery = "UPDATE products SET no_of_purchase=no_of_purchase+1 WHERE product_id=:pid";

		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery);
				query.setParameter("pid", pid);
				int i = query.executeUpdate();

				if (i > 0) {
					found = true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return found;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public Boolean IncreaseProductweightnoOfPurchase(Integer wid) {
		Boolean found = false;
		Query query = null;

		String sqlQuery = "UPDATE product_units_weight SET no_of_purchase=no_of_purchase+1 WHERE row_id=:wid";

		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery);
				query.setParameter("wid", wid);
				int i = query.executeUpdate();

				if (i > 0) {
					found = true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return found;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public Boolean IncreaseProductMerchanttnoOfPurchase(Integer pid, Integer wid, Integer mid, Integer quantity) {
		Boolean found = false;
		Query query = null;

		String sqlQuery = "UPDATE merchant_products SET no_of_purchase=no_of_purchase+1, quantity= quantity-:quantity WHERE product_id=:pid  and unit_id = :wid  and merchant_id = :mid";

		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery);
				query.setParameter("pid", pid);
				query.setParameter("wid", wid);
				query.setParameter("mid", mid);
				query.setParameter("quantity", quantity);
				int i = query.executeUpdate();

				if (i > 0) {
					found = true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return found;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public Boolean CanceldecreaseProductMerchanttnoOfPurchase(Integer pid, Integer wid, Integer mid, Integer quantity) {
		Boolean found = false;
		Query query = null;

		String sqlQuery = "UPDATE merchant_products SET no_of_purchase=no_of_purchase-1, quantity= quantity+:quantity WHERE product_id=:pid  and unit_id = :wid  and merchant_id = :mid";

		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery);
				query.setParameter("pid", pid);
				query.setParameter("wid", wid);
				query.setParameter("mid", mid);
				query.setParameter("quantity", quantity);
				int i = query.executeUpdate();

				if (i > 0) {
					found = true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return found;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ProductCartHistory> getRecentProductsCartDetail(Integer customerid) {
		ArrayList<ProductCartHistory> Cartlist = new ArrayList<ProductCartHistory>();
		String sqlQuery = "Select * from products_cart_history where customer_user_id = :customerid and status_id = 5 GROUP BY product_id order by created_on limit 8";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, ProductCartHistory.class);
				query.setParameter("customerid", customerid);
				Cartlist = (ArrayList<ProductCartHistory>) query.getResultList();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return Cartlist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ProductsEntity> getDailyProductListByaccordingtnoOfpurchase() {
		List<ProductsEntity> list = new ArrayList<ProductsEntity>();
		String sqlQuery = "Select * from grocerydb.products order by no_of_purchase DESC limit 8";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, ProductsEntity.class);
				list = (ArrayList<ProductsEntity>) query.getResultList();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<OrderStatusEntity> getOrdersStatus() {

		List<OrderStatusEntity> list = new ArrayList<OrderStatusEntity>();
		String sqlQuery = "Select * from grocerydb.order_status";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, OrderStatusEntity.class);
				list = (ArrayList<OrderStatusEntity>) query.getResultList();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ProductCartHistory> getOrderPlacedMerchantaccept(Integer meid) {

		ArrayList<ProductCartHistory> Cartlist = new ArrayList<ProductCartHistory>();
		String sqlQuery = "Select * from products_cart_history where merchant_id = :meid";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, ProductCartHistory.class);
				query.setParameter("meid", meid);
				Cartlist = (ArrayList<ProductCartHistory>) query.getResultList();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return Cartlist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<ProductCartHistory> getOrderPlacedCustomer(Integer cuid) {
		ArrayList<ProductCartHistory> Cartlist = new ArrayList<ProductCartHistory>();
		String sqlQuery = "Select * from products_cart_history where customer_user_id = :cuid";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, ProductCartHistory.class);
				query.setParameter("cuid", cuid);
				Cartlist = (ArrayList<ProductCartHistory>) query.getResultList();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return Cartlist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public Boolean UpdateOrderstatus(String orid, Integer posid) {
		Boolean found = false;
		Date date = new Date();

		if (posid.equals(2)) {
			found = true;
			Query query1 = null;
			String sqlQuery1 = "UPDATE products_cart_history SET status_id=:posid,order_cancel_date= NULL, order_accept_date=:date WHERE order_id=:orid";
			query1 = getEntityManager().createNativeQuery(sqlQuery1);
			query1.setParameter("orid", orid);
			query1.setParameter("posid", posid);
			query1.setParameter("date", date);
			int i = query1.executeUpdate();
			if (i > 0) {
				found = true;
			} else {
				found = false;
			}

		} else if (posid.equals(3)) {

			found = true;
			Query query2 = null;
			String sqlQuery2 = "UPDATE products_cart_history SET status_id=:posid, order_accept_date=NULL, order_outfor_date=NULL,order_deliver_date=NULL, order_cancel_date=:date WHERE order_id=:orid";

			query2 = getEntityManager().createNativeQuery(sqlQuery2);
			query2.setParameter("orid", orid);
			query2.setParameter("posid", posid);
			query2.setParameter("date", date);

			int i = query2.executeUpdate();
			if (i > 0) {
				found = true;
			} else {
				found = false;
			}
		} else if (posid.equals(4)) {

			found = true;
			Query query3 = null;
			String sqlQuery3 = "UPDATE products_cart_history SET status_id=:posid, order_cancel_date = NULL, order_outfor_date=:date WHERE order_id=:orid";

			query3 = getEntityManager().createNativeQuery(sqlQuery3);
			query3.setParameter("orid", orid);
			query3.setParameter("posid", posid);
			query3.setParameter("date", date);
			int i = query3.executeUpdate();
			if (i > 0) {
				found = true;
			} else {
				found = false;
			}
		} else if (posid.equals(5)) {

			found = true;
			Query query4 = null;
			String sqlQuery4 = "UPDATE products_cart_history SET status_id=:posid, order_cancel_date = NULL, order_deliver_date=:date WHERE order_id=:orid";

			query4 = getEntityManager().createNativeQuery(sqlQuery4);
			query4.setParameter("orid", orid);
			query4.setParameter("posid", posid);
			query4.setParameter("date", date);
			int i = query4.executeUpdate();
			if (i > 0) {
				found = true;
			} else {
				found = false;
			}
		}

		return found;

		/*
		 * Query query = null; String sqlQuery =
		 * "UPDATE products_cart_history SET status_id=:posid WHERE cart_history_id=:phid"
		 * ;
		 * 
		 * try { query = getEntityManager().createNativeQuery(sqlQuery);
		 * query.setParameter("phid", phid); query.setParameter("posid", posid); int i =
		 * query.executeUpdate();
		 * 
		 * if(i >0) { found = true; }
		 * 
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } return found; }
		 */
	}

	@Override
	public List<MerchantProducts> getProductListByaccordingMerchantandcategory(Integer categoryid, Integer merchantid) {
		List<MerchantProducts> list = new ArrayList<MerchantProducts>();
		String sqlQuery = "Select * from grocerydb.merchant_products where category_id = :categoryid and merchant_id = :merchantid GROUP BY product_id";
		Query query = null;
		try {
			try {
				query = getEntityManager().createNativeQuery(sqlQuery, MerchantProducts.class);
				query.setParameter("categoryid", categoryid);
				query.setParameter("merchantid", merchantid);
				list = (ArrayList<MerchantProducts>) query.getResultList();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MerchantCategory> getMerCatList(Integer merchantid) {
		List<MerchantCategory> merchantcat = new ArrayList<MerchantCategory>();
		String sqlQuery = "Select * from grocerydb.merchant_category where merchant_id=:merchantid";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery, MerchantCategory.class);
			query.setParameter("merchantid", merchantid);
			merchantcat = (ArrayList<MerchantCategory>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return merchantcat;
	}

	@Override
	@Transactional
	public Boolean saveCategoryforMerchant(MerchantCategory merchantCategory) {
		entityManager.persist(merchantCategory);
		if (merchantCategory.getRowId() > 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	@Transactional
	public Boolean ProductOrderPayment(OrderPaymentEntity orderPaymentEntity) {
		getEntityManager().persist(orderPaymentEntity);
		if (orderPaymentEntity.getId() > 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	@Transactional
	public boolean updateProductStatus(String status) {
		String sqlQuery = "update  grocerydb.products set status=:status";

		Query query = null;
		query = getEntityManager().createNativeQuery(sqlQuery);
		query.setParameter("status", status);

		int i = query.executeUpdate();
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	@Override
	public Boolean updateProductStatus(Integer product_id, String status) {
		String sqlQuery = "update grocerydb.products set status=:status where product_id=:product_id ";

		Query query = null;
		query = getEntityManager().createNativeQuery(sqlQuery);
		query.setParameter("product_id", product_id);
		query.setParameter("status", status);
		int i = query.executeUpdate();
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<ProductsEntity> getAllProductforAdmin() {
		List<ProductsEntity> list = new ArrayList<ProductsEntity>();
		String sqlQuery = "Select * from grocerydb.products ";
		Query query = null;
		try {
			query = getEntityManager().createNativeQuery(sqlQuery, ProductsEntity.class);
			list = (ArrayList<ProductsEntity>) query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProductsEntity> getAllProductforUser() {
		List<ProductsEntity> list = new ArrayList<ProductsEntity>();
		String sqlQuery = "Select * from grocerydb.products where status = 'Y' order by product_id ";
		Query query = null;
		try {

			query = getEntityManager().createNativeQuery(sqlQuery, ProductsEntity.class);
			list = (ArrayList<ProductsEntity>) query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	@Transactional
	public boolean updateProductStatusForView(int product_id, Integer p_status) {
		String sqlQuery = "update grocerydb.products set p_status=:p_status where product_id=:product_id ";

		Query query = null;
		query = getEntityManager().createNativeQuery(sqlQuery);
		query.setParameter("product_id", product_id);
		query.setParameter("p_status", p_status);
		int i = query.executeUpdate();
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean updateUserStatus(int merchant_id, Integer product_status) {
		String sqlQuery = "update grocerydb.users set product_status=:product_status where user_id=:user_id ";

		Query query = null;
		query = getEntityManager().createNativeQuery(sqlQuery);
		query.setParameter("product_status", product_status);
		query.setParameter("user_id", merchant_id);
		int i = query.executeUpdate();
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

}
