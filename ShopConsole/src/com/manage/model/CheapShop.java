package com.manage.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;

/**
 * 活动商品信息
 *
 * @author mdzhang
 */

public class CheapShop extends Model<CheapShop>{
	/**
	 * 主键标识
	 */
	private int iid;
	/**
	 * 商品ID，存储UID
	 */
	private String shopId;
	/**
	 * 商户ID，存储UID
	 */
	private String storeId;
	/**
	 * 活动ID，存储活动的UID
	 */
	private String activeId;
	/**
	 * 商店名称
	 */
	private String storeName;
	/**
	 * 商品名称
	 */
	private String shopName;
	/**
	 * 活动名称
	 */
	private String activeName;
	/**
	 * 活动开始时间
	 */
	private Date sellTime;
	/**
	 * 活动结束时间
	 */
	private Date overTime;
	/**
	 * 显示的销售价格
	 */
	private float showPrice;
	/**
	 * 商家的真实价格
	 */
	private float realPrice;
	/**
	 * 显示的原始价格
	 */
	private float sellPrice;
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

	public void setIid(int iid) {
		this.iid = iid;
	}

	public int getIid() {
		return iid;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setActiveId(String activeId) {
		this.activeId = activeId;
	}

	public String getActiveId() {
		return activeId;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}

	public String getActiveName() {
		return activeName;
	}

	public void setSellTime(Date sellTime) {
		this.sellTime = sellTime;
	}

	public Date getSellTime() {
		return sellTime;
	}

	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

	public Date getOverTime() {
		return overTime;
	}

	public void setShowPrice(float showPrice) {
		this.showPrice = showPrice;
	}

	public float getShowPrice() {
		return showPrice;
	}

	public void setRealPrice(float realPrice) {
		this.realPrice = realPrice;
	}

	public float getRealPrice() {
		return realPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public float getSellPrice() {
		return sellPrice;
	}
	public static final CheapShop dao = new CheapShop();
}
