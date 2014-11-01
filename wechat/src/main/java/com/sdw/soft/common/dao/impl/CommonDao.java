package com.sdw.soft.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.sdw.soft.common.dao.ICommonDao;
import com.sdw.soft.common.vo.WechatUserSample;
import com.sdw.soft.core.dao.AncestorDao;
import com.sdw.soft.core.utils.DateUtils;
import com.sdw.soft.core.utils.IdGenerator;

/**
 * @author Sonicery_D
 * @date 2014年10月29日
 * @version 1.0.0
 * @description  公共Dao
 */
@Repository
public class CommonDao extends AncestorDao implements ICommonDao{

	/* (non-Javadoc)
	 * @see com.sdw.soft.common.dao.impl.ICommonDao#saveWechatUser(com.sdw.soft.common.vo.WechatUser)
	 */
	@Override
	public boolean saveWechatUser(WechatUserSample wechatUser){
		boolean flag = false;
		int count = this.jdbcTemplate.update("insert into wechat_user (user_id,openid,createtime) values(?,?,?)",
				new Object[]{IdGenerator.generatorId(),wechatUser.getOpenId(),DateUtils.getCurrentTimeString()});
		if(count > 0){
			flag = true;
		}
		return flag;
	}
}
