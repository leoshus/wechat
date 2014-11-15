package com.sdw.soft.business.netmall.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sonicery_D
 * @date 2014年11月15日
 * @version 1.0.0
 * @description
 **/
@Controller
@RequestMapping("/netmall")
public class NetmallController {

	private static final Logger logger = LoggerFactory.getLogger(NetmallController.class);
	
	@RequestMapping(value="/index")
	public String toIndex(){
		
		return "/netmall/index";
	}
	
	@RequestMapping(value="/weapp/{appName}")
	public String weapp(@PathVariable(value="appName")String appName) throws Exception{
		if(StringUtils.isBlank(appName)){
			logger.info("参数appName 为空!");
			throw new Exception("参数appName 为空!");
		}
		return "/netmall/"+appName;
	}
}
