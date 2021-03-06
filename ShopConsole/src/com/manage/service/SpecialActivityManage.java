package com.manage.service;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.manage.model.ModelUtil;
import com.manage.model.SpecialActivity;

public class SpecialActivityManage {
	public static final List<SpecialActivity> list(SpecialActivity as, int offset, int limit){
		StringBuffer where = new StringBuffer("from specialactivity where deleted=0");
		List<Object> params = new ArrayList<Object>();
		if(as.getStr("status") != null && !"".equals(as.getStr("status").trim())){
			where.append(" and status=?");
			params.add(as.getStr("status"));
		}
		where.append(" order by startTime");
		Page<SpecialActivity> sas = null;
		if(params.size() == 0){
			sas = SpecialActivity.dao.paginate(offset, limit, "select *", where.toString());
		}else{
			sas = SpecialActivity.dao.paginate(offset, limit, "select * ", where.toString(), params.toArray());
		}
		return sas.getList();
	}
	
	public static final boolean create(SpecialActivity sa){
		ModelUtil.generateUUID(sa);
		return sa.save();
	}
	
	public static final boolean edit(SpecialActivity sa){
		SpecialActivity model = SpecialActivity.dao.findFirst("select * from specialactivity where deleted=0 and uuid=?", sa.getStr("uuid"));
		if(model != null){
			sa.set("iid", sa.getInt("iid"));
			return sa.update();
		}
		return false;
	}
	
	public static final boolean delete(String uuid){
		SpecialActivity sa = SpecialActivity.dao.findFirst("select * from specialactivity where deleted=0 and uuid=?", uuid);
		if(sa != null){
			return ModelUtil.softDelete(sa);
		}
		return false;
	}
	
	public static final SpecialActivity getByUuid(String uuid){
		return SpecialActivity.dao.findFirst("select * from specialactivity where uuid=?", uuid);		
	}
}
