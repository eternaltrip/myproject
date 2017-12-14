package com.me.JavaWork.learn.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


/***
 * 
 * semaphore ， 类似与信号灯。
 * 假如有三台atm机，有10个人来取钱。那么同时最多只能有3个人在取钱。当其中一个取钱完成之后，离开atm机，才能让剩下的人中的一个来取钱。
 * @author trip
 *
 */
public class SemaphoreTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final Semaphore sp = new Semaphore(3);//同时只能又3个线程就行操作。
		for (int i = 0; i < 10; i++) {
			Runnable runnable =  new Runnable() {
				
				@Override
				public void run() {
					try {
						sp.acquire();
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("Thread " + Thread.currentThread().getName() + " get IN,Current Thread is " + (3 - sp.availablePermits()) );
					try {
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("Thread " + Thread.currentThread().getName() + "Ready To Leave"  );
						sp.release();
						System.out.println("Thread " + Thread.currentThread().getName() + " get Out ,Current Thread is " + (3 - sp.availablePermits()) );
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
	}
}
