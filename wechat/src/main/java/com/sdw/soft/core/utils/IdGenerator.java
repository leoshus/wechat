package com.sdw.soft.core.utils;

import java.util.UUID;

/**
 * @author Sonicery_D
 * @date 2014年10月29日
 * @version 1.0.0
 * @description 主键生成器
 */
public class IdGenerator {
	/**
	 * 生成UUID去"-"后32位串
	 * @return
	 */
	public static String generatorId(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(generatorId());
	}
}
