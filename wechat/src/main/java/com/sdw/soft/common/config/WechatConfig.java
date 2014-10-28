package com.sdw.soft.common.config;
/**
 * @author Sonicery_D
 * @date 2014年10月28日
 * @version 1.0.0
 * @description 微信号 全局配置
 **/
public class WechatConfig {

	// 微信号
		private String weixinId;

		private String appId;

		private String appSecret;

		private String token;

		private String baseAccessTokenUrl;

		private String baseUserinfoUrl;

		private String baseOAuthUrl;

		private String baseOAuthTokenUrl;

		public String getWeixinId() {
			return weixinId;
		}

		public void setWeixinId(String weixinId) {
			this.weixinId = weixinId;
		}

		public String getAppId() {
			return appId;
		}

		public void setAppId(String appId) {
			this.appId = appId;
		}

		public String getAppSecret() {
			return appSecret;
		}

		public void setAppSecret(String appSecret) {
			this.appSecret = appSecret;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getBaseAccessTokenUrl() {
			return baseAccessTokenUrl;
		}

		public void setBaseAccessTokenUrl(String baseAccessTokenUrl) {
			this.baseAccessTokenUrl = baseAccessTokenUrl;
		}

		public String getBaseUserinfoUrl() {
			return baseUserinfoUrl;
		}

		public void setBaseUserinfoUrl(String baseUserinfoUrl) {
			this.baseUserinfoUrl = baseUserinfoUrl;
		}

		public String getBaseOAuthUrl() {
			return baseOAuthUrl;
		}

		public void setBaseOAuthUrl(String baseOAuthUrl) {
			this.baseOAuthUrl = baseOAuthUrl;
		}

		public String getBaseOAuthTokenUrl() {
			return baseOAuthTokenUrl;
		}

		public void setBaseOAuthTokenUrl(String baseOAuthTokenUrl) {
			this.baseOAuthTokenUrl = baseOAuthTokenUrl;
		}
}
