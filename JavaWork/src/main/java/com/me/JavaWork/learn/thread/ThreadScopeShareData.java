package com.me.JavaWork.learn.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/***
 * 线程作用域内，数据共享。
 * map形式
 * */
public class ThreadScopeShareData {
	private static Map<Thread,Integer> threadData = new HashMap<>();
	public static void main(String[] args) {
		for ( int i = 1; i < 3; i++) {
			 new Thread(new Runnable(){
				@Override
				public void run() {
					int data = new Random().nextInt();
					threadData.put(Thread.currentThread(), data);
					System.out.println(Thread.currentThread().getName()+" set data is:" + data);
					new A().get();
					new B().get();
				}
			}).start();
			
		}
	}
	static class A {
		void get(){
			int data = threadData.get(Thread.currentThread());
			System.out.println("A get data from "+Thread.currentThread().getName()+" is :" + data);
		}
	}

	static class B {
		void get(){
			int data = threadData.get(Thread.currentThread());
			System.out.println("B get data from "+Thread.currentThread().getName()+" is :" + data);
		}
	}
}