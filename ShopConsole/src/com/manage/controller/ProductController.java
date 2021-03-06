package com.manage.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.manage.model.Product;
import com.manage.service.ProductManage;

public class ProductController extends Controller{
	public void productList(){
		render("/view/products/productList.jsp");
	}

	public void list(){
		Product product = getModel(Product.class);
		int offset = this.getParaToInt("offset");
		int limit = this.getParaToInt("limit");
		renderJson("shops", ProductManage.list(product, offset, limit));		
	}
	
	public void createProduct(){
		render("/view/products/createProducts.jsp");
	}
	
	public void create(){
		Product product = getModel(Product.class);
		product.set("type", 1);
		renderJson("succeed", ProductManage.create(product));
	}
	
	public void editProduct(){
		String uid=getPara("uuid");
		Product product=ProductManage.getByUuid(uid);
		setSessionAttr("product", product);
		render("/view/products/editProduct.jsp");
	}
	
	public void edit(){
		Product product =getModel(Product.class);
		renderJson("succeed", ProductManage.edit(product));
	}
	
	public void delete(){
		String uuid = this.getPara("uuid");
		renderJson(ProductManage.delete(uuid));
	}
	
	/**
	 * 获取商品信息列表
	 */
	public void getProductList(){
		Product product=getModel(Product.class);
		List<Product> list=product.dao.find("select * from product");
		renderJson(list);
	}
}
