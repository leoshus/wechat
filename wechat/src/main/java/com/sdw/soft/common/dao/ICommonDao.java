package com.sdw.soft.common.dao;

import com.sdw.soft.common.vo.WechatUserSample;

/**
 * @author Sonicery_D
 * @date 2014年10月29日
 * @version 1.0.0
 * @description
 */
public interface ICommonDao {

	/**
	 * 保存订阅的用户信息
	 * @param wechatUser
	 * @return
	 */
	public abstract boolean saveWechatUser(WechatUserSample wechatUser);

}