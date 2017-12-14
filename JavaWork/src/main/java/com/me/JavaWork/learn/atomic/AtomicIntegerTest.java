package com.me.JavaWork.learn.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
	public static void main(String[] args) {
		AtomicInteger totalTime = new AtomicInteger(0);
		
		System.out.println(totalTime.get());
		System.out.println(totalTime.addAndGet(20));
		System.out.println(totalTime.get());
		
	}

}
