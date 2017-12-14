package com.me.JavaWork.learn.thread;

import java.util.Random;


/****
 * 
 * 使用ThreadLocal来实现线程中数据的存储
 * @author trip
 *
 */
public class ThreadScopeSharedate_ThreadLocalTest {
	
	/**
	 * ThreadLocal 可以直接在线程的方法体中存储或提取数据。相对于线程独立（多线程互斥）。
	 * 非常方便。
	 * 其实底层也是运用了Map来实现的。
	 * */
	static ThreadLocal<MyThreadObject> threadLocal = new ThreadLocal<MyThreadObject>();
	
	public static void main(String[] args) {
		for ( int i = 1; i < 3; i++) {
			 new Thread(new Runnable(){
				@Override
				public void run() {
					int data = new Random().nextInt();
					MyThreadObject.getThreadInstance().setName("name" + data);
					MyThreadObject.getThreadInstance().setAge( data);
					System.out.println(Thread.currentThread().getName()+" set Name is:" + "name" + data);
					System.out.println(Thread.currentThread().getName()+" set Age is:" + data);
					new A().get();
					new B().get();
				}
			}).start();
			
		}
	}
	static class A {
		void get(){
			MyThreadObject threadLocal = MyThreadObject.getThreadInstance();
			System.out.println("A get data from "+Thread.currentThread().getName()+" 's Name is :" + threadLocal.getName()+" 's Age is :" + threadLocal.getAge());
		}
	}

	static class B {
		void get(){
			MyThreadObject threadLocal = MyThreadObject.getThreadInstance();
			System.out.println("B get data from "+Thread.currentThread().getName()+" 's Name is :" + threadLocal.getName()+" 's Age is :" + threadLocal.getAge());
		}
	}
	
}

class MyThreadObject{
	
	private MyThreadObject(){}
	
	private static ThreadLocal<MyThreadObject> map = new ThreadLocal<MyThreadObject>();
	
	public static MyThreadObject getThreadInstance(){
		MyThreadObject instance = map.get();
		if(instance == null){
			instance = new MyThreadObject();
			map.set(instance);
		}
		return instance;
	}
	
	
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
