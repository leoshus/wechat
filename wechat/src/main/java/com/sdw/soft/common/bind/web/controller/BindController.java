package com.sdw.soft.common.bind.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Sonicery_D
 * @date 2014年10月31日
 * @version 1.0.0
 * @description 用户绑定
 */
@Controller
@RequestMapping(value="/bind")
public class BindController {
	/**
	 * 用户绑定页面跳转
	 * @return
	 */
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public ModelAndView doBind(){
		
		return new ModelAndView("/bind/input", "openId", "123456789");
	}
}
