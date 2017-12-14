package com.me.JavaWork.learn.thread.test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

/****
 * 生产消费类型，
 * 
 * 把消费类型改成10条线程来处理、但是消费获取的数据必须是按顺序来处理的。消费线程可以没有顺序
 * 
 * 1.使用同步堵塞队列，当又需求的时候，才做生产，即，当又程序需要用到数据的时候，才往里面方数据。保证数据的顺序
 * 2.有多个线程，那么就可以使用锁或者信号灯来实现线程访问的唯一性，保证输出的数据也是按顺序来的。
 * 
 */
public class Test2 {
	public static void main(String[] args) {
		final SynchronousQueue<String> queue = new SynchronousQueue<String>();
		final Semaphore semaphore = new Semaphore(1);
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					String input;
					try {
						semaphore.acquire();
						input = queue.take();
						String returnInfo = TestDo.doSome(input);
						System.out.println(Thread.currentThread().getName() + ":" +returnInfo);
						semaphore.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				
				}
			}).start();
		}
		
		System.out.println( "begin :" +System.currentTimeMillis()/1000);
		for (int i = 0; i < 10; i++) {//这里不能动
			String input = i + "";//这里不能动
			try {
				queue.put(input);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			String returnInfo = TestDo.doSome(input);
//			System.out.println(Thread.currentThread().getName() + ":" +returnInfo);
			
		}
	}
	
}	
	
//	这个类不能动
	class TestDo{
		public static String doSome(String str){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String returnInfo = str + ":" +System.currentTimeMillis()/1000;
			return returnInfo;
		}
	}

