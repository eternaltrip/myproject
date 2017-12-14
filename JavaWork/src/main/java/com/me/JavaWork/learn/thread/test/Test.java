package com.me.JavaWork.learn.thread.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/***
 * 原方法，执行需要16秒，需要改成4个线程，执行时间缩短至4秒
 * */
public class Test {
	
	
	
	public static void main(String[] args) {
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(16);
		
		System.out.println( "begin :" +System.currentTimeMillis()/1000);

		for (int i = 0; i < 16; i++) {//这行不能改
			final String log = ""+(i + 1);//这行不能改
			
//			pareLog(log);//原方法
			try {
				queue.put(log);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		for (int i = 0; i < 4; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						while( !queue.isEmpty()){//这个可以做一个判断，是否为空，为空就不再执行线程了，避免暂用资源，但前提是队列中的数据必须在线程开始之前就全部获取到，不然会出现数据处理不完整的情况
						String log = queue.take();
						pareLog(Thread.currentThread().getName() + "   :" +log);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	
	public static void pareLog(String log){
		try {
			Thread.sleep(1000);
			System.out.println(log + ":" +System.currentTimeMillis()/1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
