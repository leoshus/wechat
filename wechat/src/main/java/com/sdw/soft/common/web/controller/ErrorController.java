package com.sdw.soft.common.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Sonicery_D
 * @date 2014年10月31日
 * @version 1.0.0
 * @description
 */
@Controller
@RequestMapping(value="/error")
public class ErrorController {

	@RequestMapping(value="/error")
	public ModelAndView forwardError(){
		return new ModelAndView("/common/error");
	}
}
