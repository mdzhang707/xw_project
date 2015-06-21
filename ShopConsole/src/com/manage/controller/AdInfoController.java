package com.manage.controller;

import com.jfinal.core.Controller;
import com.manage.model.AdInfo;
import com.manage.service.AdInfoManage;

public class AdInfoController extends Controller {
	
	
	public void list(){
		renderJson("adInfos", AdInfoManage.list());
	}
	
	public void create(){
		AdInfo ad = this.getModel(AdInfo.class);
		renderJson("succeed", AdInfoManage.create(ad));
	}
	
	public void edit(){
		AdInfo ad = this.getModel(AdInfo.class);
		renderJson("succeed", AdInfoManage.edit(ad));
	}
	
	public void delete(){
		AdInfo ad = this.getModel(AdInfo.class);
		renderJson("succeed", AdInfoManage.delete(ad.getInt("iid")));
	}
}
