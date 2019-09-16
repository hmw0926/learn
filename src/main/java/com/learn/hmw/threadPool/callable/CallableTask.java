package com.learn.hmw.threadPool.callable;

import java.util.Random;
import java.util.concurrent.Callable;

public class CallableTask implements Callable<String>{

	public String call() throws Exception {
		String res = new Random().nextInt(100) + "";
		Thread.sleep(1000);
		System.out.println("任务执行:获取到结果 :" + res);
		return res;
	}
}
