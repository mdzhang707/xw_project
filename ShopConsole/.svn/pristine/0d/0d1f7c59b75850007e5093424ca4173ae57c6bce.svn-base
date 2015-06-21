package com.manage.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;

/**
 * 广告信息，展示在页面的广告信息
 * 
 * @author mdzhang
 */
public class AdInfo extends Model<AdInfo>{
	
	/**
	 * 主键标识
	 */
	private int iid;
	/**
	 * 图片地址，文件路径
	 */
	private String image;
	/**
	 * 点击图片跳转链接
	 */
	private String url;
	/**
	 * 排序方式，展示在页面的数顺序
	 */
	private int orders;
	/**
	 * 图片展示位置，需要根据前端来设定
	 */
	private String position;
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

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public int getOrders() {
		return orders;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPosition() {
		return position;
	}
	
	public static final AdInfo dao = new AdInfo();
}
