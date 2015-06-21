package com.manage.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.manage.model.ShopInfo;
import com.manage.service.ShopInfoManage;

public class ShopController extends Controller{
	public void shopList(){
		render("/view/shop/shopList.jsp");
	}
	
	public void list(){
		ShopInfo shop = getModel(ShopInfo.class);
		int offset = this.getParaToInt("offset");
		int limit = this.getParaToInt("limit");
		renderJson("shops", ShopInfoManage.list(shop, offset, limit));		
	}
	
	public void getShopList(){
	   ShopInfo shop=getModel(ShopInfo.class);
		List<ShopInfo>list=ShopInfoManage.list(shop, 1, 50);
		renderJson(list);
	}
	
	public void createShop(){
		render("/view/shop/createShop.jsp");
	}
	
	public void create(){
		ShopInfo shop = getModel(ShopInfo.class);
		shop.set("type", 1);
		renderJson("succeed", ShopInfoManage.create(shop));
	}
	
	public void edit(){
		ShopInfo shop = this.getModel(ShopInfo.class);
		renderJson("succeed", ShopInfoManage.edit(shop));
	}
	
	public void changePassword(){
		ShopInfo shop = new ShopInfo();
		shop.setUUID(this.getPara("uuid"));
		shop.setPassword(this.getPara("password"));
		renderJson("result", ShopInfoManage.changePassword(shop));
	}
	
	public void editShop(){
		String uid=getPara("uid");
		ShopInfo shop=ShopInfo.dao.findFirst("select * from ShopInfo where uuid=?", uid);
		this.setSessionAttr("shop", shop);
		render("/view/shop/editShop.jsp");
	}
	
	public void delete(){
		String uuid = this.getPara("uid");
		renderJson(ShopInfoManage.delete(uuid));
	}

	public void checkNameExists(){
		String name=getPara("name");
		String key=getPara("key");
		renderJson("legal", !ShopInfoManage.checkNameExists(name,key));
	}
}
