package com.manage.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.manage.model.ModelUtil;
import com.manage.model.ShopComments;

public class ShopCommentsManage {
	public static final List<ShopComments> list(ShopComments comment, int offset, int limit){
		StringBuffer where = new StringBuffer("from shopcomments where deleted=0");
		List<Object> params = new ArrayList<Object>();
		if(comment.getStr("shopId") != null && !"".equals(comment.getStr("shopId"))){
			where.append(" and shopId=?");
			params.add(comment.getStr("shopId").trim());
		}
		if(comment.getStr("orderid") != null && !"".equals(comment.getStr("orderid"))){
			where.append(" and orderid=?");
			params.add(comment.getStr("orderid").trim());
		}
		if(comment.getStr("productId") != null && !"".equals(comment.getStr("productId"))){
			where.append(" and productId=?");
			params.add(comment.getStr("productId").trim());
		}
		where.append(" order by commentTime desc");
		Page<ShopComments> comments = null;
		if(params.size() == 0){
			comments = ShopComments.dao.paginate(offset, limit, "select * ", where.toString());
		}else{
			comments = ShopComments.dao.paginate(offset, limit, "select * ", where.toString(), params.toArray());
		}
		return comments.getList();
	}
	
	public static final BigDecimal stats(ShopComments comment){
		StringBuffer where = new StringBuffer(
				"select sum(grade)/count(*) as avgGrade from shopcomments where deleted=0");
		List<Object> params = new ArrayList<Object>();
		if(comment.getStr("shopId") != null && !"".equals(comment.getStr("shopId"))){
			where.append(" and shopId=?");
			params.add(comment.getStr("shopId").trim());
		}
		if(comment.getStr("productId") != null && !"".equals(comment.getStr("productId"))){
			where.append(" and productId=?");
			params.add(comment.getStr("productId").trim());
		}
		BigDecimal avgGrade = Db.queryBigDecimal(where.toString(), params.toArray());
		return avgGrade;
	}
	
	public static boolean create(ShopComments comment){
		ModelUtil.generateUUID(comment);
		comment.set("commitTime", new Date());
		return comment.save();
	}
	
	public static boolean edit(ShopComments comment){
		ShopComments model = ShopComments.dao.findFirst(
				"select * from shopcomments where uuid=?", comment.getStr("uuid"));
		if(model != null){
			comment.set("iid", model.getInt("iid"));
			return comment.update();
		}
		return false;
	}
	
	public static boolean delete(String uuid){
		ShopComments model = ShopComments.dao.findFirst(
				"select * from shopcomments where uuid=?", uuid);
		if(model != null){
			ModelUtil.softDelete(model);
		}
		return false;
	}
}
