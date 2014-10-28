package com.sdw.soft.common.reply.service;

/**
 * @author Sonicery_D
 * @date 2014年10月28日
 * @version 1.0.0
 * @description
 **/
public interface IReplyService {

	/**
	 * 处理微信请求数据 并作出响应
	 * @param requestXml
	 * @return
	 */
	public abstract String dealReceiveMessage(String requestXml);

}