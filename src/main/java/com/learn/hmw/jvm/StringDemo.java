package com.learn.hmw.jvm;

public class StringDemo {
	
	public static void main(String[] args) {
		
		test1();
		test2();
	}
	
	private static void test1() {
		/**
		 * 直接使用双引号声明出来的 String 对象会直接存储在常量池中。
		 * 如果不是用双引号声明的 String 对象，可以使用 String 提供的 intern 方法。
		 * String.intern() 是一个 Native 方法，它的作用是：如果运行时常量池中已经包含一个等于
		 * 此 String 对象内容的字符串，则返回常量池中该字符串的引用；如果没有，则在常量池中创建与此 
		 * String 内容相同的字符串，并返回常量池中创建的字符串的引用。
		 */
		String s1 = new String("计算机");  // heap中的String对象
		String s2 = s1.intern();         // 常量池中的String对象 
		String s3 = "计算机";              // 常量池中的String对象
		System.out.println(s2);//计算机
		System.out.println(s1 == s2);//false
		System.out.println(s3 == s2);//true
	}
	
	private static void test2() {
		String str1 = "str";
		String str2 = "ing";
		 
		String str3 = "str" + "ing";//常量池中的对象
		String str4 = str1 + str2; //在堆上创建的新的对象     
		String str5 = "string";//常量池中的对象
		System.out.println(str3 == str4);//false
		System.out.println(str3 == str5);//true
		System.out.println(str4 == str5);//false
	}
}
