package com.me.JavaWork.learn.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * 
 * 堵塞队列的测试
 * */
public class DequeTest {
	public static void main(String[] args) {
		Deque<String> deque =  new ArrayDeque(8);
	//	deque.add("aaaaaaa");
		
		System.out.println(deque.getFirst());
		System.out.println(deque.getLast());
		Iterator<String> iterator = deque.iterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
		
		
		
	}

}
