package com.sdw.soft.test.thread;

import java.util.Date;

/**
 * @author Sonicery_D
 * @date 2014年11月11日
 * @version 1.0.0
 * @description
 */
public class TestThreadLocalMap  extends Thread{

	@Override
	public void run() {
		while(true){
			System.out.println("当前时间为:"+new Date().getSeconds());
		}
	}
	
	public static void main(String[] args){
		TestThreadLocalMap thread1 = new TestThreadLocalMap();
		thread1.start();
		ThreadLocal tl = new ThreadLocal();
		tl.set("hello ThreadLocal");
		System.out.println("current Thread :"+Thread.currentThread().getName());
		System.out.println("");
	}
	
}
