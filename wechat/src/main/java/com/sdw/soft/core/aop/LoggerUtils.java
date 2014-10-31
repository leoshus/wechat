package com.sdw.soft.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Sonicery_D
 * @date 2014年10月31日
 * @version 1.0.0
 * @description
 */
@Component
@Aspect
public class LoggerUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggerUtils.class);
	
	@Pointcut(value="execution(* com.tkyl..*Service.*(..))")
	public void printLog(){
		
	}
	@Before(value="printLog()")
	public void beforeExecute(JoinPoint jp){
		String methodName = jp.getSignature().getName();
		String className = jp.getTarget().getClass().getName();
		StringBuffer sb = new StringBuffer("类["+className+"]-方法["+methodName+"]开始执行------");
		int i = 1;
		for(Object obj : jp.getArgs()){
			if(obj instanceof String){
				sb.append("arg"+i+"="+obj.toString()+",");
			}
			i++;
		}
		logger.info("AOP 日志拦截---{}",sb.toString());
	}
}
