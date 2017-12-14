package com.me.JavaWork.learn.thread;

import java.util.concurrent.CountDownLatch;

/****
 * countDownLatch 
 * 当计数器为0的时候，才继续执行之后的代码。如果不能减少到0 ，那么代码就会堵塞。
 */
public class CountDownLatchTest{
	public static void main(String args[]) throws InterruptedException {
		CountDownLatch startSignal = new CountDownLatch(1);//设置起始值
		CountDownLatch doneSignal = new CountDownLatch(5);

		for (int i = 0; i < 5; ++i) // create and start threads
			new Thread(new Worker(startSignal, doneSignal)).start();

		System.out.println("准备开始"); 
		startSignal.countDown();//只有当这个计数器归0之后，后面的线程才能执行
		System.out.println("做一点事情2222222222");
		doneSignal.await(); // 等待所有线程技术
	}
}

class Worker implements Runnable {
	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;

	Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
	}

	public void run() {
		try {
			System.out.println(Thread.currentThread().getName()+ " 当前startSignal数在await之前：" + startSignal.getCount());
			startSignal.await();
			System.out.println(Thread.currentThread().getName()+ " 当前startSignal数在await之后：" + startSignal.getCount());
			
			doWork();
			System.out.println(Thread.currentThread().getName()+ " 当前doneSignal数在await之前：" + doneSignal.getCount());
			doneSignal.countDown();
			System.out.println(Thread.currentThread().getName()+ " 当前doneSignal数在await之后：" + doneSignal.getCount());
		} catch (InterruptedException ex) {
		} // return;
	}

	void doWork() {
		try {
			System.out.println(Thread.currentThread().getName()+ " :这里要休息2s");
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName()+ " :休息2s结束");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

