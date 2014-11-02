package com.sdw.soft.common.httpclient.service;

/**
 * @author Sonicery_D
 * @date 2014年11月2日
 * @version 1.0.0
 * @description
 **/
public interface IHttpClientService {

	/**
	 * 发送GET请求
	 * @param uri 请求uri
	 * @param proxyFlag 是否使用代理
	 * @return
	 * @throws Exception 
	 */
	public abstract String sendGETRequest(String uri, boolean proxyFlag)
			throws Exception;
	/**
	 * 发送POST请求
	 * @param uri 请求uri
	 * @param data 请求数据
	 * @param proxyFlag 是否使用代理
	 * @return
	 */
	public abstract String sendPOSTRequest(String uri,String data,boolean proxyFlag) throws Exception;

}