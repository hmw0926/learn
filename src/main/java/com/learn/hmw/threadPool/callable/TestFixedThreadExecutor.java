package com.learn.hmw.threadPool.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestFixedThreadExecutor {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new TestFixedThreadExecutor().exec();
	}

	/**
	 	实现多线程的办法，实现Runnable接口重新run，继承Thread 重写run
	 	####重要：因为run方法的并没有返回值，使用带返回值的Callable接口
	 */
	void exec() throws InterruptedException, ExecutionException {
		// 进行异步任务列表
		List<FutureTask<String>> futureTasks = new ArrayList<FutureTask<String>>();
		// 创建数组型缓冲等待队列
		BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(20);
		// ThreadPoolExecutor:创建自定义线程池，池中保存的线程数为2，允许最大的线程数为3
		ThreadPoolExecutor executorService = new ThreadPoolExecutor(2, 3, 50, TimeUnit.MILLISECONDS, bq);
		
		long start = System.currentTimeMillis();
		// 类似与run方法的实现 Callable是一个接口，在call中手写逻辑代码
		Callable<String> callable = new CallableTask();

		for (int i = 0; i < 10; i++) {
			// 创建一个异步任务
			FutureTask<String> futureTask = new FutureTask<String>(callable);
			futureTasks.add(futureTask);
			// 提交异步任务到线程池，让线程池管理任务
			// 由于是异步并行任务，所以这里并不会阻塞
			executorService.submit(futureTask);
		}

		List<String> resultList = new ArrayList<String>();
		for (FutureTask<String> futureTask : futureTasks) {
			// futureTask.get() 得到我们想要的结果
			// 该方法有一个重载get(long timeout, TimeUnit unit) 第一个参数为最大等待时间，第二个为时间的单位
			resultList.add(futureTask.get());
		}
		long end = System.currentTimeMillis();
		System.out.println("线程池的任务全部完成:结果为>>> ");
		for (int i = 0; i < resultList.size(); i++) {
			String str = resultList.get(i);
			System.out.println("第" + i + "个任务>>>" + str);
		}
		System.out.println("使用时间：" + (end - start) + "ms");
		// 清理线程池
		executorService.shutdown();
		System.out.println("main线程关闭，进行线程的清理");
	}
}
