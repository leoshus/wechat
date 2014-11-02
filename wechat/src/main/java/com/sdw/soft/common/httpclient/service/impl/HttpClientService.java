package com.sdw.soft.common.httpclient.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpProtocolParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sdw.soft.common.httpclient.service.IHttpClientService;

/**
 * @author Sonicery_D
 * @date 2014年11月2日
 * @version 1.0.0
 * @description httpClient 工具服务
 **/
@Service
public class HttpClientService implements IHttpClientService {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpClientService.class);
	
	private static final String HTTP_PROXY_ADDRESS = "";
	
	private static final int HTTP_PROXY_PORT = 0;
	
	/* (non-Javadoc)
	 * @see com.sdw.soft.common.httpclient.service.IHttpClientService#sendGETRequest(java.lang.String, boolean)
	 */
	@Override
	public String sendGETRequest(String uri,boolean proxyFlag) throws Exception{
		String result = "";
		if(StringUtils.isBlank(uri)){
			logger.error("sendGETRequest --- uri 为空!");
			throw new Exception("uri 错误!");
		}
		DefaultHttpClient httpClient = new DefaultHttpClient();
		if(proxyFlag){
			HttpHost proxy = new HttpHost(HTTP_PROXY_ADDRESS,HTTP_PROXY_PORT);
			httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		}
		try{
			HttpProtocolParams.setUserAgent(httpClient.getParams(),
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.9) Gecko/20100315 Firefox/3.5.9");
			HttpGet httpGet = new HttpGet(uri);
			logger.info("本次GET请求的uri为:{}",httpGet.getURI());
			httpGet.getParams().setParameter(ConnManagerPNames.TIMEOUT, 2500L);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			result = httpClient.execute(httpGet,responseHandler);
			result = new String(result.getBytes("ISO8859-1"));
			logger.info("result is:{}",result);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("httpService请求报错---"+e.getMessage(),e);
		}finally{
			httpClient.getConnectionManager().shutdown();
		}
		return result;
	}

	@Override
	public String sendPOSTRequest(String uri, String data, boolean proxyFlag) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(uri);
		String result = "";
		if(proxyFlag){
			HttpHost proxy = new HttpHost(HTTP_PROXY_ADDRESS,HTTP_PROXY_PORT);
			httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		}
		try {
			StringEntity entity = new StringEntity(new String(data.getBytes("UTF-8"),"ISO8859-1"));
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
			
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			result = httpClient.execute(httpPost, responseHandler);
			logger.info("result is:{}",result);
		} catch (Exception e) {
			logger.info("httpClient POST 请求报错! --- "+e.getMessage(),e);
			e.printStackTrace();
		}finally{
			httpClient.getConnectionManager().shutdown();
		}
		return result;
	}
}
