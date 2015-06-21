package com.manage.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;

/**
 * 返利设置
 * @author Administrator
 *
 */
public class ProfitReturnSetting extends Model<ProfitReturnSetting> {
	private int iid;
	/**
	 * 一级用户返利百分率
	 */
	private int parent1;
	/**
	 * 二级用户返利百分率
	 */
	private int parent2;
	/**
	 * 三级用户返利百分率
	 */
	private int parent3;
	/**
	 * 四级用户返利百分率
	 */
	private int parent4;
	/**
	 * 五级用户返利百分率
	 */
	private int parent5;
	/**
	 * 六级用户返利百分率
	 */
	private int parent6;
	/**
	 * 七级用户返利百分率
	 */
	private int parent7;
	/**
	 * 八级用户返利百分率
	 */
	private int parent8;
	/**
	 * 九级用户返利百分率
	 */
	private int parent9;
	/**
	 * 十级用户返利百分率
	 */
	private int parent10;
	/**
	 * 未购买折算比例
	 */
	private int convertRate;
	/**
	 * 延迟期限类型
	 */
	private int delayType;
	/**
	 * 延迟期限
	 */
	private int delayDate;
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
	public int getParent1() {
		return parent1;
	}
	public void setParent1(int parent1) {
		this.parent1 = parent1;
	}
	public int getParent2() {
		return parent2;
	}
	public void setParent2(int parent2) {
		this.parent2 = parent2;
	}
	public int getParent3() {
		return parent3;
	}
	public void setParent3(int parent3) {
		this.parent3 = parent3;
	}
	public int getParent4() {
		return parent4;
	}
	public void setParent4(int parent4) {
		this.parent4 = parent4;
	}
	public int getParent5() {
		return parent5;
	}
	public void setParent5(int parent5) {
		this.parent5 = parent5;
	}
	public int getParent6() {
		return parent6;
	}
	public void setParent6(int parent6) {
		this.parent6 = parent6;
	}
	public int getParent7() {
		return parent7;
	}
	public void setParent7(int parent7) {
		this.parent7 = parent7;
	}
	public int getParent8() {
		return parent8;
	}
	public void setParent8(int parent8) {
		this.parent8 = parent8;
	}
	public int getParent9() {
		return parent9;
	}
	public void setParent9(int parent9) {
		this.parent9 = parent9;
	}
	public int getParent10() {
		return parent10;
	}
	public void setParent10(int parent10) {
		this.parent10 = parent10;
	}
	public int getConvertRate() {
		return convertRate;
	}
	public void setConvertRate(int convertRate) {
		this.convertRate = convertRate;
	}
	public int getDelayType() {
		return delayType;
	}
	public void setDelayType(int delayType) {
		this.delayType = delayType;
	}
	public int getDelayDate() {
		return delayDate;
	}
	public void setDelayDate(int delayDate) {
		this.delayDate = delayDate;
	}
	
	public static final ProfitReturnSetting dao = new ProfitReturnSetting();
}
