package com.learn.hmw.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo1 {
	
	/**
	 	可缓存线程池，先查看池中有没有以前建立的线程，如果有，就直接使用。如果没有，就建一个新的线程加入池中.
	 	缓存型池子通常用于执行一些生存期很短的异步型任务
	  	当执行当前任务时上一个任务已经完成，会复用执行上一个任务的线程，而不用每次新建线程
	 */
	public static void main(String[] args) {

		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			try {
				// sleep可明显看到使用的是线程池里面以前的线程，没有创建新的线程
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			cachedThreadPool.execute(new Runnable() {
				public void run() {
					// 打印正在执行的缓存线程信息
					System.out.println(Thread.currentThread().getName() + "正在被执行");
				}
			});
		}
	}
}