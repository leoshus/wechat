package com.sdw.soft.common.bind.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sdw.soft.common.bind.dao.impl.IBindDao;
import com.sdw.soft.common.bind.vo.WechatUser;
import com.sdw.soft.core.dao.AncestorDao;

/**
 * @author Sonicery_D
 * @date 2014年10月31日
 * @version 1.0.0
 * @description
 */
@Repository
public class BindDao extends AncestorDao implements IBindDao{

	private static final Logger logger = LoggerFactory.getLogger(BindDao.class);
	
	private static final String SAVE_BIND_USER = "insert into wechat_bind_user (id,openId,wechatName,wechatCardType,wechatCardNo,wechatSex,wechatBirth,wechatPhone,createDate,updateDate) values (?,?,?,?,?,?,?,?,?,?) ";
	private static final String FETCH_BIND_USER_BY_OPENDID = "select * from wechat_bind_user where openId = ?";
	/* (non-Javadoc)
	 * @see com.sdw.soft.common.bind.dao.IBindDao#saveBindDao(com.sdw.soft.common.bind.vo.WechatUser)
	 */
	@Override
	public boolean saveBindUser(WechatUser user){
		logger.info(SAVE_BIND_USER);
		boolean flag = false;
		try {
			int count = this.jdbcTemplate.update(SAVE_BIND_USER, 
					new Object[]{user.getId(),user.getOpenId(),user.getWechatName(),user.getWechatCardType(),user.getWechatCardNo(),user.getWechatSex(),user.getWechatBirth(),user.getWechatPhone(),user.getCreateDate(),user.getUpdateDate()});
			if(count > 0){
				flag = true;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.info(e.getMessage(),e);
		}
		
		return flag;
	}

	@Override
	public WechatUser fetchBindUser(String openId) {
		WechatUser user = null;
		try {
			user = this.jdbcTemplate.queryForObject(FETCH_BIND_USER_BY_OPENDID, new RowMapper<WechatUser>(){

				@Override
				public WechatUser mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					WechatUser user = new WechatUser();
					user.setId(rs.getString("id"));
					user.setWechatName(rs.getString("wechatName"));
					user.setWechatCardType(rs.getString("wechatCardType"));
					user.setWechatCardNo(rs.getString("wechatCardNo"));
					user.setWechatSex(rs.getString("wechatSex"));
					user.setWechatBirth(rs.getString("wechatBirth"));
					user.setWechatPhone(rs.getString("wechatPhone"));
					return user;
				}
				
			},openId);
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.info(e.getMessage(),e);
		}
		return user;
	}
}
