package com.sdw.soft.common.bind.dao.impl;

import com.sdw.soft.common.bind.vo.WechatUser;

/**
 * @author Sonicery_D
 * @date 2014年10月31日
 * @version 1.0.0
 * @description
 */
public interface IBindDao {

	/**
	 * 保存用户信息
	 * @param user
	 * @return
	 */
	public abstract boolean saveBindUser(WechatUser user);
	/**
	 * 查询绑定用户
	 * @param openId
	 * @return
	 */
	public abstract WechatUser fetchBindUser(String openId);

}