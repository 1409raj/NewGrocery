package com.sampark.grocery.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sampark.grocery.constant.ApiConstants;
import com.sampark.grocery.dao.Inter;
import com.sampark.grocery.dao.ProductDao;
import com.sampark.grocery.dao.UserDao;
import com.sampark.grocery.dao.impl.ProductDaoImpl;
import com.sampark.grocery.model.CustomerOrder;
import com.sampark.grocery.model.CustomerViewPage;
import com.sampark.grocery.model.DailyUseProducts;
import com.sampark.grocery.model.Domain;
import com.sampark.grocery.model.MerchantCategory;
import com.sampark.grocery.model.MerchantDetails;
import com.sampark.grocery.model.MerchantOrder;
import com.sampark.grocery.model.MerchantProductDetails;
import com.sampark.grocery.model.MerchantProducts;
import com.sampark.grocery.model.MerchantUpdateQuantity;
import com.sampark.grocery.model.OrderCart;
import com.sampark.grocery.model.OrderStatusDate;
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
import com.sampark.grocery.model.RecntproductList;
import com.sampark.grocery.model.TrackOrderEntity;
import com.sampark.grocery.model.UsersEntity;
import com.sampark.grocery.model.userAddressDetails;
import com.sampark.grocery.service.ProductService;
import com.sampark.grocery.util.RandomText;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao dao;

	@Autowired
	UserDao udao;

	ProductDaoImpl ffd;

	@Override
	public Domain<List<ProductsEntity>> getProductListByCategory(Integer id) {

		ProductsEntity productsEntity = new ProductsEntity();
		List<ProductsEntity> list = new ArrayList<ProductsEntity>();
		Domain<List<ProductsEntity>> domain = new Domain<List<ProductsEntity>>();
		list = dao.getProductListByCategory(id);
		if (list.size() > 0) {
			Iterator<ProductsEntity> it = list.iterator();
			while (it.hasNext()) {
				productsEntity = it.next();
				productsEntity.getAllproImageEntity().setImagepath(ApiConstants.server_url + "images?image="
						+ productsEntity.getAllproImageEntity().getImage() + "&folder=image");

			}
			domain.setObject(list);
			domain.setMessage("Product list is not empty.");
			domain.setHasError(false);
		} else {
			domain.setMessage("Product in the category does not exist.");
			domain.setHasError(true);
		}

		return domain;
	}

	@Override
	public Domain<ProductsEntity> getProductListByproductid(Integer id, Integer mid) {
		List<ProductsEntity> list = new ArrayList<ProductsEntity>();

		Domain<ProductsEntity> domain = new Domain<ProductsEntity>();
		ProductImageEntity productImageEntity = new ProductImageEntity();
		List data = new ArrayList();
		ProductsEntity prima = new ProductsEntity();
		prima = dao.getProductListByproductid(id);

		List<ProductImageEntity> Imageentity = dao.getProductsImageByproductId(id);
		Iterator<ProductImageEntity> it = Imageentity.iterator();
		while (it.hasNext()) {
			productImageEntity = it.next();
			productImageEntity.setImagepath(
					ApiConstants.server_url + "images?image=" + productImageEntity.getImage() + "&folder=image");
			data.add(productImageEntity);
		}
		List<ProductPriceEntity> Priceentity = dao.getpricebyproductid(id);
		List<ProductUnitsWeightEntity> Weightentity = dao.getproductweightbyproductid(id);

		List<MerchantProducts> merchantExistProducts = dao.getMerchantunitsExits(mid, id);
		for (int i = 0; i < merchantExistProducts.size(); i++) {
			if (merchantExistProducts.get(i).getUnitid().equals(Weightentity.get(i).getRowId())) {
				Weightentity.get(i).setUnitexist(true);
			} else {
				Weightentity.get(i).setUnitexist(false);
			}

		}

		prima.setProductPrices(Priceentity);
		prima.setProductimage(data);
		prima.setProductweight(Weightentity);

		domain.setObject(prima);
		domain.setMessage("Product image is not empty.");
		domain.setHasError(false);

		return domain;
	}

	@Override
	public Domain<List<ProductsEntity>> getProductsByName(String name) {
		ProductsEntity productsEntity = new ProductsEntity();
		List<ProductsEntity> list = new ArrayList<ProductsEntity>();
		Domain<List<ProductsEntity>> domain = new Domain<List<ProductsEntity>>();
		list = dao.getProductsByName(name);
		if (list.size() > 0) {
			Iterator<ProductsEntity> it = list.iterator();
			while (it.hasNext()) {
				ProductCategoryEntity productCategory = new ProductCategoryEntity();
				productsEntity = it.next();
				productCategory = dao.getCategoryDetails(productsEntity.getCategoryId());
				productsEntity.setCategoryName(productCategory.getName());
				productsEntity.getAllproImageEntity().setImagepath(ApiConstants.server_url + "images?image="
						+ productsEntity.getAllproImageEntity().getImage() + "&folder=image");
			}
			domain.setObject(list);
			domain.setMessage("Product list is not empty.");
			domain.setHasError(false);
		} else {
			domain.setMessage("Product in the category does not exist.");
			domain.setHasError(true);
		}

		return domain;
	}

	@Override
	public Domain<Boolean> createProduct(ProductsEntity Product) {
		Domain<Boolean> domain = new Domain<Boolean>();

		if (dao.isProductExist(Product.getName())) {
			domain.setMessage("Product already exist.");
			domain.setHasError(true);
		} else {
			dao.createProduct(Product);
			domain.setId(Product.getProductId());
			domain.setMessage("Product saved successfully.");
			domain.setHasError(false);
		}
		return domain;
	}

	@Override
	public Domain<Boolean> updateProduct(ProductsEntity Product) {
		Domain<Boolean> domain = new Domain<Boolean>();
		if (dao.isProductExist(Product.getName())) {
			domain.setMessage("Product already exist.");
			domain.setHasError(true);
		} else {
			dao.updateProduct(Product);
			domain.setObject(true);
			domain.setMessage("Product saved successfully.");
			domain.setHasError(false);
		}
		return domain;
	}

	@Override
	public Domain<List<ProductCategoryEntity>> getCategoryList(Integer customerid) {

		List<ProductCategoryEntity> list = new ArrayList<ProductCategoryEntity>();
		List<MerchantCategory> merchantcat = new ArrayList<MerchantCategory>();
		ProductCategoryEntity procatlist = new ProductCategoryEntity();
		Domain<List<ProductCategoryEntity>> domain = new Domain<List<ProductCategoryEntity>>();
		list = dao.getCategoryList();
		merchantcat = dao.getMerCatList(customerid);
		for (ProductCategoryEntity modelListdata : list) {
            for (MerchantCategory prevListdata : merchantcat) {
                if (modelListdata.getCategoryId().equals(prevListdata.getCategoryid())
                        ) {
                	modelListdata.setCatExist("Y");

                }
            }

        }
		if (list.size() > 0) {
			domain.setObject(list);
			domain.setMessage("category list is not empty.");
			domain.setHasError(false);
		} else {
			domain.setMessage(" category does not exist.");
			domain.setHasError(true);
		}
		// TODO Auto-generated method stub
		return domain;
	}

	@Override
	public Domain<Boolean> createCategory(ProductCategoryEntity ProductCategory) {
		Domain<Boolean> domain = new Domain<Boolean>();
		if (dao.isCategoryExist(ProductCategory.getName())) {
			domain.setMessage("Category already exist.");
			domain.setHasError(true);
		} else {
			dao.createCategory(ProductCategory);
			domain.setObject(true);
			domain.setMessage("category saved successfully.");
			domain.setHasError(false);
		}
		return domain;
	}

	@Override
	public Domain<ProductsEntity> getProductDetails(Integer id) {
		ProductsEntity product = new ProductsEntity();
		Domain<ProductsEntity> domain = new Domain<ProductsEntity>();
		ProductPriceEntity productprice = new ProductPriceEntity();
		product = dao.getProductDetails(id);
		productprice = dao.getProductPrice(product.getPriceid());
		product.setProductPrice(productprice);
		product.getAllproImageEntity().setImagepath(ApiConstants.server_url + "images?image="
				+ product.getAllproImageEntity().getImage() + "&folder=image");
		domain.setObject(product);
		domain.setMessage("Product price.");
		domain.setHasError(false);

		return domain;
	}

	@Override
	public List<ProductPriceEntity> getpricebyproductid(Integer id) {

		List<ProductPriceEntity> list = new ArrayList<ProductPriceEntity>();
		Domain<List<ProductPriceEntity>> domain = new Domain<List<ProductPriceEntity>>();
		list = dao.getpricebyproductid(id);
		if (list.size() > 0) {
			domain.setObject(list);
			domain.setMessage("Product price is not empty.");
			domain.setHasError(false);
		} else {
			domain.setMessage("Product price does not exist.");
			domain.setHasError(true);
		}
		return list;
	}

	@Override
	public List<ProductUnitsWeightEntity> getproductweightbyproductid(Integer id) {

		List<ProductUnitsWeightEntity> list = new ArrayList<ProductUnitsWeightEntity>();
		Domain<List<ProductUnitsWeightEntity>> domain = new Domain<List<ProductUnitsWeightEntity>>();
		list = dao.getproductweightbyproductid(id);
		if (list.size() > 0) {
			domain.setObject(list);
			domain.setMessage("Product weight is not empty.");
			domain.setHasError(false);
		} else {
			domain.setMessage("Product weight does not exist.");
			domain.setHasError(true);
		}
		return list;
	}

	@Override
	public List<ProductImageEntity> getProductsImageByproductId(Integer id) {
		List<ProductImageEntity> list = new ArrayList<ProductImageEntity>();
		Domain<List<ProductImageEntity>> domain = new Domain<List<ProductImageEntity>>();
		list = dao.getProductsImageByproductId(id);
		if (list.size() > 0) {
			domain.setObject(list);
			domain.setMessage("Product image is not empty.");
			domain.setHasError(false);
		} else {
			domain.setMessage("Product image does not exist.");
			domain.setHasError(true);
		}
		return list;
	}

	@Override
	public Domain<Boolean> saveproductsforMerchant(MerchantProducts merchnatproducts) throws Exception {

		Domain<Boolean> s = new Domain<Boolean>();
		Domain<Boolean> products = null;
		Date date = new Date();

		List<String> wpriceList = Arrays.asList(merchnatproducts.getArraystringwprice().split(","));
		List<String> wweightList = Arrays.asList(merchnatproducts.getArraystringweight().split(","));
		List<String> wunitList = Arrays.asList(merchnatproducts.getArraystringwunit().split(","));

		int nnunitid = 0;

		for (int i = 0; i < wweightList.size(); i++) {

			MerchantProducts merchantentity = new MerchantProducts();
			ProductPriceEntity productPriceEntity = new ProductPriceEntity();
			ProductUnitsWeightEntity productUnitsWeightEntity = new ProductUnitsWeightEntity();
			ProductImageEntity productImageEntity = new ProductImageEntity();

			String nweight = wweightList.get(i);
			String nprice = wpriceList.get(i);
			String nunit = wunitList.get(i);
			productPriceEntity.setPrice(nprice);
			productPriceEntity.setProductid(merchnatproducts.getProductid());
			productPriceEntity.setStatus("Y");
			createPrice(productPriceEntity);
			int cpriceid = productPriceEntity.getPriceId();

			ProductUnitsWeightEntity productUnitWeightEntity = new ProductUnitsWeightEntity();
			productUnitWeightEntity = dao.isProductweightExist(nweight, merchnatproducts.getProductid());
			if (productUnitWeightEntity.getWeight() != null) {

				nnunitid = productUnitWeightEntity.getRowId();

			} else {
				productUnitsWeightEntity.setUnitId(Integer.parseInt(nunit));
				productUnitsWeightEntity.setProductId(merchnatproducts.getProductid());
				productUnitsWeightEntity.setWeight(nweight);
				productUnitsWeightEntity.setCrearteOn(date);
				productUnitsWeightEntity.setUpdatedOn(date);
				productUnitsWeightEntity.setNoOfpurchase(0);
				createunitweight(productUnitsWeightEntity);
				nnunitid = productUnitsWeightEntity.getRowId();

			}
			merchantentity.setMerchantid(merchnatproducts.getMerchantid());
			merchantentity.setCategoryid(merchnatproducts.getCategoryid());
			merchantentity.setProductid(merchnatproducts.getProductid());
			merchantentity.setImageid(merchnatproducts.getImageid());
			merchantentity.setUnitid(nnunitid);
			merchantentity.setPriceid(cpriceid);
			merchantentity.setNoOfpurchase(0);
			try {

				if (dao.saveproductsforMerchant(merchantentity)) {
					s.setObject(true);
					s.setMessage("products saved successfully.");
					s.setHasError(false);
				} else {
					s.setMessage("Error.");
					s.setHasError(true);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return s;
	}

	@Override
	public Domain<Boolean> createPrice(ProductPriceEntity ProductpriceEntity) {
		Domain<Boolean> domain = new Domain<Boolean>();

		{
			dao.createPrice(ProductpriceEntity);
			domain.setObject(true);
			domain.setMessage("price saved successfully.");
			domain.setHasError(false);
		}
		return domain;
	}

	@Override
	public Domain<Boolean> createunitweight(ProductUnitsWeightEntity ProductsUnitWeightEntity) {

		Domain<Boolean> domain = new Domain<Boolean>();
		{
			dao.createunitweight(ProductsUnitWeightEntity);
			domain.setObject(true);
			domain.setMessage("Productweight saved successfully.");
			domain.setHasError(false);
		}
		return domain;
	}

	@Override
	public Domain<Boolean> saveproductImage(ProductImageEntity productImageEntity) {
		Domain<Boolean> domain = new Domain<Boolean>();
		{
			dao.saveproductImage(productImageEntity);
			domain.setObject(true);
			domain.setMessage("ProductImahe saved successfully.");
			domain.setHasError(false);
		}
		return domain;
	}

	@Override
	public Domain<List<MerchantProducts>> getMerchantProductList(Integer merchnatid, Integer Categoryid) {
		MerchantProducts merchantProducts = new MerchantProducts();

		List<MerchantProducts> list = new ArrayList<MerchantProducts>();
		Domain<List<MerchantProducts>> domain = new Domain<List<MerchantProducts>>();
		List<ProductUnitsWeightEntity> pwlist = new ArrayList<ProductUnitsWeightEntity>();
		List<ProductPriceEntity> pplist = new ArrayList<ProductPriceEntity>();
		List<Integer> qlist = new ArrayList<Integer>();

		List data = new ArrayList();

		list = dao.getMerchantProductList(merchnatid, Categoryid);
		Iterator<MerchantProducts> it = list.iterator();
		while (it.hasNext()) {
			merchantProducts = it.next();
			ProductImageEntity productImageEntity = dao.getProductsImageById(merchantProducts.getImageid());
			pwlist = dao.getproductweightbyproductid(merchantProducts.getProductid());
			pplist = dao.getpricebyproductid(merchantProducts.getProductid());
			qlist = dao.getQuantitybyid(merchantProducts.getProductid());
			merchantProducts.setImagepath(
					ApiConstants.server_url + "images?image=" + productImageEntity.getImage() + "&folder=image");
			merchantProducts.getPrEntity().setProductweight(pwlist);
			merchantProducts.getPrEntity().setProductPrices(pplist);
			merchantProducts.getPrEntity().setQuantity(qlist);
			data.add(merchantProducts);
		}

		if (data.size() > 0) {

			domain.setObject(data);
			domain.setMessage("Product list is not empty.");
			domain.setHasError(false);
		} else {
			domain.setMessage("Product does not exist.");
			domain.setHasError(true);
		}

		return domain;
	}

	@Override
	public Domain<Boolean> updateAllProductidaftersave(Integer pid, Integer propriceid, Integer proweightid,
			Integer proimgid) {
		Domain<Boolean> domain = new Domain<Boolean>();
		if (dao.updateAllProductidaftersave(pid, propriceid, proweightid, proimgid)) {
			domain.setMessage("Products all mapped id update.");
			domain.setHasError(false);
		} else {
			domain.setMessage("Product all maped id not update successfully.");
			domain.setHasError(true);
		}
		return domain;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MerchantProductDetails> getMerchantProductDetail(Integer merchnatid, Integer Productidid) {
		List<MerchantProductDetails> list1 = new ArrayList<MerchantProductDetails>();
		List list = new ArrayList();

		list = dao.getMerchantProductDetail(merchnatid, Productidid);
		// HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		for (int i = 0; i < list.size(); i++) {
			MerchantProductDetails merchantProductDetails = new MerchantProductDetails();
			// System.out.println("list.get(i).getUnit()"+ list.get(i));
			merchantProductDetails.setUnit(list.get(i).toString());
			merchantProductDetails.setPrice(list.get(i).toString());
			list1.add(merchantProductDetails);
			// hmap.put("price",list.get(1));
		}

		return list;
	}

	@Override
	public Domain<List<MerchantProducts>> getMerchantProductListbymerchant(Integer merchnatid) {
		MerchantProducts merchantProducts = new MerchantProducts();

		List<MerchantProducts> list = new ArrayList<MerchantProducts>();
		Domain<List<MerchantProducts>> domain = new Domain<List<MerchantProducts>>();
		List<ProductUnitsWeightEntity> pwlist = new ArrayList<ProductUnitsWeightEntity>();
		List<ProductPriceEntity> pplist = new ArrayList<ProductPriceEntity>();
		List<Integer> qlist = new ArrayList<Integer>();

		List data = new ArrayList();

		list = dao.getMerchantProductListbymerchantid(merchnatid);
		Iterator<MerchantProducts> it = list.iterator();
		while (it.hasNext()) {
			merchantProducts = it.next();
			ProductImageEntity productImageEntity = dao.getProductsImageById(merchantProducts.getImageid());
			pwlist = dao.getproductweightbyproductid(merchantProducts.getProductid());
			pplist = dao.getpricebyproductid(merchantProducts.getProductid());
			qlist = dao.getQuantitybyid(merchantProducts.getProductid());
			merchantProducts.setImagepath(
					ApiConstants.server_url + "images?image=" + productImageEntity.getImage() + "&folder=image");
			merchantProducts.getPrEntity().setProductweight(pwlist);
			merchantProducts.getPrEntity().setProductPrices(pplist);
			merchantProducts.getPrEntity().setQuantity(qlist);
			data.add(merchantProducts);
		}

		if (data.size() > 0) {

			domain.setObject(data);
			domain.setMessage("Product list is not empty.");
			domain.setHasError(false);
		} else {
			domain.setMessage("Product does not exist.");
			domain.setHasError(true);
		}

		return domain;
	}

	@Override
	public Domain<Boolean> getupdateQuantity(List<MerchantUpdateQuantity.MerchantPurchaseProductDetailEList> as) {
		Domain<Boolean> domain = new Domain<Boolean>();
		System.out.println(as);
		for (MerchantUpdateQuantity.MerchantPurchaseProductDetailEList obj : as) {
			System.out.println(obj);
			int mid = Integer.parseInt(obj.getMerchantID());
			int productid = Integer.parseInt(obj.getProductID());
			int unitid = Integer.parseInt(obj.getWeightID());
			int vendorid = Integer.parseInt(obj.getVendorId());
			int quantity = Integer.parseInt(obj.getQuantity());

			System.out.println(mid);
			if (dao.updateQuantity(mid, productid, unitid, vendorid, quantity)) {
				domain.setMessage("Quantity Successfully Updated");
				domain.setHasError(false);
			} else {
				domain.setMessage("Quantity not Updated.");
				domain.setHasError(true);
			}

		}
		return domain;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Domain<List<UsersEntity>> getMerchantIds(Integer unitid, Integer productid) {
		List<MerchantProducts> list = new ArrayList<MerchantProducts>();
		Domain<List<UsersEntity>> domain = new Domain<List<UsersEntity>>();
		UsersEntity list1 = new UsersEntity();
		ProductPriceEntity plist = new ProductPriceEntity();
		MerchantProducts merchantProducts = new MerchantProducts();

		List userlist = new ArrayList();
		list = dao.getMerchantIds(unitid, productid);
		System.out.println(list);
		Iterator<MerchantProducts> it = list.iterator();
		while (it.hasNext()) {
			merchantProducts = it.next();
			list1 = udao.getallmerchhantdetails(merchantProducts.getMerchantid());
			plist = dao.getPricebyId(merchantProducts.getPriceid());
			list1.setPriceEntity(plist);
			userlist.add(list1);
		}

		if (list.size() > 0) {

			domain.setObject(userlist);
			domain.setHasError(false);
		} else {

			domain.setHasError(true);
		}
		return domain;

	}

	@Override
	public Domain<List<ProductsEntity>> getProductListByaccordingtopurchase(Integer categoryid) {
		ProductsEntity productsEntity = new ProductsEntity();
		MerchantProducts merchantProducts = new MerchantProducts();
		UsersEntity usersEntity = new UsersEntity();

		List<ProductsEntity> list = new ArrayList<ProductsEntity>();
		Domain<List<ProductsEntity>> domain = new Domain<List<ProductsEntity>>();
		list = dao.getProductListByaccordingtopurchase(categoryid);
		if (list.size() > 0) {
			Iterator<ProductsEntity> it = list.iterator();
			while (it.hasNext()) {
				productsEntity = it.next();
				productsEntity.getAllproImageEntity().setImagepath(ApiConstants.server_url + "images?image="
						+ productsEntity.getAllproImageEntity().getImage() + "&folder=image");
				merchantProducts = dao.getMerchantDetails(productsEntity.getProductId());
				usersEntity = dao.getSingleMerchantDetails(merchantProducts.getMerchantid());
				productsEntity.setUsersentity(usersEntity);
			}
			domain.setObject(list);
			domain.setMessage("Product list is not empty.");
			domain.setHasError(false);
		} else {
			domain.setMessage("Product in the category does not exist.");
			domain.setHasError(true);
		}

		return domain;
	}

	@Override
	public Domain<List<ProductUnitsWeightEntity>> getMerchantProductUnitList(Integer productid) {

		List<ProductUnitsWeightEntity> list = new ArrayList<ProductUnitsWeightEntity>();
		Domain<List<ProductUnitsWeightEntity>> domain = new Domain<List<ProductUnitsWeightEntity>>();

		list = dao.getMerchantProductUnitList(productid);
		if (list.size() > 0) {

			domain.setObject(list);
			domain.setMessage("Unit list is not empty.");
			domain.setHasError(false);
		} else {
			domain.setMessage("Unit does not exist.");
			domain.setHasError(true);
		}

		return domain;
	}

	@Override
	public Domain<Boolean> createProductCart(ProductsCartEntity productsCartEntity) {
		Domain<Boolean> domain = new Domain<Boolean>();
		Date date = new Date();
		OrderCart orderCart = new OrderCart();
		OrderCart orderCart2 = new OrderCart();
		OrderCart orderCart3 = new OrderCart();
		int orderid;

		int cuid = productsCartEntity.getCustomeruserid();
		int muid = productsCartEntity.getMerchantid();
		int uwid = productsCartEntity.getUnitweightid();
		int pid = productsCartEntity.getProductid();
		String qty = productsCartEntity.getQuantity();

		productsCartEntity.setStatus("Y");
		productsCartEntity.setCrearteOn(date);
		productsCartEntity.setUpdatedOn(date);

		orderCart.setCustomeruserid(cuid);
		orderCart.setMerchantid(muid);

		if (dao.IsCartOrderExist(cuid, muid).getOrdercartid() != null) {

			orderCart2 = dao.IsCartOrderExist(cuid, muid);
			orderid = orderCart2.getOrdercartid();
			productsCartEntity.setOrdercartid(orderid);

			if (dao.IsProductCartExist(cuid, pid, uwid, muid, qty, orderid)) {
				domain.setMessage("Product Quantity Update.");
				domain.setHasError(false);
			} else {
				dao.createProductCart(productsCartEntity);
				domain.setMessage("Product saved successfully.");
				domain.setHasError(false);
			}

		} else {
			int id = dao.createCartOrder(orderCart);
			int ordcart = id;
			productsCartEntity.setOrdercartid(ordcart);
			if (dao.IsProductCartExist(cuid, pid, uwid, muid, qty, ordcart)) {
				domain.setMessage("Product Quantity Update.");
				domain.setHasError(false);
			} else {
				dao.createProductCart(productsCartEntity);
				domain.setMessage("Product saved successfully.");
				domain.setHasError(false);
			}

		}

		return domain;
	}

	@Override
	public Domain<List<ProductsCartEntity>> getProductsCartDetail(Integer customerid) {
		List<ProductsCartEntity> list = new ArrayList<ProductsCartEntity>();
		Domain<List<ProductsCartEntity>> domain = new Domain<List<ProductsCartEntity>>();
		ProductsCartEntity productsCartEntity = new ProductsCartEntity();
		MerchantProducts merchantProducts = new MerchantProducts();
		ProductPriceEntity productPriceEntity = new ProductPriceEntity();
		ProductImageEntity productImageEntity = new ProductImageEntity();
		List cartlist = new ArrayList();

		list = dao.getProductsCartDetail(customerid);
		Iterator<ProductsCartEntity> it = list.iterator();
		while (it.hasNext()) {
			productsCartEntity = it.next();
			merchantProducts = dao.getPricidforcart(productsCartEntity.getProductid(),
					productsCartEntity.getUnitweightid(), productsCartEntity.getMerchantid());
			productPriceEntity = dao.getPricebyId(merchantProducts.getPriceid());
			productImageEntity = dao.getProductsImageById(merchantProducts.getImageid());
			productImageEntity.setImagepath(
					ApiConstants.server_url + "images?image=" + productImageEntity.getImage() + "&folder=image");
			productsCartEntity.setProductPriceEntity(productPriceEntity);
			productsCartEntity.setProductImageEntity(productImageEntity);
			cartlist.add(productsCartEntity);
		}
		if (list.size() > 0) {
			domain.setObject(cartlist);
			domain.setMessage("Product Cart details.");
			domain.setHasError(false);
		} else {
			domain.setMessage("No cart Detail.");
			domain.setHasError(true);
		}
		return domain;
	}

	@Override
	public Domain<Boolean> createProductCartHistory(ProductCartHistory productCartHistory) {
		Domain<Boolean> domain = new Domain<Boolean>();

		dao.createProductCartHistory(productCartHistory);
		domain.setMessage("Product History placed created");
		domain.setHasError(false);

		return domain;
	}

	@Override
	public Domain<TrackOrderEntity> trackProductOrder(TrackOrderEntity trackOrderEntity) {
		Domain<TrackOrderEntity> domain = new Domain<TrackOrderEntity>();

		String random = RandomText.generateRND();
		String message = "OR" + random;
		trackOrderEntity.setOrderid(message);
		/*
		 * for(int i = 0 ; i < 100 ; i ++) { System.out.println(String.format("%06d",
		 * i+1)); }
		 */

		if (dao.trackProductOrder(trackOrderEntity)) {
			domain.setMessage("OrderPlaced Successfully");
			domain.setHasError(false);
		} else {
			domain.setMessage("OrderPlaced Error");
			domain.setHasError(true);
		}

		return domain;
	}

	@Override
	public Domain<List<ProductCartHistory>> getRecentProductsCartDetail(Integer customerid) {
		List<ProductCartHistory> list = new ArrayList<ProductCartHistory>();
		Domain<List<ProductCartHistory>> domain = new Domain<List<ProductCartHistory>>();
		ProductCartHistory productCartHistory = new ProductCartHistory();
		MerchantProducts merchantProducts = new MerchantProducts();
		ProductPriceEntity productPriceEntity = new ProductPriceEntity();
		ProductImageEntity productImageEntity = new ProductImageEntity();
		List recentlist = new ArrayList();
		List recentProductList = new ArrayList<>();

		list = dao.getRecentProductsCartDetail(customerid);
		Iterator<ProductCartHistory> it = list.iterator();
		while (it.hasNext()) {
			productCartHistory = it.next();
			RecntproductList recntproductList = new RecntproductList();
			merchantProducts = dao.getPricidforcart(productCartHistory.getProductid(),
					productCartHistory.getUnitweightid(), productCartHistory.getMerchantid());
			productPriceEntity = dao.getPricebyId(merchantProducts.getPriceid());
			productImageEntity = dao.getProductsImageById(merchantProducts.getImageid());
			productImageEntity.setImagepath(
					ApiConstants.server_url + "images?image=" + productImageEntity.getImage() + "&folder=image");
			productCartHistory.setProductPriceEntity(productPriceEntity);
			productCartHistory.setProductImageEntity(productImageEntity);
			recentlist.add(productCartHistory);

			recntproductList.setProductid(productCartHistory.getProductid());
			recntproductList.setName(productCartHistory.getProductsEntity().getName());
			recntproductList.setDescription(productCartHistory.getProductsEntity().getDescription());
			recntproductList.setProductimagepath(productCartHistory.getProductImageEntity().getImagepath());
			recntproductList.setWeight(productCartHistory.getProductUnitsWeightEntity().getWeight());
			recntproductList.setPrice(productCartHistory.getProductPriceEntity().getPrice());
			recntproductList.setUnit(productCartHistory.getProductUnitsWeightEntity().getUnitentity().getUnitName());
			recntproductList.setUnitweightid(productCartHistory.getProductUnitsWeightEntity().getRowId());
			recntproductList.setMerchantid(productCartHistory.getMerchantDetails().getUserId());
			recntproductList.setMerchantfirstname(productCartHistory.getMerchantDetails().getFirstName());
			recntproductList.setMerchantlastname(productCartHistory.getMerchantDetails().getLastName());
			recntproductList.setMerchantemailid(productCartHistory.getMerchantDetails().getEmailId());
			recntproductList.setStorename(productCartHistory.getMerchantDetails().getStorename());
			recentProductList.add(recntproductList);

		}
		if (list.size() > 0) {
			domain.setObject(recentProductList);
			domain.setMessage("Recent Product details.");
			domain.setHasError(false);
		} else {
			domain.setObject(recentProductList);
			domain.setMessage("No Recent Detail.");
			domain.setHasError(true);
		}
		return domain;
	}

	@Override
	public Domain<Object> quantityGreator(ProductQuantityLimit productQuantityLimit) {
		Domain<Object> domain = new Domain<Object>();
		MerchantProducts merchantProducts = new MerchantProducts();

		List<String> cartidList = Arrays.asList(productQuantityLimit.getCartidList().split(","));
		List<String> ordercartidList = Arrays.asList(productQuantityLimit.getOrdercartidList().split(","));
		List<String> productididList = Arrays.asList(productQuantityLimit.getProductididList().split(","));
		List<String> unitweightidList = Arrays.asList(productQuantityLimit.getUnitweightidList().split(","));
		List<String> merchantidList = Arrays.asList(productQuantityLimit.getMerchantidList().split(","));
		List<String> customeruseridList = Arrays.asList(productQuantityLimit.getCustomeruseridList().split(","));
		List<String> quantityList = Arrays.asList(productQuantityLimit.getQuantityList().split(","));

		List<String> quantity = new ArrayList<>();
		List<String> remainquant = new ArrayList<>();

		for (int i = 0; i < cartidList.size(); i++) {

			/*
			 * ProductCartHistory productCartHistory = new ProductCartHistory();
			 * 
			 * productCartHistory.setOrdercartid(Integer.parseInt(ordercartidList.get(i)));
			 * productCartHistory.setCustomeruserid(Integer.parseInt(customeruseridList.get(
			 * i)));
			 * productCartHistory.setMerchantid(Integer.parseInt(merchantidList.get(i)));
			 * productCartHistory.setProductid(Integer.parseInt(productididList.get(i)));
			 * productCartHistory.setUnitweightid(Integer.parseInt(unitweightidList.get(i)))
			 * ; productCartHistory.setQuantity(quantityList.get(i));
			 */

			merchantProducts = dao.getPricidforcart(Integer.parseInt(productididList.get(i)),
					Integer.parseInt(unitweightidList.get(i)), Integer.parseInt(merchantidList.get(i)));

			if (merchantProducts.getQuantity() >= Integer.parseInt(quantityList.get(i))) {

				// quantity.add(ordercartidList.get(i));
				// domain.setMessage("true");
				System.out.println("true");

			} else {

				quantity.add(cartidList.get(i));
				remainquant.add(Integer.toString(merchantProducts.getQuantity()));
				/*
				 * quantity.set(i, cartidList.get(i)); quantity.set(i+1,
				 * Integer.toString(merchantProducts.getQuantity()));
				 */
				domain.setHasError(true);
				domain.setValue(remainquant);
				domain.setMessage("false");
				domain.setObject(quantity);
				// domain.setObject(merchantProducts.getQuantity());
			}

		}

		if (remainquant.size() > 0) {

			return domain;

		} else {

			domain.setMessage("true");
			return domain;
		}

	}

	@Override
	public Domain<List<ProductsEntity>> getDailyProductListByaccordingtnoOfpurchase() {

		ProductsEntity productsEntity = new ProductsEntity();
		MerchantProducts merchantProducts = new MerchantProducts();
		List<ProductsEntity> list = new ArrayList<ProductsEntity>();
		Domain<List<ProductsEntity>> domain = new Domain<List<ProductsEntity>>();
		List dailyProductList = new ArrayList<>();
		UsersEntity usersEntity = new UsersEntity();
		list = dao.getDailyProductListByaccordingtnoOfpurchase();
		if (list.size() > 0) {
			Iterator<ProductsEntity> it = list.iterator();
			while (it.hasNext()) {
				productsEntity = it.next();
				DailyUseProducts dailyUseProducts = new DailyUseProducts();
				merchantProducts = dao.getMerchantids(productsEntity.getProductId(), productsEntity.getAllprWeightEntity().getRowId(), productsEntity.getAllproPriceEntity().getPriceId());
				usersEntity = dao.getSingleMerchantDetails(merchantProducts.getMerchantid());
				productsEntity.getAllproImageEntity().setImagepath(ApiConstants.server_url + "images?image="
						+ productsEntity.getAllproImageEntity().getImage() + "&folder=image");
				dailyUseProducts.setProductid(productsEntity.getProductId());
				dailyUseProducts.setName(productsEntity.getName());
				dailyUseProducts.setDescription(productsEntity.getDescription());
				dailyUseProducts.setProductimagepath(productsEntity.getAllproImageEntity().getImagepath());
				dailyUseProducts.setProductUnitsWeightEntity(productsEntity.getAllprWeightEntity());
				dailyUseProducts.setProductPriceEntity(productsEntity.getAllproPriceEntity());
				dailyUseProducts.setMerchantid(usersEntity.getUserId());
				dailyUseProducts.setMerchantfirstname(usersEntity.getFirstName());
				dailyUseProducts.setMerchantlastname(usersEntity.getLastName());
				dailyUseProducts.setMerchantemailid(usersEntity.getEmailId());
				dailyUseProducts.setStorename(usersEntity.getStorename());

				dailyProductList.add(dailyUseProducts);
			}
			domain.setObject(dailyProductList);
			domain.setMessage("Product list is not empty.");
			domain.setHasError(false);
		} else {
			domain.setMessage("Product in the category does not exist.");
			domain.setHasError(true);
		}

		return domain;
	}

	@Override
	public Domain<List<OrderStatusEntity>> getOrdersStatus() {
		OrderStatusEntity orderStatusEntity = new OrderStatusEntity();

		List<OrderStatusEntity> list = new ArrayList<OrderStatusEntity>();
		Domain<List<OrderStatusEntity>> domain = new Domain<List<OrderStatusEntity>>();
		list = dao.getOrdersStatus();
		if (list.size() > 0) {
			domain.setObject(list);
			domain.setMessage("Order Status list is");
			domain.setHasError(false);
		} else {
			domain.setMessage("Order Status does not exist.");
			domain.setHasError(true);
		}

		return domain;
	}

	@Override
	public Domain<List<ProductCartHistory>> getOrderPlacedMerchantaccept(Integer meid) {

		List<ProductCartHistory> list = new ArrayList<ProductCartHistory>();
		Domain<List<ProductCartHistory>> domain = new Domain<List<ProductCartHistory>>();

		ProductCartHistory productCartHistory = new ProductCartHistory();
		MerchantProducts merchantProducts = new MerchantProducts();
		ProductPriceEntity productPriceEntity = new ProductPriceEntity();
		ProductImageEntity productImageEntity = new ProductImageEntity();

		List orderlist = new ArrayList();
		List merchantorderlist = new ArrayList<>();

		list = dao.getOrderPlacedMerchantaccept(meid);
		Iterator<ProductCartHistory> it = list.iterator();
		while (it.hasNext()) {
			productCartHistory = it.next();
			MerchantOrder merchantOrder = new MerchantOrder();
			merchantProducts = dao.getPricidforcart(productCartHistory.getProductid(),
					productCartHistory.getUnitweightid(), productCartHistory.getMerchantid());
			productPriceEntity = dao.getPricebyId(merchantProducts.getPriceid());
			productImageEntity = dao.getProductsImageById(merchantProducts.getImageid());
			productImageEntity.setImagepath(
					ApiConstants.server_url + "images?image=" + productImageEntity.getImage() + "&folder=image");
			productCartHistory.setProductPriceEntity(productPriceEntity);
			productCartHistory.setProductImageEntity(productImageEntity);
			orderlist.add(productCartHistory);

			merchantOrder.setOrderid(productCartHistory.getOrderid());
			merchantOrder.setStatusid(productCartHistory.getOrderStatusEntity());
			merchantOrder.setWeight(productCartHistory.getWeight());
			merchantOrder.setPrice(productCartHistory.getPrice());
			merchantOrder.setUnit(productCartHistory.getUnit());
			merchantOrder.setQuantity(productCartHistory.getQuantity());
			merchantOrder.setCreatetime(productCartHistory.getCrearteOn());
			merchantOrder.setOrdacceptdate(productCartHistory.getOrdacceptdate());
			merchantOrder.setOrdcanceldate(productCartHistory.getOrdcanceldate());
			merchantOrder.setOrdoutfordate(productCartHistory.getOrdoutfordate());
			merchantOrder.setOrddeliverdate(productCartHistory.getOrddeliverdate());
			merchantOrder.setProductid(productCartHistory.getProductsEntity().getProductId());
			merchantOrder.setProductname(productCartHistory.getProductsEntity().getName());
			merchantOrder.setProductdesc(productCartHistory.getProductsEntity().getDescription());
			merchantOrder.setProductimagepath(productCartHistory.getProductImageEntity().getImagepath());
			merchantOrder.setCustomerid(productCartHistory.getCustomerDetails().getUserId());
			merchantOrder.setCustomerfirstname(productCartHistory.getCustomerDetails().getFirstName());
			merchantOrder.setCustomerlastname(productCartHistory.getCustomerDetails().getLastName());
			merchantOrder.setCustomeremailid(productCartHistory.getCustomerDetails().getEmailId());
			merchantOrder.setAddressLine1(productCartHistory.getCustomerDetails().getAddressLine1());
			merchantOrder.setAddressLine2(productCartHistory.getCustomerDetails().getAddressLine2());
			merchantOrder.setCity(productCartHistory.getCustomerDetails().getCity());
			merchantOrder.setState(productCartHistory.getCustomerDetails().getState());
			merchantOrder.setPincode(productCartHistory.getCustomerDetails().getPincode());
			merchantorderlist.add(merchantOrder);

		}

		if (list.size() > 0) {
			domain.setObject(merchantorderlist);
			domain.setMessage("Product Order list is not empty.");
			domain.setHasError(false);
		} else {
			domain.setMessage("Product does not exist.");
			domain.setHasError(true);
		}

		return domain;
	}

	@Override
	public Domain<List<ProductCartHistory>> getOrderPlacedCustomer(Integer cuid) {
		List<ProductCartHistory> list = new ArrayList<ProductCartHistory>();
		Domain<List<ProductCartHistory>> domain = new Domain<List<ProductCartHistory>>();

		ProductCartHistory productCartHistory = new ProductCartHistory();
		MerchantProducts merchantProducts = new MerchantProducts();
		ProductPriceEntity productPriceEntity = new ProductPriceEntity();
		ProductImageEntity productImageEntity = new ProductImageEntity();

		List orderlist = new ArrayList();
		List customerorderlist = new ArrayList<>();

		list = dao.getOrderPlacedCustomer(cuid);
		Iterator<ProductCartHistory> it = list.iterator();
		while (it.hasNext()) {
			productCartHistory = it.next();
			CustomerOrder customerOrder = new CustomerOrder();
			merchantProducts = dao.getPricidforcart(productCartHistory.getProductid(),
					productCartHistory.getUnitweightid(), productCartHistory.getMerchantid());
			productPriceEntity = dao.getPricebyId(merchantProducts.getPriceid());
			productImageEntity = dao.getProductsImageById(merchantProducts.getImageid());
			productImageEntity.setImagepath(
					ApiConstants.server_url + "images?image=" + productImageEntity.getImage() + "&folder=image");
			productCartHistory.setProductPriceEntity(productPriceEntity);
			productCartHistory.setProductImageEntity(productImageEntity);
			orderlist.add(productCartHistory);

			customerOrder.setOrderid(productCartHistory.getOrderid());
			customerOrder.setStatusid(productCartHistory.getOrderStatusEntity());
			customerOrder.setWeight(productCartHistory.getWeight());
			customerOrder.setPrice(productCartHistory.getPrice());
			customerOrder.setUnit(productCartHistory.getUnit());
			customerOrder.setQuantity(productCartHistory.getQuantity());
			customerOrder.setCreatetime(productCartHistory.getCrearteOn());
			customerOrder.setOrdacceptdate(productCartHistory.getOrdacceptdate());
			customerOrder.setOrdcanceldate(productCartHistory.getOrdcanceldate());
			customerOrder.setOrdoutfordate(productCartHistory.getOrdoutfordate());
			customerOrder.setOrddeliverdate(productCartHistory.getOrddeliverdate());

			customerOrder.setProductid(productCartHistory.getProductsEntity().getProductId());
			customerOrder.setProductname(productCartHistory.getProductsEntity().getName());
			customerOrder.setProductdesc(productCartHistory.getProductsEntity().getDescription());
			customerOrder.setProductimagepath(productCartHistory.getProductImageEntity().getImagepath());

			customerOrder.setMerchantid(productCartHistory.getCustomerDetails().getUserId());
			customerOrder.setMerchantfirstname(productCartHistory.getMerchantDetails().getFirstName());
			customerOrder.setMerchantlastname(productCartHistory.getMerchantDetails().getLastName());

			customerOrder.setMerchantemailid(productCartHistory.getCustomerDetails().getEmailId());
			customerOrder.setAddressLine1(productCartHistory.getCustomerDetails().getAddressLine1());
			customerOrder.setAddressLine2(productCartHistory.getCustomerDetails().getAddressLine2());
			customerOrder.setCity(productCartHistory.getCustomerDetails().getCity());
			customerOrder.setState(productCartHistory.getCustomerDetails().getState());
			customerOrder.setPincode(productCartHistory.getCustomerDetails().getPincode());
			customerOrder.setMerchantemailid(productCartHistory.getMerchantDetails().getEmailId());
			customerorderlist.add(customerOrder);
		}

		if (list.size() > 0) {
			domain.setObject(customerorderlist);
			domain.setMessage("Product Order list is not empty.");
			domain.setHasError(false);
		} else {
			domain.setMessage("Product does not exist.");
			domain.setHasError(true);
		}

		return domain;
	}

	@Override
	public Domain<ProductsEntity> getProductListBySearch(Integer pid) {
		ProductsEntity productsEntity = new ProductsEntity();
		MerchantProducts merchantProducts = new MerchantProducts();
		UsersEntity usersEntity = new UsersEntity();

		Domain<ProductsEntity> domain = new Domain<ProductsEntity>();
		productsEntity = dao.getProductDetails(pid);
		if (productsEntity.getProductId() > 0) {

			productsEntity.getAllproImageEntity().setImagepath(ApiConstants.server_url + "images?image="
					+ productsEntity.getAllproImageEntity().getImage() + "&folder=image");
			merchantProducts = dao.getMerchantDetails(productsEntity.getProductId());
			usersEntity = dao.getSingleMerchantDetails(merchantProducts.getMerchantid());
			productsEntity.setUsersentity(usersEntity);

			domain.setObject(productsEntity);
			domain.setMessage("Product list is not empty.");
			domain.setHasError(false);
		} else {
			domain.setMessage("Product in the category does not exist.");
			domain.setHasError(true);
		}

		return domain;
	}

	@Override
	public Domain<ProductCartHistory> UpdateOrderstatus(String orid, Integer posid) {
		Domain<ProductCartHistory> domain = new Domain<ProductCartHistory>();

		Date date = new Date();

		if (dao.UpdateOrderstatus(orid, posid)) {
			domain.setMessage("Status Update Successfully");
			domain.setHasError(false);
		} else {
			domain.setMessage("Not updated");
			domain.setHasError(true);
		}

		return domain;
	}

	@Override
	public Domain<List<UsersEntity>> getNearestMerchant(Integer customerid, Float radius) {
		List<UsersEntity> list = new ArrayList<UsersEntity>();
		Domain<List<UsersEntity>> domain = new Domain<List<UsersEntity>>();
		UsersEntity user = new UsersEntity();

		user = udao.getallmerchhantdetails(customerid);
		list = udao.getNearestmerchantDetail(user, radius);

		List NearestMerchnat = new ArrayList<>();

		Iterator<UsersEntity> it = list.iterator();
		while (it.hasNext()) {
			user = it.next();
			userAddressDetails userAddressDetails = new userAddressDetails();

			userAddressDetails.setUserId(user.getUserId());
			userAddressDetails.setFirstName(user.getFirstName());
			userAddressDetails.setLastName(user.getLastName());
			userAddressDetails.setAddressLine1(user.getAddressLine1());
			userAddressDetails.setAddressLine2(user.getAddressLine2());
			userAddressDetails.setEmailId(user.getEmailId());
			userAddressDetails.setStorename(user.getStorename());

			userAddressDetails.setPhone1(user.getPhone1());
			userAddressDetails.setState(user.getState());
			userAddressDetails.setCity(user.getCity());
			userAddressDetails.setPincode(user.getPincode());
			userAddressDetails.setLat(user.getLat());
			userAddressDetails.setLng(user.getLng());
			userAddressDetails.setSponsorby(user.getSponsorby());
			userAddressDetails.setStartshoptime(user.getStartshoptime());
			userAddressDetails.setEndshoptime(user.getEndshoptime());
			userAddressDetails.setImagepath(
					ApiConstants.server_url + "images?image=" + user.getImagename() + "&folder=profileimage");
			NearestMerchnat.add(userAddressDetails);

		}
		if (list.size() > 0) {
			domain.setObject(NearestMerchnat);
			domain.setMessage("Nearest Merchants details.");
			domain.setHasError(false);
		} else {
			domain.setObject(NearestMerchnat);
			domain.setMessage("No Merchants Detail.");
			domain.setHasError(true);
		}
		return domain;
	}

	@Override
	public Domain<CustomerViewPage> customerviewpage(Integer customerid,Float radius) {
		Domain<CustomerViewPage> domain = new Domain<CustomerViewPage>();
		CustomerViewPage customerViewPage = new CustomerViewPage();
		Domain<List<ProductsEntity>> daily = new Domain<List<ProductsEntity>>();
		Domain<List<ProductCartHistory>> recent = new Domain<List<ProductCartHistory>>();
		Domain<List<UsersEntity>> narestMerchnat = new Domain<List<UsersEntity>>();
		daily = getDailyProductListByaccordingtnoOfpurchase();
		recent = getRecentProductsCartDetail(customerid);
		narestMerchnat = getNearestMerchant(customerid, radius);

		customerViewPage.setDaily(daily.getObject());
		customerViewPage.setRecent(recent.getObject());
		customerViewPage.setNearestMerchants(narestMerchnat.getObject());

		if (daily.getHasError()) {
			domain.setMessage("Status Update Successfully");
			domain.setHasError(true);
		} else {
			domain.setObject(customerViewPage);
			domain.setMessage("Home page list");
			domain.setHasError(false);
		}

		return domain;
	}

	@Override
	public Domain<List<ProductsEntity>> getProductListByaccordingMerchantandcategory(Integer categoryid,
			Integer merchantid) {

		MerchantProducts merchantProductss = new MerchantProducts();
		Domain<List<ProductsEntity>> domain = new Domain<List<ProductsEntity>>();
		Domain<ProductsEntity> domain2 = new Domain<ProductsEntity>();
		UsersEntity usersEntity = new UsersEntity();
		List<ProductsEntity> list1 = new ArrayList<ProductsEntity>();
		List<MerchantProducts> list = new ArrayList<MerchantProducts>();
		List accpdt = new ArrayList<>();

		list = dao.getProductListByaccordingMerchantandcategory(categoryid, merchantid);
		if (list.size() > 0) {
			Iterator<MerchantProducts> it = list.iterator();
			while (it.hasNext()) {
				merchantProductss = it.next();
				list1 = dao.getProductListByaccordingtopurchase(merchantProductss.getCategoryid());
				domain2 = getProductDetails(merchantProductss.getProductid());
				// merchantProducts=dao.getMerchantDetails(merchantProductss.getProductid());
				usersEntity = dao.getSingleMerchantDetails(merchantProductss.getMerchantid());
				domain2.getObject().setUsersentity(usersEntity);
				accpdt.add(domain2.getObject());
			}
			domain.setObject(accpdt);
			domain.setMessage("Product list is not empty.");
			domain.setHasError(false);
		} else {
			domain.setMessage("Product in the category does not exist.");
			domain.setHasError(true);
		}

		return domain;
	}

	@Override
	public Domain<List<ProductAdervtiesment>> getNearestMerchantAdvertiesment(Integer customerid, Float radius) {
		List<UsersEntity> list = new ArrayList<UsersEntity>();
		Domain<List<ProductAdervtiesment>> domain = new Domain<List<ProductAdervtiesment>>();
		UsersEntity user = new UsersEntity();
		List<ProductAdervtiesment> listad = new ArrayList<ProductAdervtiesment>();
		user = udao.getallmerchhantdetails(customerid);
		list = udao.getNearestmerchantDetail(user, radius);

//		List NearestMerchnatAdvert = new ArrayList<>();

		Iterator<UsersEntity> it = list.iterator();
		while (it.hasNext()) {
			user = it.next();
			listad = udao.getNearestmerchantAdvertiesment(user.getUserId());
			listad.forEach(h -> h.setImagepath(ApiConstants.server_url + "images?image=" + h.getImagename() + "&folder=advertiesments"));


		}
		if (list.size() > 0) {
			domain.setObject(listad);
			domain.setMessage("Nearest Merchants Adevertiesment details.");
			domain.setHasError(false);
		} else {
			domain.setObject(listad);
			domain.setMessage("No Adevertiesment Detail.");
			domain.setHasError(true);
		}
		return domain;
	}

	@Override
	public Domain<String> saveCategoryforMerchant(MerchantCategory merchantCategory) {
		  Domain<String> s = new Domain<String>();
			if(dao.saveCategoryforMerchant(merchantCategory))
			{
				s.setMessage("Category added successfully.");
				s.setHasError(false);		
			}
			else
			{
				s.setMessage("Category not added");
				s.setHasError(true);		
			}
		return s;
	}

	@Override
	public Domain<List<ProductUnitsWeightEntity>> getMerchantProductUnitDetail(Integer merchnatid, Integer Productidid) {
		
		Domain<List<ProductUnitsWeightEntity>> domain = new Domain<List<ProductUnitsWeightEntity>>();
		MerchantProducts mlist = new MerchantProducts();
		ProductUnitsWeightEntity ulist = new ProductUnitsWeightEntity();
		List<MerchantProducts> list = new ArrayList<MerchantProducts>();
		List unitlist = new ArrayList<>();
		list = dao.getMerchantunitsExits(merchnatid, Productidid); // ignore method name only for reuse
		Iterator<MerchantProducts> it = list.iterator();
		while (it.hasNext()) {
			
			mlist = it.next();
			ulist = dao.getUnitweightbyid(mlist.getUnitid());
			unitlist.add(ulist);
		}
        System.out.println(unitlist);
		if (list.size() > 0) {

			domain.setObject(unitlist);
			domain.setMessage("list is not empty.");
			domain.setHasError(false);
		} else {
			domain.setMessage("empty list.");
			domain.setHasError(true);
		}

		return domain;
	}

	/*
	 * @Override public Domain<ProductsEntity> getProductByPhone(String phone,
	 * String password) { ProductsEntity Product = new ProductsEntity();
	 * Domain<ProductsEntity> response = new Domain<ProductsEntity>(); Product =
	 * dao.getProductByPhone(phone); if (CommonUtil.isNull(Product.getPhone1())) {
	 * response.setMessage("Product Id or password is invalid");
	 * response.setHasError(true); } else { String decryptedPass =
	 * EncryptionUtil.decrypt(Product.getPasswd()); if
	 * (decryptedPass.equals(password)) { response.setObject(Product);
	 * response.getObject().setPasswd("");
	 * response.setMessage("Login is successful."); response.setHasError(false); }
	 * else { response.setMessage("Product Id or password is invalid.");
	 * response.setHasError(true); } }
	 * 
	 * return response; }
	 * 
	 * @Override public Domain<ProductsEntity> getProductByEmail(String emailId,
	 * String password) { ProductsEntity Product = new ProductsEntity();
	 * Domain<ProductsEntity> response = new Domain<ProductsEntity>(); Product =
	 * dao.getProductByEmail(emailId); if (CommonUtil.isNull(Product.getPhone1())) {
	 * response.setMessage("Product Id or password is invalid");
	 * response.setHasError(true); } else { String decryptedPass =
	 * EncryptionUtil.decrypt(Product.getPasswd()); if
	 * (decryptedPass.equals(password)) { response.setObject(Product);
	 * response.getObject().setPasswd("");
	 * response.setMessage("Login is successful."); response.setHasError(false); }
	 * else { response.setMessage("Product Id or password is invalid.");
	 * response.setHasError(true); } } return response; }
	 * 
	 * @Override public Domain<String> updateProduct(ProductsEntity Product) {
	 * String password = ""; //Product.setPasswd(password); Domain<String> s = new
	 * Domain<String>();
	 * 
	 * if(dao.isProductExist(Product.getPhone1())) {
	 * s.setMessage("Mobile number already exist."); s.setHasError(true); } else {
	 * dao.updateProduct(Product); s.setMessage("Data saved successfully.");
	 * s.setHasError(false); } return s;
	 * 
	 * }
	 * 
	 * @Override public Domain<String> createProduct(ProductsEntity Product) {
	 * Domain<String> s = new Domain<String>();
	 * 
	 * String password = EncryptionUtil.encrypt(Product.getPasswd());
	 * Product.setPasswd(password);
	 * 
	 * if(dao.isProductExist(Product.getPhone1())) {
	 * s.setMessage("Mobile number already exist."); s.setHasError(true); } else {
	 * dao.createProduct(Product); s.setMessage("Data saved successfully.");
	 * s.setHasError(false); } return s; }
	 * 
	 * @Override public String generateToken(String phone) { String random =
	 * RandomText.generateRND(); String message =
	 * "The one time password genereted is " + random +
	 * ". This will be valid for 10 minutes."; RestTemplate restTemplate = new
	 * RestTemplate();
	 * 
	 * String resourceUrl = "http://localhost:8080/SMSWeb/outbound.do?mobile=" +
	 * phone + "&message=" + message; ResponseEntity<String> response =
	 * restTemplate.getForEntity(resourceUrl, String.class); if
	 * (response.getStatusCodeValue() == 200) { return random; } else { return
	 * response.getStatusCode().toString(); } }
	 */

}