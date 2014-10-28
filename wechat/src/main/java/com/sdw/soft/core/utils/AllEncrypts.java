package com.sdw.soft.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * @author Sonicery_D
 * @date 2014年10月28日
 * @version 1.0.0
 * 
 * 加密方法 工具集
 */
public class AllEncrypts {

	private static final String ALGORITHEM_SHA_1 = "SHA-1";
	/**
	 * SHA-1加密算法
	 * @param convertStr
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String SHAsum(byte[] convertStr) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance(ALGORITHEM_SHA_1);
		return byteArray2Hex(md.digest(convertStr));
	}
	
	private static String byteArray2Hex(final byte[] byteArray){
		Formatter formatter = new Formatter();
		for(byte b : byteArray){
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
}
