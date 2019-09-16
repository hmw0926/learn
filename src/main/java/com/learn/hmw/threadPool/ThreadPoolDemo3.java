package com.learn.hmw.threadPool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ThreadPoolDemo3 {

	@Test
	public void delay() throws Exception{
		// 创建一个定长线程池，支持定时及周期性任务执行——延迟执行
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		// 延迟1秒执行
		System.out.println("start>>>" + new Date());
		scheduledThreadPool.schedule(new Runnable() {
			public void run() {
				System.out.println("延迟1秒执行>>>" + new Date() );
			}
		}, 1, TimeUnit.SECONDS);
		
		Thread.sleep(3000);
	}
	
	@Test
	public void delaySome() throws Exception{
		// 创建一个定长线程池，支持定时及周期性任务执行——延迟执行
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		// 延迟1秒后每3秒执行一次
		System.out.println("start>>>" + new Date());
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("延迟1秒执行每3秒执行一次>>>" + new Date());
			}
		}, 1, 3, TimeUnit.SECONDS);
		
		Thread.sleep(10000);
	}
}
