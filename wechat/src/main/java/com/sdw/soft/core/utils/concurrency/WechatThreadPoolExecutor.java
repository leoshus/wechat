package com.sdw.soft.core.utils.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sonicery_D
 * @date 2014年12月21日
 * @version 1.0.0
 * @description  任务处理线程池
 */
public class WechatThreadPoolExecutor extends ThreadPoolExecutor {

	private static final Logger logger = LoggerFactory.getLogger(WechatThreadPoolExecutor.class);
	
	private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
	
	public WechatThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}
	
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		startTime.set(System.currentTimeMillis());
		logger.info("thread {}:执行开始...t {}",t,r);
	}
	
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		long interval = System.currentTimeMillis() - startTime.get();
		logger.info("task {} 线程已执行结束,共消耗{}毫秒!",r,interval);
		startTime.remove();
		super.afterExecute(r, t);
	}

}
