package com.manage.controller;

import com.jfinal.core.Controller;
import com.manage.model.CityCode;
import com.manage.service.CityCodeManage;

public class CityCodeController extends Controller {
	
	
	public void list(){
		CityCode cityCode = this.getModel(CityCode.class);
		renderJson("cities", CityCodeManage.list(cityCode));
	}
}
