package com.manage.service;

import java.util.ArrayList;
import java.util.List;

import com.manage.model.ModelUtil;
import com.manage.model.ShopTypeDic;

public class ShopTypeDicManage {
	public static final List<ShopTypeDic> list(ShopTypeDic std){
		StringBuffer where = new StringBuffer("select * from shoptypedic where deleted=0");
		List<Object> params = new ArrayList<Object>();
		if(std.getInt("level") != null){
			where.append(" and level=?");
			params.add(std.getInt("level"));
		}
		if(std.getStr("parentUuid") != null && !"".equals(std.getStr("parentUid").trim())){
			where.append(" and parentUuid=?");
			params.add(std.getStr("parentUid"));
		}
		if(params.size() == 0){
			return ShopTypeDic.dao.find(where.toString());
		}
		return ShopTypeDic.dao.find(where.toString(), params.toArray());
	}
	
	public static final boolean create(ShopTypeDic std){
		ModelUtil.generateUUID(std);
		return std.save();
	}
	
	public static final boolean delete(String uuid){
		ShopTypeDic std = ShopTypeDic.dao.findFirst("select * form shoptypedic where deleted=0 and uuid=?", uuid);
		if(std !=null){
			return ModelUtil.softDelete(std);
		}
		return false;
	}
	
	public static final boolean edit(ShopTypeDic std){
		ShopTypeDic model = ShopTypeDic.dao.findFirst("select * from shoptypedic where deleted=0 and uuid=?", std.getStr("uuid"));
		if(model != null){
			std.set("iid", model.getInt("iid"));
			return std.update();
		}
		return false;
	}
}
