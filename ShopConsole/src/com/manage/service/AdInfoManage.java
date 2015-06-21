package com.manage.service;

import java.util.List;

import com.manage.model.AdInfo;
import com.manage.model.ModelUtil;

public class AdInfoManage {
	public static final List<AdInfo> list(){
		return AdInfo.dao.find("select * from adinfo");
	}
	
	public static final boolean create(AdInfo ad){
		return ad.save();
	}
	
	public static final boolean delete(int iid){
		AdInfo ad = AdInfo.dao.findFirst(
				"select * from adinfo where deleted=0 and iid=?", iid);
		if(ad != null){
			return ModelUtil.softDelete(ad);
		}
		return false;
	}
	
	public static final boolean edit(AdInfo ad){
		return ad.update();
	}
}