package com.sdw.soft.core.utils;


/**
 * @author Sonicery_D
 * @date 2014年11月16日
 * @version 1.0.0
 * @description 随机数生成器
 **/
public class RandomUtils {

	public static String getRandom(){
		String random = String.valueOf(Math.random());
		String temp = random.substring(random.indexOf(".")+1,random.indexOf(".")+5);
		
		return temp;
	}
}
