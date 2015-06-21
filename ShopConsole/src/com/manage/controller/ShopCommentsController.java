package com.manage.controller;

import com.jfinal.core.Controller;
import com.manage.model.ShopComments;
import com.manage.service.ShopCommentsManage;

public class ShopCommentsController extends Controller {
	
	
	public void list(){
		ShopComments comment = this.getModel(ShopComments.class);
		int offset = this.getParaToInt("offset");
		int limit = this.getParaToInt("limit");
		renderJson("comments", ShopCommentsManage.list(comment, offset, limit));
	}
	
	public void stats(){
		ShopComments comment = this.getModel(ShopComments.class);
		renderJson("comments", ShopCommentsManage.stats(comment));
	}
	
	public void create(){
		ShopComments comment = this.getModel(ShopComments.class);
		renderJson("comments", ShopCommentsManage.create(comment));
	}
	
	public void edit(){
		ShopComments comment = this.getModel(ShopComments.class);
		renderJson("comments", ShopCommentsManage.edit(comment));
	}
	
	public void delete(){
		ShopComments comment = this.getModel(ShopComments.class);
		renderJson("comments", ShopCommentsManage.delete(comment.getStr("uuid")));
	}
}
