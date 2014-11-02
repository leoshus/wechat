package com.sdw.soft.weixin.accesstoken.vo;
/**
 * @author Sonicery_D
 * @date 2014年11月2日
 * @version 1.0.0
 * @description
 **/
public class AccessToken {

	private String serviceProvider;
	
	private String token;
	
	private String createtime;

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
}
