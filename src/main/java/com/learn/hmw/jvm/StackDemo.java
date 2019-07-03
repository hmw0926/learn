package com.learn.hmw.jvm;

public class StackDemo {
	
	/**
	 * 虚拟机栈操作javap -c StackDemo.class >> StackDemo.txt
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int a = 1;
		int b = 2;
		int c = 5;
		System.out.println((a + b) * c); 
	}
}
