package com.sdw.soft.common.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sonicery_D
 * @date 2014年10月28日
 * @version 1.0.0
 */
@Controller
public class LoadingController {

	@RequestMapping(value="/index")
	private String forwardIndex(){
		return "/common/index";
	}
}
