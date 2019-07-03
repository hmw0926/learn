package com.learn.hmw.countDownLatch;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class TestCountDownLatch {

	private static final int USER_NUM = 10;
	private static CountDownLatch cdl = new CountDownLatch(USER_NUM);

	@Test
	public void TestInvoke() throws Exception {
		for (int i = 0; i < USER_NUM; i++) {
			new Thread(new TicketRequest()).start();
			cdl.countDown();
		}
	}

	public class TicketRequest implements Runnable {

		public void run() {
			try {
				cdl.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("hello");
		}
	}
}
