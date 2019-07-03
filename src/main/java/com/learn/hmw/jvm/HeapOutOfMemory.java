package com.learn.hmw.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Heap堆溢出
 */
public class HeapOutOfMemory {
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		String str = "String";
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			str += str;
			list.add(str);
		}
	}
}
