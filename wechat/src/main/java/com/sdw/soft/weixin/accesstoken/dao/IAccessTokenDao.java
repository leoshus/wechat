package com.sdw.soft.weixin.accesstoken.dao;

import java.util.List;

import com.sdw.soft.weixin.accesstoken.vo.AccessToken;

/**
 * @author Sonicery_D
 * @date 2014年11月2日
 * @version 1.0.0
 * @description
 **/
public interface IAccessTokenDao {

	public abstract List<AccessToken> fetchAccessToken();

}