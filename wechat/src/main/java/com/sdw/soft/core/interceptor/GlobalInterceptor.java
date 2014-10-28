package com.sdw.soft.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Sonicery_D
 * @date 2014年10月28日
 * @version 1.0.0
 */
public class GlobalInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger = LoggerFactory.getLogger(GlobalInterceptor.class);
	
	private static final String CHARSET = "UTF-8";
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		request.setCharacterEncoding(CHARSET);
		response.setCharacterEncoding(CHARSET);
		response.setContentType("text/html;charset=UTF-8");
		
		String requestUrl = request.getRequestURI();
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String token = null;
		String method = null;
		if(StringUtils.isBlank(signature) || StringUtils.isBlank(timestamp) || StringUtils.isBlank(nonce)){
			logger.info("不是来自微信的请求！:{}",requestUrl);
			token = request.getParameter("token");
			method = request.getMethod();
			if(method.equalsIgnoreCase("get")){
				
			}else if(method.equalsIgnoreCase("post")){
				logger.info("post 请求放过!");
			}
		}else{
			logger.info("来自微信的请求！:{}",requestUrl);
			//进行Token校验
			
		}
		logger.info("--------------------preHandle---------------");
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("--------------------postHandle--------------");
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("--------------------afterCompletion----------");
		super.afterCompletion(request, response, handler, ex);
	}
	
}
