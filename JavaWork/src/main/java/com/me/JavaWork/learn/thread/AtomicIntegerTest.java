package com.me.JavaWork.learn.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
	public static void main(String[] args) {
		int num = 5;
		AtomicInteger atomicInteger = new AtomicInteger();
		new Thread(new Runnable(){

			@Override
			public void run() {
				atomicInteger.addAndGet(num);
			}}).start();
	}

}
