package com.manage.service;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.manage.model.CheapProduct;
import com.manage.model.ModelUtil;

public class CheapProductManage {
	public static final List<CheapProduct> list(CheapProduct cp, int offset, int limit){
		StringBuffer where = new StringBuffer("from cheapproduct where deleted=0");
		List<Object> params = new ArrayList<Object>();
		if(cp.getStr("activeId") != null && !"".equals(cp.getStr("activeId").trim())){
			where.append(" and activeId=?");
			params.add(cp.getStr("activeId"));
		}
		if(cp.getStr("shopId") != null && !"".equals(cp.getStr("shopId"))){
			where.append(" and shopId=?");
			params.add(cp.getStr("shopId").trim());
		}
		Page<CheapProduct> cps = null;
		if(params.size() == 0){
			cps = CheapProduct.dao.paginate(offset, limit, "select *", where.toString());
		}else{
			cps = CheapProduct.dao.paginate(offset, limit, "select * ", where.toString(), params.toArray());
		}
		return cps.getList();
	}
	
	public static final boolean create(CheapProduct cp){
		return cp.save();
	}
	
	public static final boolean delete(int iid){
		CheapProduct cp = CheapProduct.dao.findFirst(
				"select * from cheapproduct where deleted=0 and iid=?", iid);
		if(cp != null){
			return ModelUtil.softDelete(cp);
		}
		return false;
	}
	
	public static final boolean edit(CheapProduct cp){
		return cp.update();
	}
}
