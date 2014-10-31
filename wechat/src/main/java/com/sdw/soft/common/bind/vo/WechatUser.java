package com.sdw.soft.common.bind.vo;

import java.io.Serializable;

import com.sdw.soft.core.entity.AncestorEntity;

/**
 * @author Sonicery_D
 * @date 2014年10月31日
 * @version 1.0.0
 * @description 绑定用户实体
 */
public class WechatUser extends AncestorEntity implements Serializable{

	private static final long serialVersionUID = 7711022557907014896L;

	private String openId;
	private String wechatName;//姓名
	private String wechatCardType;//证件类型
	private String wechatCardNo;//证件号
	private String wechatSex;
	private String wechatBirth;
	private String wechatPhone;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getWechatName() {
		return wechatName;
	}
	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}
	public String getWechatCardType() {
		return wechatCardType;
	}
	public void setWechatCardType(String wechatCardType) {
		this.wechatCardType = wechatCardType;
	}
	public String getWechatCardNo() {
		return wechatCardNo;
	}
	public void setWechatCardNo(String wechatCardNo) {
		this.wechatCardNo = wechatCardNo;
	}
	public String getWechatSex() {
		return wechatSex;
	}
	public void setWechatSex(String wechatSex) {
		this.wechatSex = wechatSex;
	}
	public String getWechatBirth() {
		return wechatBirth;
	}
	public void setWechatBirth(String wechatBirth) {
		this.wechatBirth = wechatBirth;
	}
	public String getWechatPhone() {
		return wechatPhone;
	}
	public void setWechatPhone(String wechatPhone) {
		this.wechatPhone = wechatPhone;
	}
	
}
