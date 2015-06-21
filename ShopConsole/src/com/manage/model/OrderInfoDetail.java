package com.manage.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;

/**
 * 订单记录表
 * @author Administrator
 *
 */
public class OrderInfoDetail extends Model<OrderInfoDetail> {
	private int iid;
	private String uuid;
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 商品号
	 */
	private String shopId;
	/**
	 * 数量
	 */
	private int count;
	/**
	 * 单价
	 */
	private float price;
	/**
	 * 总价
	 */
	private float total;
	/**
	 * 属性
	 */
	private String attributes;
	/**
	 * 是否删除
	 */
	private int deleted;
	/**
	 * 删除时间
	 */
	private Date deleted_at;

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Date getDeleted_at() {
		return deleted_at;
	}

	public void setDeleted_at(Date deleted_at) {
		this.deleted_at = deleted_at;
	}
	
	public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid = iid;
	}
	public String getUUID() {
		return uuid;
	}
	public void setUUID(String uuid) {
		this.uuid = uuid;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	
	public static final OrderInfoDetail dao = new OrderInfoDetail();
}
