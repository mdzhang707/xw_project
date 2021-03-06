package com.manage.model;

import java.util.Date;
import com.jfinal.plugin.activerecord.Model;

/**
 * 用于记录执行销售到帐
 * @author Administrator
 *
 */
public class SalesPayTask extends Model<SalesPayTask> {
	private int iid;
	private String uuid;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 待执行时间
	 */
	private Date operateTime;
	/**
	 * 收款商户
	 */
	private String shopId;
	/**
	 * 状态
	 */
	private int status;
	/**
	 * 关联订单号
	 */
	private String orderId;
	/**
	 * 总额
	 */
	private float total;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	
	public static final SalesPayTask dao = new SalesPayTask();	
}
