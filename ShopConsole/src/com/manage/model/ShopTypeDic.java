package com.manage.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;

/**
 * 记录商品的类型内容
 * @author Administrator
 *
 */
public class ShopTypeDic extends Model<ShopTypeDic> {
	private int iid;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 标识码
	 */
	private String uuid;
	/**
	 * 展示级别
	 */
	private int leve;
	/**
	 * 父级
	 */
	private String parentUuid;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public int getLeve() {
		return leve;
	}
	public void setLeve(int leve) {
		this.leve = leve;
	}
	public String getParentUuid() {
		return parentUuid;
	}
	public void setParentCode(String parentUuid) {
		this.parentUuid = parentUuid;
	}
	
	public static final ShopTypeDic dao = new ShopTypeDic();
}
