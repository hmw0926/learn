package com.learn.hmw.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo4 {
	
	/**
	 	缓冲队列BlockingQueue简介：
    	BlockingQueue是双缓冲队列。BlockingQueue内部使用两条队列，允许两个线程同时向队列一个存储，一个取出操作。
     	在保证并发安全的同时，提高了队列的存取效率。
	 	缓冲队列BlockingQueue和自定义线程池ThreadPoolExecutor
	 */
	public static void main(String[] args) {
		// 创建数组型缓冲等待队列
		BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(20);
		// ThreadPoolExecutor:创建自定义线程池，池中保存的线程数为2，允许最大的线程数为3
		ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 3, 50, TimeUnit.MILLISECONDS, bq);

		for (int i = 0; i < 20; i++) {
			tpe.execute(new TempThread());
		}
		System.out.println("init Queue");

		// 关闭自定义线程池
		tpe.shutdown();
	}
}

class TempThread implements Runnable {

	public void run() {
		System.out.println(Thread.currentThread().getName() + "正在被执行");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
