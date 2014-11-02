package com.sdw.soft.weixin.accesstoken.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.sdw.soft.core.dao.AncestorDao;
import com.sdw.soft.weixin.accesstoken.dao.IAccessTokenDao;
import com.sdw.soft.weixin.accesstoken.vo.AccessToken;

/**
 * @author Sonicery_D
 * @date 2014年11月2日
 * @version 1.0.0
 * @description
 **/
@Service
public class AccessTokenDao extends AncestorDao implements IAccessTokenDao {

	private static final Logger logger = LoggerFactory.getLogger(AccessTokenDao.class);
	
	private static final String FETCH_ACCESS_TOKEN = "select service_provider,token,create_time from wechat_access_token";
	
	/* (non-Javadoc)
	 * @see com.sdw.soft.weixin.accesstoken.dao.IAccessTokenDao#fetchAccessToken()
	 */
	@Override
	public List<AccessToken> fetchAccessToken(){
		List<AccessToken> results = null;
		try {
			results = this.jdbcTemplate.query(FETCH_ACCESS_TOKEN, new RowMapper<AccessToken>(){

				@Override
				public AccessToken mapRow(ResultSet rs, int index)
						throws SQLException {
					AccessToken token = new AccessToken();
					token.setServiceProvider(rs.getString("service_provider"));
					token.setToken(rs.getString("token"));
					token.setCreatetime(rs.getString("create_time"));
					return token;
				}
				
			});
		} catch (DataAccessException e) {
			logger.error("查询接入系统token信息出错！");
			e.printStackTrace();
			logger.info(e.getMessage(),e);
		}
		
		return results;
	}
}
