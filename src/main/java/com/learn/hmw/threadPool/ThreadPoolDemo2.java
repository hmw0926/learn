package com.learn.hmw.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo2 {

	/**
	 * 因为线程池大小为3，每个任务输出打印结果后sleep 2秒，所以每两秒打印3个结果。
	 */
	public static void main(String[] args) {

		// 创建一个可重用固定个数的线程池
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++) {
			fixedThreadPool.execute(new Runnable() {
				public void run() {
					try {
						// 打印正在执行的缓存线程信息
						System.out.println(Thread.currentThread().getName() + "正在被执行");
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}
