package com.sampark.grocery.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sampark.grocery.constant.ApiConstants;
import com.sampark.grocery.dao.ProductDao;
import com.sampark.grocery.model.DailyUseProducts;
import com.sampark.grocery.model.Domain;
import com.sampark.grocery.model.ProductsEntity;
import com.sampark.grocery.service.ProductService;

@Controller
@RequestMapping(value = "/web")
public class WebController {
	
	@Autowired
	ProductService  productService;
	
	@Autowired
	ProductDao dao;
	
	@RequestMapping(value = "/getproductlistbhhyid", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<ProductsEntity>>> getProductByCategoryId(@RequestParam("id") String id) {
		int ids=Integer.parseInt(id);  
		Domain<List<ProductsEntity>> entity = productService.getProductListByCategory(ids);
		
		ProductsEntity productsEntity = new ProductsEntity();
		List<ProductsEntity> list = new ArrayList<ProductsEntity>();
		Domain<List<ProductsEntity>> domain = new Domain<List<ProductsEntity>>();
		List dailyProductList = new ArrayList<>();
		list = dao.getProductListByCategory(ids);
		if (list.size() > 0) {
			Iterator<ProductsEntity> it = list.iterator();
			while (it.hasNext()) {
				productsEntity = it.next();
				DailyUseProducts dailyUseProducts = new DailyUseProducts();
				productsEntity.getAllproImageEntity().setImagepath(ApiConstants.server_url + "images?image="
						+ productsEntity.getAllproImageEntity().getImage() + "&folder=image");
				dailyUseProducts.setProductid(productsEntity.getProductId());
				dailyUseProducts.setName(productsEntity.getName());
				dailyUseProducts.setDescription(productsEntity.getDescription());
				dailyUseProducts.setProductimagepath(productsEntity.getAllproImageEntity().getImagepath());
				dailyUseProducts.setProductUnitsWeightEntity(productsEntity.getAllprWeightEntity());
				dailyUseProducts.setProductPriceEntity(productsEntity.getAllproPriceEntity());
//				dailyUseProducts.setMerchantid(usersEntity.getUserId());
//				dailyUseProducts.setMerchantfirstname(usersEntity.getFirstName());
//				dailyUseProducts.setMerchantlastname(usersEntity.getLastName());
//				dailyUseProducts.setMerchantemailid(usersEntity.getEmailId());
//				dailyUseProducts.setStorename(usersEntity.getStorename());

				dailyProductList.add(dailyUseProducts);

			}
			domain.setObject(dailyProductList);
			domain.setMessage("Product list is not empty.");
			domain.setHasError(false);
		} else {
			domain.setMessage("Product in the category does not exist.");
			domain.setHasError(true);
		}

		return new ResponseEntity<Domain<List<ProductsEntity>>>(entity,HttpStatus.OK);
		}

}
