package com.manage.controller;

import com.jfinal.core.Controller;
import com.manage.model.ShopTypeDic;
import com.manage.service.ShopTypeDicManage;

public class ShopTypeDicController extends Controller {

	public void list(){
		ShopTypeDic std = this.getModel(ShopTypeDic.class);
		renderJson("types", ShopTypeDicManage.list(std));
	}
	
	public void create(){
		ShopTypeDic std = this.getModel(ShopTypeDic.class);
		renderJson("types", ShopTypeDicManage.create(std));
	}
	
	public void edit(){
		ShopTypeDic std = this.getModel(ShopTypeDic.class);
		renderJson("types", ShopTypeDicManage.edit(std));
	}
	
	public void delete(){
		ShopTypeDic std = this.getModel(ShopTypeDic.class);
		renderJson("types", ShopTypeDicManage.delete(std.getStr("uuid")));
	}
}
