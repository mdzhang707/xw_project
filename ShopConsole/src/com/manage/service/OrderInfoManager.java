package com.manage.service;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.manage.model.ModelUtil;
import com.manage.model.OrderInfo;
import com.manage.model.OrderInfoDetail;

public class OrderInfoManager {
	public static final List<OrderInfo> list(OrderInfo order, int offset, int limit){
		StringBuffer where = new StringBuffer("from orderinfo where deleted=0");
		List<Object> params = new ArrayList<Object>();
		if(order.getStr("createUser") != null && !"".equals(order.getStr("createUser").trim())){
			where.append(" and createUser=?");
			params.add(order.getStr("createUser").trim());
		}
		if(order.getStr("shopCode") != null && !"".equals(order.getStr("shopCode").trim())){
			where.append(" and shopCode=?");
			params.add(order.getStr("shopCode").trim());
		}
		where.append(" order by createTime desc");	
		Page<OrderInfo> orders = null;
		if(params.size() == 0){
			orders = OrderInfo.dao.paginate(offset, limit, "select *", where.toString());
		}else{
			orders = OrderInfo.dao.paginate(offset, limit, "select *", where.toString(), params.toArray());
		}
		return orders.getList();
	}
	
	@Before(Tx.class)
	public static final boolean create(OrderInfo order){
		ModelUtil.generateUUID(order);
		order.set("createTime", new Date());
		for(OrderInfoDetail detail:order.getDetails()){
			ModelUtil.generateUUID(detail);
			detail.setOrderId(order.getStr("uid"));
			detail.save();
		}
		return order.save();
	}
	
	@Before(Tx.class)
	public static final boolean delete(String uuid){
		OrderInfo order = OrderInfo.dao.findFirst(
				"select * from orderInfo where deleted=0 and uuid=?", uuid);
		if(order != null){
			List<OrderInfoDetail>details = OrderInfoDetail.dao.find(
					"select * from product where deleted=0 and orderId=?", uuid);
			for(OrderInfoDetail detal: details){
				ModelUtil.softDelete(detal);
			}
			return ModelUtil.softDelete(order);
		}
		return false;
	}
	
	public static final boolean changeStatus(OrderInfo order){
		OrderInfo model = OrderInfo.dao.findFirst(
				"select * from orderInfo where deleted=0 and uuid=?", order.getStr("uid"));
		if(model != null){
			model.set("status", order.getStatus());
			return model.save();
		}
		return false;
	}
}
