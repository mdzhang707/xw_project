package com.manage.model;

import java.util.Date;
import com.jfinal.plugin.activerecord.Model;

/**
 * 活动信息
 *
 * @author mdzhang
 */
public class SpecialActivity extends Model<SpecialActivity>{
	/**
	 * 主键
	 */
	private int iid;
	/**
	 * 唯一标识
	 */
	private String uuid;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 活动开始时间
	 */
	private Date startTime;
	/**
	 * 活动结束时间
	 */
	private Date endTime;
	/**
	 * 描述信息
	 */
	private String notes;
	/**
	 * 状态
	 */
	private int status;
	/**
	 * 预计结束时间
	 */
	private Date tagTime;
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

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getTagTime() {
		return tagTime;
	}

	public void setTagTime(Date tagTime) {
		this.tagTime = tagTime;
	}
	
	public static final SpecialActivity dao = new SpecialActivity();
}
