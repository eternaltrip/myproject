package com.me.JavaWork.learn.thread;


/**
 * 多线程共享数据
 * 多线程，一个线程执行对象的减1，另一个线程针对对象加1
 * @author Administrator
 *
 */
public class MulitThreadShareData {
	
	
	private static int j = 10 ;
	private int i = 5;
	
	
	//两种方式
	public static void main(String[] args) {
		//1.第一种
		MulitThreadShareData data = new MulitThreadShareData();
		Inc inc = data.new Inc();
		Dec dec = data.new Dec();
		for (int i = 0; i < 2; i++) {
			Thread t1 = new Thread(inc);
			t1.start();
			Thread t2 = new Thread(dec);
			t2.start();
		}
		
		//2.第二种
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				public void run() {
					Inc();
				}
			}).start();
			new Thread(new Runnable() {
				public void run() {
					Dec();
				}
			}).start();
		}
		
	}
	
	private synchronized  void Inc1(){
		System.out.print("原数据i：" + i);
		i ++;
		System.out.println(Thread.currentThread().getName()+"++1:"+i);
	}
	
	private synchronized void Dec1(){
		System.out.print("原数据i：" + i);
		i --;
		System.out.println(Thread.currentThread().getName()+"--1:"+i);
	}
	
	
	
	private synchronized static void Inc(){
		System.out.print("原数据j：" + j);
		j ++;
		System.out.println(Thread.currentThread().getName()+"++1:"+j);
	}
	
	private synchronized static void Dec(){
		System.out.print("原数据j：" + j);
		j --;
		System.out.println(Thread.currentThread().getName()+"--1:"+j);
	}
	
	class Inc implements Runnable{
		@Override
		public void run() {
			Inc1();
		}
	}
	
	class Dec implements Runnable{
		@Override
		public void run() {
			Dec1();
		}
	}

}
