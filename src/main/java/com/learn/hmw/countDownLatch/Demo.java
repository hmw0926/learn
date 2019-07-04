package com.learn.hmw.countDownLatch;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class Demo {
	
	/** 1、关键词：并发数 */
	public static final int USER_NUM = 10;
	/** 1、构造函数(并发数) */
	private static CountDownLatch cdl = new CountDownLatch(USER_NUM);
	
	/**
	 * CountDownLatch流程
	 * 1、设置并发数 NUM
	 * 2、创建NUM个线程
	 * 3、执行countDown()后, CountDownLatch内部会递减NUM,直到置零时一起执行
	 */
	@Test
	public void testLatch() throws Exception{
		/** 2、创建NUM个线程*/
		for (int i = 0; i < USER_NUM; i++) {
			new Thread(new Req()).start();
			/** 3、递减*/
			cdl.countDown();
		}
		Thread.sleep(3000);
	}
	
	public class Req implements Runnable{

		public void run() {
			try {
				/** 关键词：线程等待*/
				cdl.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			/** 需要测试的url */
			System.out.println("hello");
		}
	}
	
}
