package com.manage.controller;

import com.jfinal.core.Controller;
import com.manage.model.OrderInfo;
import com.manage.service.OrderInfoManager;

public class OrderController extends Controller{
	public void list(){
		OrderInfo order = getModel(OrderInfo.class);
		int offset = this.getParaToInt("offset");
		int limit = this.getParaToInt("limit");
		renderJson("shops", OrderInfoManager.list(order, offset, limit));		
	}
	
	public void create(){
		OrderInfo order = getModel(OrderInfo.class);
		//orderinfodetails
		renderJson("succeed", OrderInfoManager.create(order));
	}

	public void delete(){
		String uuid = this.getPara("uuid");
		renderJson(OrderInfoManager.delete(uuid));
	}
	
	public void changeStatus(){
		OrderInfo order = new OrderInfo();
		order.setStatus(this.getParaToInt("status"));
		renderJson("succeed", OrderInfoManager.changeStatus(order));
	}
}
