package com.learn.hmw.work;

public class Demo {
	private static Integer num1 = 123;
	private static Integer num2 = new Integer("123");
	
	public static void main(String[] args) {
		System.out.println(num1.equals(num2));
	}
}
