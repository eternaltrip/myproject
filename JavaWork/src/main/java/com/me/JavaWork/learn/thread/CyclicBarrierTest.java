package com.me.JavaWork.learn.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/***
 * CyclicBarrier
 * 多个线程执行，规定在某个地方（cb.await();），必须当有规定的线程都到达之后，才能继续。
 * 
 * @author trip
 *
 */
public class CyclicBarrierTest {
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CyclicBarrier cb = new CyclicBarrier(3);//当至少三个线程同时在的时候才能继续
		for (int i = 0; i < 10; i++) {
			Runnable runnable =  new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("Thread " + Thread.currentThread().getName() + " 到达集合地点1，当前共有  " + (cb.getNumberWaiting() + 1) + "已到达，" + (cb.getNumberWaiting() == 2?"都已到达，继续走":"未全部到达"));
						
						cb.await();
						System.out.println("Thread " + Thread.currentThread().getName() + " 到达集合地点2，当前共有  " + (cb.getNumberWaiting() + 1) + "已到达，" + (cb.getNumberWaiting() == 2?"都已到达，继续走":"未全部到达"));
						
						cb.await();
						System.out.println("Thread " + Thread.currentThread().getName() + " 到达集合地点3，当前共有  " + (cb.getNumberWaiting() + 1) + "已到达，" + (cb.getNumberWaiting() == 2?"都已到达，继续走":"未全部到达"));
						
						cb.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
	}
}
