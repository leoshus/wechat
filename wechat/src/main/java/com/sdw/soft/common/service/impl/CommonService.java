package com.sdw.soft.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdw.soft.common.dao.ICommonDao;
import com.sdw.soft.common.service.ICommonService;
import com.sdw.soft.common.vo.WechatUser;

/**
 * @author Sonicery_D
 * @date 2014年10月29日
 * @version 1.0.0
 * @description
 */
@Service
public class CommonService implements ICommonService {

	@Autowired
	private ICommonDao commonDao;
	
	/* (non-Javadoc)
	 * @see com.sdw.soft.common.service.impl.ICommonService#saveWechatUser(com.sdw.soft.common.vo.WechatUser)
	 */
	@Override
	public boolean saveWechatUser(WechatUser wechatUser){
		return commonDao.saveWechatUser(wechatUser);
	}
}
