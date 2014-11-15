package com.sdw.soft.test.thread;
/**
 * @author Sonicery_D
 * @date 2014年11月13日
 * @version 1.0.0
 * @description
 **/
public class Share {

	public synchronized void print(String str){
		System.out.print("["+str);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.print("thread interrupted ...");
		}
		System.out.print("]");
	}
	
	public static void main(String[] args) {
		Share share = new Share();
		Call call1 = new Call("A",share);
		Call call2 = new Call("B",share);
		Call call3 = new Call("C",share);
		Call call4 = new Call("D",share);
	}
}
class Call implements Runnable{

	private Share share;
	private String str;
	public Call(String str,Share share){
		this.str = str;
		this.share = share;
		Thread thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {
		share.print(str);
	}
	
}