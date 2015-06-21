package com.manage.service;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.manage.fileUtil.SqlHelp;
import com.manage.model.ModelUtil;
import com.manage.model.Product;

/**
 * 商品信息管理
 * @author mdzhang
 *
 */
public class ProductManage {
	public List<Product> getProduct(Product product){
		
		return new ArrayList<Product>();
	}
	
	public static final List<Product> list(Product product, int offset, int limit){
		StringBuffer where = new StringBuffer("from product where deleted=0");
		List<Object> params = new ArrayList<Object>();
		if(product.getStr("shop") != null && !"".equals(product.getStr("shop").trim())){
			where.append(" and shop=?");
			params.add(product.getStr("shop").trim());
		}
		if(product.getStr("name") != null && !"".equals(product.getStr("name").trim())	){
			where.append(" and name like ?");
			params.add("%"+product.getStr("name").trim()+"%");
		}
		where.append(" order by iid desc");		
		Page<Product> products = null;
		if(params.size() == 0){
			products = Product.dao.paginate(offset, limit, "select *", where.toString());
		}else{
			products = Product.dao.paginate(offset, limit, "select *", where.toString(), params.toArray());
		}
		return products.getList();
	}
	
	public static String sqlAppend(Product product){
		StringBuilder sql=new StringBuilder("SELECT * FROM Product WHERE IID IS NOT NULL ");
		if(null == product){
			return sql.toString(); 
		}
		if(product.getIid()>=0){
			sql.append("and iid=").append(product.getIid()).append(" ");
		}
		if(!SqlHelp.IsNullOrBlack(product.getName())){
			sql.append("and name like '%").append(product.getName()).append("% ");
		}
		if(!SqlHelp.IsNullOrBlack(product.getShop())){
			sql.append("and shop=").append(product.getShop()).append(" ");
		}
		return sql.toString();
	}
	
	public static final boolean create(Product product){
		ModelUtil.generateUUID(product);
		return product.save();
	}
	
	public static final boolean delete(String uuid){
		Product model = Product.dao.findFirst("select * from product where deleted =0 and uuid=?", uuid);
		if(model != null){
			return ModelUtil.softDelete(model);
		}
		return false;
	}
	
	public static final boolean edit(Product product){
		Product model = Product.dao.findFirst("select * from product where deleted =0 and uuid=?", product.getStr("uid"));
		if(model != null){
			product.set("iid", model.getInt("iid"));
			return product.update();
		}
		return false;
	}
	
	public static final Product getByUuid(String uuid){
		return Product.dao.findFirst("select * from Product where uuid=?",uuid);
	}
}
