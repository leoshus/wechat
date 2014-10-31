package com.sdw.soft.common.bind.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdw.soft.common.bind.dao.impl.IBindDao;
import com.sdw.soft.common.bind.service.impl.IBindService;
import com.sdw.soft.common.bind.vo.WechatUser;
import com.sdw.soft.core.utils.DateUtils;
import com.sdw.soft.core.utils.IdGenerator;

/**
 * @author Sonicery_D
 * @date 2014年10月31日
 * @version 1.0.0
 * @description
 */
@Service
public class BindService implements IBindService {
	
	@Autowired
	private IBindDao bindDao;
	
	/* (non-Javadoc)
	 * @see com.sdw.soft.common.bind.service.IBindService#saveBindUser(com.sdw.soft.common.bind.vo.WechatUser)
	 */
	@Override
	public boolean saveBindUser(WechatUser user){
		user.setId(IdGenerator.generatorId());
		String cardType = user.getWechatCardType();
		String cardNo = user.getWechatCardNo();
		if("0".equals(cardType)){
			user.setWechatBirth(cardNo.substring(6,10)+"-"+cardNo.substring(10,12)+"-"+cardNo.substring(12,14));//生日
			user.setWechatSex(((Integer.parseInt(cardNo.substring(16,17))%2) == 0) ? "1" : "0");//性别
		}
		user.setCreateDate(DateUtils.getCurrentTimeString());
		user.setUpdateDate(DateUtils.getCurrentTimeString());
		return bindDao.saveBindUser(user);
	}

	@Override
	public WechatUser fetchBindUser(String openId) {
		return bindDao.fetchBindUser(openId);
	}
}
