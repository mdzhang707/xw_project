package com.manage.model;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * 订单信息
 * 
 * @author Administrator
 *
 */
public class OrderInfo extends Model<OrderInfo> {
	private int iid;
	private String uuid;
	/**
	 * 创建人
	 */
	private String createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 商家编号
	 */
	private String shopCode;
	/**
	 * 总计
	 */
	private float total;
	/**
	 * 订单状态
	 */
	private int status;
	/**
	 * 备注
	 */
	private String notes;
	/**
	 * 收货地址
	 */
	private String address;
	/**
	 * 收货人姓名
	 */
	private String userName;
	/**
	 * 联系方式
	 */
	private String connection;	
	/**
	 * 是否删除
	 */
	private int deleted;
	/**
	 * 删除时间
	 */
	private Date deleted_at;
	
	private List<OrderInfoDetail> details;
	
	public List<OrderInfoDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderInfoDetail> details) {
		this.details = details;
	}

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

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public static final OrderInfo dao = new OrderInfo();
}
