package com.sdw.soft.common.vo;
/**
 * @author Sonicery_D
 * @date 2014年10月29日
 * @version 1.0.0
 * @description 关注用户信息
 */
public class WechatUser {

	private String userId;
	
	private String openId;
	
	private String createTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
