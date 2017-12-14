package com.me.JavaWork.learn.thread;


/**
 * 线程之间通讯，两个线程交替执行
 *子线程循环10次，主线程循环20次，如此交替执行5次。 
 ***/
public  class TraditionnalThreadCommunication {
	public static void main(String[] args) {
		final Business business = new Business();
		/**
		 * 这里的原理
		 * （
		 * 子循环和主循环在同一个类中，用同步锁来修饰他们的方法
		 * ）
		 * 1.先开启一个线程，线程中调用一个子循环方法
		 * 2.开启一个循环（非线程），调用一个主循环方法
		 * 3.子循环先执行，第一轮执行完成之后，子循环针对统一对象的对象锁解锁，通知另一个等待的线程（主线程）来处理
		 * 4.接收锁的线程开始处理，处理完之后，释放锁，并通知另一个线程（子线程）来处理。
		 * 
		 * 这里为什么不开启两个线程？
		 * 其实开启两个也是可以的，在执行java的时候，本身就开启了一道线程，所以这里，再开启一个线程其实没有必要，所以，直接写代码就可以了
		 * **/
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 5; i++) {
					business.main(i);
				}
			}
		}).start();
		
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				for (int i = 1; i <= 5; i++) {
//					business.sub(i);
//				}
//			}
//		}).start();
		
		for (int i = 1; i <= 5; i++) {
			business.sub(i);
		}
	}
}

class Business {
	private boolean state = false;
	/**
	 * synchronized作用于方法的时候，值针对此方法所在的类中，被synchronized修饰的所有方法在同一时刻只能有一个被访问。
	 * **/
	public synchronized void sub(int t) {
		if (state) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i <= 10; i++) {
			System.out.println("sub线程执行第" + i + "次,主线程第" + t + "次");
		}
		this.state = true;
		this.notify();
	}

	public synchronized void main(int t) {
		if (!state) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i <= 20; i++) {
			System.out.println("mian线程执行第" + i + "次,主线程第" + t + "次");
		}
		this.state = false;
		this.notify();
	}
}
