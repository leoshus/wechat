package com.sdw.soft.common.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.sdw.soft.common.dao.ICommonDao;
import com.sdw.soft.common.vo.WechatUser;
import com.sdw.soft.core.utils.DateUtils;
import com.sdw.soft.core.utils.IdGenerator;

/**
 * @author Sonicery_D
 * @date 2014年10月29日
 * @version 1.0.0
 * @description  公共Dao
 */
@Repository
public class CommonDao implements ICommonDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.sdw.soft.common.dao.impl.ICommonDao#saveWechatUser(com.sdw.soft.common.vo.WechatUser)
	 */
	@Override
	public boolean saveWechatUser(WechatUser wechatUser){
		boolean flag = false;
		int count = this.jdbcTemplate.update("insert into wechat_user (user_id,openid,createtime) values(?,?,?)",
				new Object[]{IdGenerator.generatorId(),wechatUser.getOpenId(),DateUtils.getCurrentTimeString()});
		if(count > 0){
			flag = true;
		}
		return flag;
	}
}
