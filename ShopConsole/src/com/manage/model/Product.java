package com.manage.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;

/**
 * 商品信息
 * 
 * @author mdzhang
 *
 */
public class Product extends Model<Product>{
	/**
	 * 数据库标识
	 */
	private int iid;
	/**
	 * 主键标识
	 */
	private String uuid;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 类型
	 */
	private int type;
	/**
	 * 存储商店的UID
	 */
	private String shop;
	/**
	 * 显示价格,即显示在页面中的原价
	 */
	private float showPrice;
	/**
	 * 价格，即现价，买方要支付的价格
	 */
	private float salesPrice;
	/**
	 * 原始价格，商场的售价
	 */
	private float origPrice;
	/**
	 * 图片信息，存储图片的原始位置
	 */
	private String image;
	/**
	 * 总销量
	 */
	private int salesVolume;
	/**
	 * 商品的属性，Map字符串
	 */
	private String attributes;
	/**
	 * 品牌
	 */
	private String singlTag;	
	/**
	 * 库存
	 */
	private int Count;
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
	
	public int getCount() {
		return Count;
	}

	public void setCount(int count) {
		Count = count;
	}

	public void setIid(int iid) {
		this.iid = iid;
	}

	public int getIid() {
		return iid;
	}

	public void setUUID(String uuid) {
		this.uuid = uuid;
	}

	public String getUUID() {
		return uuid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getShop() {
		return shop;
	}

	public void setShowPrice(float showPrice) {
		this.showPrice = showPrice;
	}

	public float getShowPrice() {
		return showPrice;
	}

	public void setSalesPrice(float salesPrice) {
		this.salesPrice = salesPrice;
	}

	public float getSalesPrice() {
		return salesPrice;
	}

	public void setOrigPrice(float origPrice) {
		this.origPrice = origPrice;
	}

	public float getOrigPrice() {
		return origPrice;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setSalesVolume(int salesVolume) {
		this.salesVolume = salesVolume;
	}

	public int getSalesVolume() {
		return salesVolume;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setSinglTag(String singlTag) {
		this.singlTag = singlTag;
	}

	public String getSinglTag() {
		return singlTag;
	}
	
	public static final Product dao = new Product();
}
