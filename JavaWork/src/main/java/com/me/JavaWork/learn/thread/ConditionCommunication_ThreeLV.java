package com.me.JavaWork.learn.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三级线程。
 * 1级循环10次，然后让2级循环10次，然后让3级循环10次。
 * 如此循环5次
 * 使用lock 和   condition来实现
 * 在Condition中，用await()替换wait()，用signal()替换notify()，用signalAll()替换notifyAll()
 ***/
public class ConditionCommunication_ThreeLV {
	public static void main(String[] args) {
		final Business business = new Business();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 5; i++) {
					business.Lv2(i);
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 5; i++) {
					business.Lv1(i);
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 5; i++) {
					business.Lv3(i);
				}
			}
		}).start();
	}

static class Business {
	Lock lock =  new ReentrantLock();
	Condition condition1 = lock.newCondition();
	Condition condition2 = lock.newCondition();
	Condition condition3 = lock.newCondition();
	int lv = 1;
	
	public  void Lv1(int t) {
		System.out.println("lv1 method ................开始抢夺资源");
		lock.lock();
		try {
			while (lv != 1) {
				System.out.println("lv1 method .................被阻塞");
				condition1.await();	
			}
			System.out.println("lv1 method .................开始执行核心程序");
			for (int i = 0; i <= 10; i++) {
				System.out.println("lv 1  第" + i + "次,主线程第" + t + "次");
			}
			lv = 2;
			System.out.println("lv1 method ................执行完毕，通知lv2 method");
			condition2.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.out.println("lv1 method ................释放资源");
			lock.unlock();
		}
	}

	public  void Lv2(int t) {
		System.out.println("lv2 method 开始抢夺资源");
		lock.lock();
		try {
			while (lv != 2) {
				System.out.println("lv2 method .................被阻塞");
				condition2.await();	
			}
			System.out.println("lv2 method .................开始执行核心程序");
			for (int i = 0; i <= 10; i++) {
				System.out.println("lv 2  第" + i + "次,主线程第" + t + "次");
			}
			lv = 3;
			System.out.println("lv2 method ................执行完毕，通知lv3 method");
			condition3.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.out.println("lv2 method ................释放资源");
			lock.unlock();
		}
	}
	
	public  void Lv3(int t) {
		System.out.println("lv3 method 开始抢夺资源");
		lock.lock();
		try {
			while (lv != 3) {
				System.out.println("lv3 method .................被阻塞");
				condition3.await();	
			}
			System.out.println("lv3 method .................开始执行核心程序");
			for (int i = 0; i <= 10; i++) {
				System.out.println("lv 3   第" + i + "次,主线程第" + t + "次");
			}
			lv = 1;
			System.out.println("lv3 method ................执行完毕，通知lv1 method");
			condition1.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.out.println("lv3 method ................释放资源");
			lock.unlock();
		}
	}
}
}