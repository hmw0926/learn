package com.learn.hmw.jvm;

/**
 * 内存泄露
 */
public class MemoryLeakDemo {
	
	public static void main(String[] args) {
		MyStack myStack = new MyStack();
		Object in = new Object();
		System.out.println("in=" + in);
		//入栈
		myStack.push(in);
		//出栈
		Object out = myStack.pop();
		System.out.println("out=" + out);
		//残留的对象
		System.out.println(myStack.elements[0]);
		
	}
}

class MyStack{
	public Object[] elements;
	private int size = 0;
	private static final int Cap = 16;
	
	public MyStack() {
		elements = new Object[Cap];
	}
	//入栈
	public void push(Object e) {
		elements[size] = e;
		size ++;
	}
	//出栈
	public Object pop() {
		size = size -1;
		Object obj = elements[size];
		//不写这一行会造成内存泄露
		elements[size] = null;
		return obj;
	}
}
