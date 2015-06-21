package com.manage.model;

import java.util.Date;
import java.util.Map;

import com.jfinal.plugin.activerecord.Model;

/**
 * 商户信息
 * 
 * @author mdzhang
 *
 */
public class ShopInfo extends Model<ShopInfo>{
	/**
	 * 数据库标识主键
	 */
	private int iid;
	/**
	 * 唯一主键
	 */
	private String uuid;
	/**
	 * 商户名称
	 */
	private String name;
	/**
	 * 商户类型
	 */
	private int type;
	/**
	 * 所属省
	 */
	private String firstCode;
	/**
	 * 所属市
	 */
	private String secCode;
	/**
	 * 经度
	 */
	private float longitude;
	/**
	 * 纬度
	 */
	private float latitude;
	/**
	 * 微信号(用于登录)
	 */
	private String wechart;
	/**
	 * 登录密码(系统登录密码，非微信密码)
	 */
	private String password;
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
		this.deleted = getInt("deleted");
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getFirstCode() {
		return firstCode;
	}

	public void setFirstCode(String firstCode) {
		this.firstCode = firstCode;
	}

	public String getSecCode() {
		return secCode;
	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public String getWechart() {
		return wechart;
	}

	public void setWechart(String wechart) {
		this.wechart = wechart;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static final ShopInfo dao= new ShopInfo();
	
	public Map<String, Object> getAttrs(){
	    return super.getAttrs();
	}
}
