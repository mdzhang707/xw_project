package com.manage.controller;

import com.jfinal.core.Controller;
import com.manage.model.ProfitReturnSetting;
import com.manage.service.ProfitReturnSettingManage;

public class ProfitReturnSettingController extends Controller {
	public void create(){
		ProfitReturnSetting prs = getModel(ProfitReturnSetting.class);
		renderJson("succeed", ProfitReturnSettingManage.create(prs));
	}
	
	public void edit(){
		ProfitReturnSetting prs = getModel(ProfitReturnSetting.class);
		renderJson("succeed", ProfitReturnSettingManage.edit(prs));
	}
}
