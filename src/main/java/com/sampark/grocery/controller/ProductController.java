package com.sampark.grocery.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.sampark.grocery.constant.ApiConstants;
import com.sampark.grocery.constant.ResponseObject;
import com.sampark.grocery.dao.ProductDao;
import com.sampark.grocery.model.CustomerViewPage;
import com.sampark.grocery.model.Domain;
import com.sampark.grocery.model.MerchantCategory;
import com.sampark.grocery.model.MerchantProductDetails;
import com.sampark.grocery.model.MerchantProducts;
import com.sampark.grocery.model.MerchantUpdateQuantity;
import com.sampark.grocery.model.OrderStatusDate;
import com.sampark.grocery.model.MerchantUpdateQuantity.MerchantPurchaseProductDetailEList;
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
import com.sampark.grocery.service.ProductService;
import com.sampark.grocery.util.RandomText;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
		
	@Autowired
	ProductService  productService;
	
	@Autowired
	ProductDao dao;
	
	@RequestMapping(value = "/getproductlistbyid", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<ProductsEntity>>> getProductByCategoryId(@RequestParam("id") String id) {
		int ids=Integer.parseInt(id);  
		Domain<List<ProductsEntity>> entity = productService.getProductListByCategory(ids);
		return new ResponseEntity<Domain<List<ProductsEntity>>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getproductlistbyname", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<ProductsEntity>>> getProductByCategoryName(@RequestParam("name") String name) {
		Domain<List<ProductsEntity>> entity = productService.getProductsByName(name);
		return new ResponseEntity<Domain<List<ProductsEntity>>>(entity, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/updateproduct",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<Boolean>> updateProduct(@RequestBody ProductsEntity productEntity)
	{
		Domain<Boolean> product=productService.updateProduct(productEntity);
		return new ResponseEntity<>(product,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/savecategory",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<Boolean>> saveCategory(@RequestBody ProductCategoryEntity ProductCategoryEntity)
	{
		Domain<Boolean> categoryEntity=productService.createCategory(ProductCategoryEntity);
		return new ResponseEntity<>(categoryEntity,HttpStatus.OK);	
	}
	
	
	@RequestMapping(value = "/getcategorylist", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<ProductCategoryEntity>>> getCategoryList(@RequestParam("userid") String id) {
		int cids=Integer.parseInt(id);
		Domain<List<ProductCategoryEntity>> entity = productService.getCategoryList(cids);
		return new ResponseEntity<Domain<List<ProductCategoryEntity>>>(entity,HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/getproductdetails", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<ProductsEntity>> getProductdetails(@RequestParam("id") String id) {
		int ids=Integer.parseInt(id);  
		Domain<ProductsEntity> entity = productService.getProductDetails(ids);
		return new ResponseEntity<Domain<ProductsEntity>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getproductAddshowdetails", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<ProductsEntity>> getproductAdddshowetails(@RequestParam("id") String id,@RequestParam("mid") String mid) {
		int ids=Integer.parseInt(id);  
		int mids=Integer.parseInt(mid);
		Domain<ProductsEntity> entity= productService.getProductListByproductid(ids,mids);
		
		return new ResponseEntity<Domain<ProductsEntity>>(entity,HttpStatus.OK);	
		
	}
	
	@RequestMapping(value = "/saveProductsforMerchant",method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<Boolean>> saveproductsForMerchant(
			@RequestParam("merchantid") String merchantId,
			@RequestParam("categoryid") String categoryid,
			@RequestParam("productid") String productid,
			@RequestParam("imageid") String imageid,
			@RequestParam("wprice") String wprice,
			@RequestParam("wweight") String wweight,
			@RequestParam("wunit") String wunit,
			@RequestParam(value = "image", required=false) MultipartFile files) {
		
		Domain<Boolean> products = null;
		Date date = new Date();
		int merchantIds=Integer.parseInt(merchantId);
		int categoryids=Integer.parseInt(categoryid); 
		int productids=Integer.parseInt(productid); 
		int imageids=Integer.parseInt(imageid); 
		/*int wunits= Integer.parseInt(wunit);*/
		MerchantProducts merchantentity = new MerchantProducts();
		
			    merchantentity.setMerchantid(merchantIds);
				merchantentity.setCategoryid(categoryids);
				merchantentity.setProductid(productids);
				merchantentity.setImageid(imageids);
				merchantentity.setArraystringwunit(wunit);
				merchantentity.setArraystringweight(wweight);
				merchantentity.setArraystringwprice(wprice);
				
				
				try {
					products = productService.saveproductsforMerchant(merchantentity);
				} catch (Exception e) {
					
					e.printStackTrace();
				} 
           return new ResponseEntity<>(products,HttpStatus.OK);

			/*Iterator<String> it=wpriceList.iterator();
			while(it.hasNext())
			{
				String newid=it.next();
				productPriceEntity.setPrice(newid);
				productPriceEntity.setProductid(productids);
				productPriceEntity.setStatus("Y");
				Domain<Boolean> productprice=productService.createPrice(productPriceEntity);
				int cpriceid = productPriceEntity.getPriceId();
			}
		

			Iterator<String> wit=wweightList.iterator();
			while(wit.hasNext())
			{
				String newwid=wit.next();
				productUnitsWeightEntity.setUnitId(wunits);
				productUnitsWeightEntity.setProductId(productids);
				productUnitsWeightEntity.setWeight(newwid);
				Domain<Boolean> productunitweight=productService.createunitweight(productUnitsWeightEntity);
				 int cunitid = productUnitsWeightEntity.getRowId();
			
			}*/
			
		/*
			try {
				byte[] bytes11 = files.getBytes();
				File dir11 = new File(ApiConstants.image_path);
				if (!dir11.exists()){
					dir11.mkdirs();
				}						
				File serverFile11 = new File(dir11.getAbsolutePath()
						+ File.separator + files.getOriginalFilename());						
				productImageEntity.setImage(files.getOriginalFilename());
				productImageEntity.setProductid(productids);
				Domain<Boolean> productImage=productService.saveproductImage(productImageEntity);
				 cimageid=productImage.getId();
				if(productImage.getHasError().equals("false"))
				{
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(serverFile11));
					stream.write(bytes11);
					
					stream.close();                                                  
					//logger.info("files uploaded successfully ");
		        System.out.println("files uploaded successfully");
		        Domain<Boolean> updateids=productService.updateAllProductidaftersave(proid, proprice, proweight, proimgid);
		        
				}
				
			}
			catch (Exception e) {
				e.printStackTrace();
			
			}
		*/
		
		
	}
	
	@RequestMapping(value="/saveproduct",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<Boolean>> saveProduct(
			@RequestParam("merchantid") String merchantId,
			@RequestParam("productname") String productname,
			@RequestParam("productdesc") String productdesc,
			@RequestParam("categoryid") String categoryid,
			@RequestParam("price") String price,
			@RequestParam("unitid") String unitid,
			@RequestParam("unitweight") String unitweight,
			@RequestParam("image") MultipartFile files)
	
	
	{
		
		int merids=Integer.parseInt(merchantId);
		int catids=Integer.parseInt(categoryid);
		Domain<Boolean> s = new Domain<Boolean>();
		Date date = new Date();
		ProductsEntity productentity = new ProductsEntity();
		ProductImageEntity productImageEntity = new ProductImageEntity();
		
		List<String> newpriceList = Arrays.asList(price.split(",")); 
		List<String> newweightList = Arrays.asList(unitweight.split(","));
		List<String> newprounitid = Arrays.asList(unitid.split(","));
		
		productentity.setName(productname);
		productentity.setDescription(productdesc);
		productentity.setCategoryId(catids);
		productentity.setStatus("Y");
		productentity.setNoOfpurchase(0);
		productentity.setCreatedOn(date);
		productentity.setUpdatedOn(date);
		
		Domain<Boolean> product=productService.createProduct(productentity);
		int proid = product.getId();
		int proimgid = 0;
		
		String fname = null;
		 fname = UUID.randomUUID().toString()
					+ files.getOriginalFilename().substring(files.getOriginalFilename().lastIndexOf('.'));
		
		try {
		byte[] bytes11 = files.getBytes();
		File dir11 = new File(ApiConstants.image_path);
		if (!dir11.exists()){
			dir11.mkdirs();
		}						
		File serverFile11 = new File(dir11.getAbsolutePath()
				+ File.separator + fname);						
		productImageEntity.setImage(fname);
		productImageEntity.setProductid(proid);
		productImageEntity.setStatus("Y");
		productImageEntity.setCrearteOn(date);
		productImageEntity.setUpdatedOn(date);
		Domain<Boolean> productImage=productService.saveproductImage(productImageEntity);
	     proimgid=productImageEntity.getImageId();
		
		if(productImage.getHasError().equals(false))
		{
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(serverFile11));
			stream.write(bytes11);
			
			stream.close();                                                  
			//logger.info("files uploaded successfully ");
        System.out.println("files uploaded successfully");
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		
		}
		for (int i = 0; i < newweightList.size(); i++) {
			
			MerchantProducts merchantentity = new MerchantProducts();
			ProductPriceEntity productPriceEntity = new ProductPriceEntity();
			ProductUnitsWeightEntity productUnitsWeightEntity = new ProductUnitsWeightEntity();
			
			
			 String nweight=newweightList.get(i);
	    	 String nprice=newpriceList.get(i);
	    	 String nunitid=newprounitid.get(i);
			
			productPriceEntity.setPrice(nprice);
			productPriceEntity.setProductid(proid);
			productPriceEntity.setStatus("Y");
			
			productUnitsWeightEntity.setUnitId(Integer.parseInt(nunitid));
			productUnitsWeightEntity.setProductId(proid);
			productUnitsWeightEntity.setWeight(nweight);
			productUnitsWeightEntity.setNoOfpurchase(0);
			productUnitsWeightEntity.setCrearteOn(date);
			productUnitsWeightEntity.setUpdatedOn(date);
			
			if(product.getHasError().equals(false)) {
				
				Domain<Boolean> productprice=productService.createPrice(productPriceEntity);
				int proprice = productPriceEntity.getPriceId();
				Domain<Boolean> productunitweight=productService.createunitweight(productUnitsWeightEntity);
				int proweight = productUnitsWeightEntity.getRowId();
				
					    merchantentity.setMerchantid(merids);
						merchantentity.setCategoryid(catids);
						merchantentity.setProductid(proid);
						merchantentity.setImageid(proimgid);
						merchantentity.setUnitid(proweight);
						merchantentity.setPriceid(proprice);
						merchantentity.setNoOfpurchase(0);
						
						
						try {
							if(dao.saveproductsforMerchant(merchantentity)) {
								s.setObject(true);
								s.setMessage("products saved successfully.");
								s.setHasError(false);		
								}else {
									s.setMessage("Error.");
									s.setHasError(true);
								}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					System.out.println(s);
					Domain<Boolean> updateids=productService.updateAllProductidaftersave(proid, proprice, proweight, proimgid);
			        System.out.println(updateids);
				
			}else {
				
				return new ResponseEntity<>(product,HttpStatus.OK);
			}
			
			
		}
		return new ResponseEntity<>(product,HttpStatus.OK);	
		
	}
	
	@RequestMapping(value = "/getMerchantproductlist", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<MerchantProducts>>> getMerchantproductlist(
			@RequestParam("merchantid") String merchantid,
			@RequestParam("categoryid") String categoryid) {
		int merchantids=Integer.parseInt(merchantid);  
		int categoryids=Integer.parseInt(categoryid);
		Domain<List<MerchantProducts>> entity = productService.getMerchantProductList(merchantids, categoryids);
		return new ResponseEntity<Domain<List<MerchantProducts>>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getMerchantproductdetails", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity <List<MerchantProductDetails>> getMerchantProductdetails(@RequestParam("mid") String mid,@RequestParam("pid") String pid) {
		int mids=Integer.parseInt(mid);  
		int pids=Integer.parseInt(pid);  
		List<MerchantProductDetails> entity = productService.getMerchantProductDetail(mids, pids);
		return new ResponseEntity<List<MerchantProductDetails>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllMerchantproductlist", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<MerchantProducts>>> getAllMerchantproductlist(
			@RequestParam("merchantid") String merchantid) {
		int merchantids=Integer.parseInt(merchantid); 
		Domain<List<MerchantProducts>> entity = productService.getMerchantProductListbymerchant(merchantids);
		return new ResponseEntity<Domain<List<MerchantProducts>>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getUpdateQuantity", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<Boolean>> updateProductQuantity(@RequestBody List<MerchantUpdateQuantity.MerchantPurchaseProductDetailEList> merchantUpdateQuantity)
	{
		/*System.out.println(new Gson().toJson(merchantUpdateQuantity, MerchantUpdateQuantity.MerchantPurchaseProductDetailEList.class));*/
		System.out.println(merchantUpdateQuantity);
		Domain<Boolean> productquantity=productService.getupdateQuantity(merchantUpdateQuantity);
		return new ResponseEntity<>(productquantity,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/getUpdatesQuantity", method = RequestMethod.POST)
	public @ResponseBody Domain<List<ProductsEntity>> updatesProductQuantity(@RequestParam("quantity") String quantity)
	{
		Domain<List<ProductsEntity>> domain=new Domain<List<ProductsEntity>>();
		System.out.println(quantity);
		String myCustom_JSONResponse="{\"merchantPurchaseProductDetailEList\":"+quantity+"}";
		MerchantUpdateQuantity productList = new Gson().fromJson(myCustom_JSONResponse, MerchantUpdateQuantity.class);
		for(MerchantUpdateQuantity.MerchantPurchaseProductDetailEList obj : productList.getMerchantPurchaseProductDetailEList()) {
			int mid=Integer.parseInt(obj.getMerchantID());
			int productid=Integer.parseInt(obj.getProductID());
			int unitid=Integer.parseInt(obj.getWeightID());
			int vendorid=Integer.parseInt(obj.getVendorId());
			int quantity1=Integer.parseInt(obj.getQuantity());
			
			if(dao.updateQuantity(mid, productid, unitid, vendorid, quantity1)) {
				domain.setMessage("Quantity Successfully Updated");
				domain.setHasError(false);
			} else {
				domain.setMessage("Quantity not Updated.");
				domain.setHasError(true);			
			}
		}
		return domain;
	
		
		
	}
		
	@RequestMapping(value = "/getMerchantDetails", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity <Domain<List<UsersEntity>>> getMerchantdetails(@RequestParam("unitid") String unitid,@RequestParam("productid") String productid) {
		int uids=Integer.parseInt(unitid);  
		int pids=Integer.parseInt(productid);  
		Domain<List<UsersEntity>> entity = productService.getMerchantIds(uids, pids);
		return new ResponseEntity<Domain<List<UsersEntity>>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getListbyAccByNoPurchase", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<ProductsEntity>>> getlistbyaccbynopurchase(@RequestParam("categoryid") String categoryid) {
		int ids=Integer.parseInt(categoryid);  
		Domain<List<ProductsEntity>> entity = productService.getProductListByaccordingtopurchase(ids);
		return new ResponseEntity<Domain<List<ProductsEntity>>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getListbyAccBycatMer", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<ProductsEntity>>> getProductListByaccordingMerchantandcategory(@RequestParam("categoryid") String categoryid,@RequestParam("merchantid") String merchantid) {
		int ids=Integer.parseInt(categoryid);  
		int mids = Integer.parseInt(merchantid);
		Domain<List<ProductsEntity>> entity = productService.getProductListByaccordingMerchantandcategory(ids, mids);
		return new ResponseEntity<Domain<List<ProductsEntity>>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getUnitbyProductid", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<ProductUnitsWeightEntity>>> getProductByProductId(@RequestParam("productid") String productid) {
		int ids=Integer.parseInt(productid);  
		Domain<List<ProductUnitsWeightEntity>> entity = productService.getMerchantProductUnitList(ids);
		return new ResponseEntity<Domain<List<ProductUnitsWeightEntity>>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value="/saveCartDetails",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<Boolean>> saveCart(@RequestBody ProductsCartEntity produstscartEntity)
	{
		Domain<Boolean> produstscart=productService.createProductCart(produstscartEntity);
		return new ResponseEntity<>(produstscart,HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/getCustomerCartDetail", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<ProductsCartEntity>>> getProductsCartDetail(
			@RequestParam("customerid") String customerid) {
		int cids=Integer.parseInt(customerid); 
		Domain<List<ProductsCartEntity>> entity = productService.getProductsCartDetail(cids);
		return new ResponseEntity<Domain<List<ProductsCartEntity>>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value="/placeOrder",method=RequestMethod.POST)
	public @ResponseBody ResponseObject saveProduct(
			@RequestParam("cartid") String cartid,
			@RequestParam("ordercartid") String orderid,
			@RequestParam("productid") String productid,
			@RequestParam("weight") String weight,
			@RequestParam("price") String price,
			@RequestParam("unit") String unit,
			@RequestParam("unitweightid") String unitweightid,
			@RequestParam("merchantid") String merchantid,
			@RequestParam("customeruserid") String customeruserid,
			@RequestParam("quantity") String quantity)
	{
		Domain<TrackOrderEntity> trackentity=new Domain<TrackOrderEntity>();
		TrackOrderEntity trackOrderEntity = new TrackOrderEntity();
		Domain<Object> domain = new Domain<Object>();
		
		
		ResponseObject rsObject=new ResponseObject();
		
		
		ProductQuantityLimit productQuantityLimit = new ProductQuantityLimit();
		productQuantityLimit.setCartidList(cartid);
		productQuantityLimit.setCustomeruseridList(customeruserid);
		productQuantityLimit.setMerchantidList(merchantid);
		productQuantityLimit.setOrdercartidList(orderid);
		productQuantityLimit.setProductididList(productid);
		productQuantityLimit.setUnitweightidList(unitweightid);
		productQuantityLimit.setQuantityList(quantity);
		System.out.println(productQuantityLimit);
		domain=productService.quantityGreator(productQuantityLimit);
		
		if(domain.getMessage().equals("true")) {
			
			Date date = new Date();
			List<String> cartidList = Arrays.asList(cartid.split(",")); 
			List<String> ordercartidList = Arrays.asList(orderid.split(","));
			List<String> productididList = Arrays.asList(productid.split(","));
			List<String> unitweightidList = Arrays.asList(unitweightid.split(",")); 
			List<String> merchantidList = Arrays.asList(merchantid.split(","));
			List<String> customeruseridList = Arrays.asList(customeruserid.split(","));
			List<String> quantityList = Arrays.asList(quantity.split(","));
			List<String> weightList = Arrays.asList(weight.split(","));
			List<String> priceList = Arrays.asList(price.split(","));
			List<String> unitList = Arrays.asList(unit.split(","));
			
			String random = RandomText.generateRND();
			String message = "OR" + random;
			 
			for (int i = 0; i < cartidList.size(); i++) {
				
				ProductCartHistory productCartHistory = new ProductCartHistory();
				
				productCartHistory.setOrderid(message);
				productCartHistory.setStatusid(1);
				productCartHistory.setOrdercartid(Integer.parseInt(ordercartidList.get(i)));
				productCartHistory.setCustomeruserid(Integer.parseInt(customeruseridList.get(i)));
				productCartHistory.setMerchantid(Integer.parseInt(merchantidList.get(i)));
				productCartHistory.setProductid(Integer.parseInt(productididList.get(i)));
				productCartHistory.setUnitweightid(Integer.parseInt(unitweightidList.get(i)));
				productCartHistory.setQuantity(quantityList.get(i));
				productCartHistory.setWeight(Integer.parseInt(weightList.get(i)));
				productCartHistory.setPrice(Integer.parseInt(priceList.get(i)));
				productCartHistory.setUnit(unitList.get(i));
				productCartHistory.setCrearteOn(date);
				productCartHistory.setUpdatedOn(date);
				
				productService.createProductCartHistory(productCartHistory);
				
				if(dao.IncreaseProductnoOfPurchase(Integer.parseInt(productididList.get(i)))) {
					System.out.println("Increase product no of purchase");
				}else {
					System.out.println("Not product increase");
				}
				if(dao.IncreaseProductweightnoOfPurchase(Integer.parseInt(unitweightidList.get(i)))) {
					System.out.println("Increase unit weight no of purchase");
				}else {
					System.out.println("Not unit weight increase");
				}
				if(dao.IncreaseProductMerchanttnoOfPurchase(Integer.parseInt(productididList.get(i)), Integer.parseInt(unitweightidList.get(i)), Integer.parseInt(merchantidList.get(i)),Integer.parseInt(quantityList.get(i)))) {
					System.out.println("Increase merchant product no of purchase and decrease quantity");
				}else {
					System.out.println("Not merchant product increase");
				}
	            /* if(i==0) {
				trackOrderEntity.setCrearteOn(date);
				trackOrderEntity.setUpdatedOn(date);
				trackOrderEntity.setOrdercartid(Integer.parseInt(ordercartidList.get(i)));
				trackOrderEntity.setStatus_id(1);
				 trackentity=productService.trackProductOrder(trackOrderEntity);
	              }*/
				
				if(dao.deleteProductCart(Integer.parseInt(cartidList.get(i)))) {
					System.out.println("product delete");
					
				}else {
					System.out.println("product not delete");
				}
				
			}
			rsObject.setObject(trackentity);
			rsObject.setHasError(false);
			rsObject.setMessage("Order Placed Successfully");
			 return rsObject;
			
		}else {
			
			rsObject.setObject(domain);
			rsObject.setHasError(true);
			rsObject.setMessage("quantity is greator");
			return rsObject;
		}
		
		
	}
	
	@RequestMapping(value = "/getRecentProductDetail", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<ProductCartHistory>>> getProductsRecentDetail(
			@RequestParam("customerid") String customerid) {
		int cids=Integer.parseInt(customerid); 
		Domain<List<ProductCartHistory>> entity = productService.getRecentProductsCartDetail(cids);
		return new ResponseEntity<Domain<List<ProductCartHistory>>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getdailyProductlist", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<ProductsEntity>>> getDailyProductListByaccordingtnoOfpurchase() {
	
		Domain<List<ProductsEntity>> entity = productService.getDailyProductListByaccordingtnoOfpurchase();
		return new ResponseEntity<Domain<List<ProductsEntity>>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getNearestMerchantDetail", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<UsersEntity>>> getNearestMerchantDetail(
			@RequestParam("customerid") String customerid,
			@RequestParam("radius") String radius) {
		int cids=Integer.parseInt(customerid);
		float rad = Float.parseFloat(radius);
		Domain<List<UsersEntity>> entity = productService.getNearestMerchant(cids,rad);
		return new ResponseEntity<Domain<List<UsersEntity>>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getOrderStatus", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<OrderStatusEntity>>> getOrdersStatus() {
	
		Domain<List<OrderStatusEntity>> entity = productService.getOrdersStatus();
		return new ResponseEntity<Domain<List<OrderStatusEntity>>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getOrderforMerchant", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<ProductCartHistory>>> getOrderPlacedMerchantaccept(
			@RequestParam("meid") String meid) {
		int merids=Integer.parseInt(meid); 
		Domain<List<ProductCartHistory>> entity = productService.getOrderPlacedMerchantaccept(merids);
		return new ResponseEntity<Domain<List<ProductCartHistory>>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getOrderforCustomer", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<ProductCartHistory>>> getOrderPlacedCustomer(
			@RequestParam("cuid") String cuid) {
		int custids=Integer.parseInt(cuid); 
		Domain<List<ProductCartHistory>> entity = productService.getOrderPlacedCustomer(custids);
		return new ResponseEntity<Domain<List<ProductCartHistory>>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getProductDetailsBySearch", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<ProductsEntity>> getProductListBySearch(@RequestParam("productid") String pid) {
		int ids=Integer.parseInt(pid);  
		Domain<ProductsEntity> entity = productService.getProductListBySearch(ids);
		return new ResponseEntity<Domain<ProductsEntity>>(entity,HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/MerchantOrderStatus",method=RequestMethod.POST)
	public @ResponseBody Domain<ProductCartHistory> updateStatus(
			@RequestParam("orderid") String orderid,
			@RequestParam("statusid") String statusid
			)
	{
		System.out.println(orderid);
		Domain cartHistory=new Domain();
		Date date = new Date();
		/*List<String> carthidList = Arrays.asList(carthid.split(",")); 
		List<String> statusidList = Arrays.asList(statusid.split(","));*/
		 OrderStatusDate orderStatusDate = new OrderStatusDate();
	     orderStatusDate.setDate(date);
	     orderStatusDate.setId(statusid);
			ProductCartHistory productCartHistory=new ProductCartHistory();
		cartHistory = productService.UpdateOrderstatus(orderid, Integer.parseInt(statusid));
		cartHistory.setObject(orderStatusDate);
		return cartHistory;
		
	}
	
	@RequestMapping(value = "/getNearestMerchantAdvertisement", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<ProductAdervtiesment>>> getNearestMerchantAdvertesment(
			@RequestParam("customerid") String customerid,
			@RequestParam("radius") String radius) {
		int cids=Integer.parseInt(customerid);
		float rad = Float.parseFloat(radius);
		Domain<List<ProductAdervtiesment>> entity = productService.getNearestMerchantAdvertiesment(cids, rad);
		return new ResponseEntity<Domain<List<ProductAdervtiesment>>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCustomerhomePage", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<CustomerViewPage>> CustomerViewpage(
			@RequestParam("customerid") String customerid,
			@RequestParam("radius") String radius) {
		int cids=Integer.parseInt(customerid); 
		float rad = Float.parseFloat(radius);
		Domain<CustomerViewPage> entity = productService.customerviewpage(cids,rad);
		return new ResponseEntity<Domain<CustomerViewPage>>(entity,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/savecategoryformerchant",method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<String>> saveCatrgoryForMerchant(@RequestParam("merchantid") String merchantId,@RequestParam("categoryid") String categoryId) {
		
		MerchantCategory merchantCategory = new MerchantCategory();
		int cmids=Integer.parseInt(merchantId); 
		int cids=Integer.parseInt(categoryId); 
		merchantCategory.setMerchantid(cmids);
		merchantCategory.setCategoryid(cids);
		merchantCategory.setStatus("Y");
		Domain<String> catmerchant= productService.saveCategoryforMerchant(merchantCategory);
		return new ResponseEntity<>(catmerchant,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getUnitbyProductidMerchant", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<ProductUnitsWeightEntity>>> getUnitbyProductidMerchant(@RequestParam("productid") String productid,@RequestParam("merchantid") String merchantid) {
		int ids=Integer.parseInt(productid); 
		int mids = Integer.parseInt(merchantid);
		Domain<List<ProductUnitsWeightEntity>> entity = productService.getMerchantProductUnitDetail(mids, ids);
		return new ResponseEntity<Domain<List<ProductUnitsWeightEntity>>>(entity,HttpStatus.OK);
	}
}
