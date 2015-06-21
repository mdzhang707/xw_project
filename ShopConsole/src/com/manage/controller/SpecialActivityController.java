package com.manage.controller;

import com.jfinal.core.Controller;
import com.manage.model.SpecialActivity;
import com.manage.service.SpecialActivityManage;

public class SpecialActivityController extends Controller {

	
	public void list(){
		SpecialActivity sa = this.getModel(SpecialActivity.class);
		int offset = this.getParaToInt("offset");
		int limit = this.getParaToInt("limit");
		renderJson("list", SpecialActivityManage.list(sa, offset, limit));
	}
	
	public void create(){
		SpecialActivity sa = this.getModel(SpecialActivity.class);
		renderJson("list", SpecialActivityManage.create(sa));
	}
	
	public void edit(){
		SpecialActivity sa = this.getModel(SpecialActivity.class);
		renderJson("list", SpecialActivityManage.edit(sa));
	}
	
	public void delete(){
		SpecialActivity sa = this.getModel(SpecialActivity.class);
		renderJson("list", SpecialActivityManage.delete(sa.getStr("uuid")));
	}
}
