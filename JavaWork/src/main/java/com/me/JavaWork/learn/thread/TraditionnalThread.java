package com.me.JavaWork.learn.thread;


/****
 * 传统线程
 * 实现线程的两种方式
 * 1.集成thread
 * 2.实现runnable
 * 区别：1.并发，实现runnable。因为runnable是多继承，而thread是单继承。所以在处理并发的时候，一般多使用runnable
 * 	   2.适合于资源的共享。thread没办法实现资源的共享。这是硬伤。（比如：买票，总共100张，使用 5条thread来卖，每条卖100张，那么总数就是500张。但其实没有那么多票）
 * 从线程的实现上可以很好的说明这一点
 * 1.Thread thread= new Thread(){}.start();//这里是直接开启了一个线程。并且是单线程
 * 2.Runnable run  = new Runnable(){}
 * Thread thread2 = new Thread(run){}.start();//这里是把runnable对象交给了thread，又thread来开始线程。可以把runnable对象同时交给几个thread。那么几个thread就共同操作一个runnable对象。实现了资源的共享
 * 
 * 
 * @author trip
 *
 */
public class TraditionnalThread {
	public static void main (String []args){
		
		
		//继承thread
		Thread thread= new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"正在执行");
				}
			}
		};
		//thread.start();
		
		
		//实现runnable。
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"正在执行");
				}
			}
		});
		//thread2.start();
		
		
		//继承thread，实现runnable。但线程开始只会调用thread的线程体
		new Thread(new Runnable(){

			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(700);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Runnable:"+Thread.currentThread().getName()+"正在执行");
				}
			}
			
		}){
			public void run(){
				while(true){
					try {
						Thread.sleep(700);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("l3:"+Thread.currentThread().getName()+"正在执行");
				}
			}
			
		}.start();
		
	}

}
