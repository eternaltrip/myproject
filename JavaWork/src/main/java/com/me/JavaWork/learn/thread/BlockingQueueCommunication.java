package com.me.JavaWork.learn.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *堵塞队列blockingqueue
 *几个方法需要注意
 *	Throws exception	Special value（boolean）	Blocks（堵塞）			Times out
 *	Insert	add(e)		offer(e)				put(e)				offer(e, time, unit)
 *	Remove	remove()	poll()					take()				poll(time, unit)
 *	Examine	element()	peek()					not applicable		not applicable
 *
 ***/

/**
 * 主线程执行5次，次线程执行10.如此循环交替执行5次
 * */
public class BlockingQueueCommunication {
	public static void main(String[] args) {
		final Business business = new Business();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 5; i++) {
					business.main(i);
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 5; i++) {
					business.sub(i);
				}
			}
		}).start();
	}

static class Business {
	BlockingQueue<Integer> queue1 =  new ArrayBlockingQueue<Integer>(1);
	BlockingQueue<Integer> queue2 =  new ArrayBlockingQueue<Integer>(1);
	
	/***
	 * 匿名构造方法，在所有构造方法之前运行
	 */
	{
		try {
			queue2.put(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public  void sub(int t) {
		try {
			queue1.put(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i <= 10; i++) {
			System.out.println("sub线程执行第" + i + "次,主线程第" + t + "次");
		}
		try {
			queue2.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public  void main(int t) {
		try {
			queue2.put(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i <= 20; i++) {
			System.out.println("mian线程执行第" + i + "次,主线程第" + t + "次");
		}
		try {
			queue1.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
}

