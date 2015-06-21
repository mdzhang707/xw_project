package com.manage.service;

import java.util.ArrayList;
import java.util.List;

import com.manage.model.CityCode;

public class CityCodeManage {
	public static final List<CityCode> list(CityCode city){
		StringBuffer where = new StringBuffer("select * from citycode where deleted=0");
		List<Object> params = new ArrayList<Object>();
		if(city.getInt("level") != null){
			where.append(" and level=?");
			params.add(city.getInt("level"));
		}
		if(city.getStr("parentName") != null && !"".equals(city.getStr("parentName").trim())){
			where.append(" and parentName=?");
			params.add(city.getStr("parentName"));
		}
		if(city.getStr("parentCode") != null && !"".equals(city.getStr("parentCode").trim())){
			where.append(" and parentCode=?");
			params.add(city.getStr("parentCode").trim());
		}
		where.append(" order by convert(name using gb2312)");
		if(params.size() == 0){
			return CityCode.dao.find(where.toString());
		}
		return CityCode.dao.find(where.toString(), params.toArray());
	}
}
