package com.sdw.soft.core.utils;

import java.util.Arrays;

/**
 * @author Sonicery_D
 * @date 2014年10月28日
 * @version 1.0.0
 * 微信校验
 */
public class WechatAuthentication {

	private static final String TOKEN = "sydlovemic";
	
	/**
	 * 微信校验
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean authenticate(String signature,String timestamp,String nonce){
		
		String[] array = {TOKEN,timestamp,nonce};
		Arrays.sort(array);//按字母排序
		String result = "";
		//将排序后的三个字符串 拼成一个字符串
		for(String str : array){
			result += str;
		}
		String ret = ""; 
		try {
			//对拼装结果字符串进行SHA-1加密
			ret = AllEncrypts.SHAsum(result.getBytes());
			if(ret.equalsIgnoreCase(signature)){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static void main(String[] args) {
		String signature = "ff6b3bb9fc2e409b9d3e3f2c80bbde7f4c9a784c";
		String timestamp = "1414509862";
		String nonce = "1295345138";
		System.out.println(authenticate(signature, timestamp, nonce));
	}
}
