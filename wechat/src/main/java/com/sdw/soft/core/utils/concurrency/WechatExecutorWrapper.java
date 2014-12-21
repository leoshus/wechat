package com.sdw.soft.core.utils.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

/**
 * @author Sonicery_D
 * @date 2014年12月21日
 * @version 1.0.0
 * @description
 */
public class WechatExecutorWrapper implements DisposableBean {

	
	private static final Logger logger = LoggerFactory.getLogger(WechatExecutorWrapper.class);
	
	/**
	 * 线程池
	 */
	private WechatThreadPoolExecutor wechatExecutor ;
	
	/**
	 * 保留线程数量
	 */
	private int corePoolSize = 1;
	/**
	 * 最大线程数量
	 */
	private int maximumPoolSize = 5;
	/**
	 * 超时时间,单位秒
	 */
	private long timeout = 60;
	/**
	 * 等待队列容量
	 */
	private int workQueueCapacity = 100;
	
	/**
	 * 
	 * @param task
	 */
	public void execute(Runnable task){
		this.fetchThreadPoolExecutor().execute(task);
	}
	/**
	 * 返回单例模式的线程池任务执行者  
	 * 前提<bean id="wechatExecutorWrapper" class="com.sdw.soft.core.utils.concurrency.WechatExecutorWrapper" scope="singleton"></bean>
	 * @return
	 */
	private ThreadPoolExecutor fetchThreadPoolExecutor(){
		if(this.wechatExecutor == null){
			BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(this.workQueueCapacity);
			this.wechatExecutor = new WechatThreadPoolExecutor(this.corePoolSize, this.maximumPoolSize, this.timeout, TimeUnit.SECONDS, workQueue);
			this.wechatExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		}
		return this.wechatExecutor;
	}
	@Override
	public void destroy() throws Exception {

		if(this.fetchThreadPoolExecutor() != null && (!this.fetchThreadPoolExecutor().isShutdown())){
			this.fetchThreadPoolExecutor().shutdown();
			logger.info("**************************WechatThreadPoolExecutor has been shutdown*************************");
		}
	}
	public int getCorePoolSize() {
		return corePoolSize;
	}
	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}
	public int getMaximumPoolSize() {
		return maximumPoolSize;
	}
	public void setMaximumPoolSize(int maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}
	public long getTimeout() {
		return timeout;
	}
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	public int getWorkQueueCapacity() {
		return workQueueCapacity;
	}
	public void setWorkQueueCapacity(int workQueueCapacity) {
		this.workQueueCapacity = workQueueCapacity;
	}

}
