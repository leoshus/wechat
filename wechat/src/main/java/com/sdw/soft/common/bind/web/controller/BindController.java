package com.sdw.soft.common.bind.web.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sdw.soft.common.bind.service.IBindService;
import com.sdw.soft.common.bind.vo.WechatUser;
import com.sdw.soft.core.entity.ActionResult;

/**
 * @author Sonicery_D
 * @date 2014年10月31日
 * @version 1.0.0
 * @description 用户绑定
 */
@Controller
@RequestMapping(value="/bind")
public class BindController {
	
	private static Logger logger = LoggerFactory.getLogger(BindController.class);
	
	@Autowired
	private IBindService bindService;
	
	/**
	 * 用户绑定页面跳转
	 * @return
	 */
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public ModelAndView doBind(@RequestParam(value="openId",required=true)String openId,HttpSession session){
		if(StringUtils.isBlank(openId)){
			logger.info("-------------绑定时openId为空!----------");
			throw new RuntimeException("绑定时openId 不能为空!");
		}
		session.setAttribute("openId", openId);
		return new ModelAndView("/bind/input","openId",openId);
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public @ResponseBody ActionResult createBind(WechatUser user){
		ActionResult actionResult = new ActionResult();
		WechatUser wechatUser = bindService.fetchBindUser(user.getOpenId());
		if(wechatUser != null){
			actionResult.setMessage("您已经绑定成功,无需重复绑定！");
			return actionResult;
		}
		if(bindService.saveBindUser(user)){
			actionResult.setSuccess(true);
			actionResult.setMessage("绑定成功！");
		}
		return actionResult;
	}
	
	@RequestMapping(value="/success")
	public ModelAndView bindSuccess(){
		
		return new ModelAndView("/bind/success");
	}
	
}
