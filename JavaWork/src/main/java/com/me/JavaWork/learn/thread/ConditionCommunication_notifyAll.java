package com.me.JavaWork.learn.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三级线程。
 * 1级循环10次，然后让2级循环10次，然后让3级循环10次。
 * 如此循环5次.
 * 使用synchronized 和notifyAll 和wait来实现
 ***/
public class ConditionCommunication_notifyAll {
	public static void main(String[] args) {
		final Business business = new Business();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 5; i++) {
					business.lv1(i);
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 5; i++) {
					business.lv2(i);
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 5; i++) {
					business.lv3(i);
				}
			}
		}).start();
	}

static class Business {
	int lv = 1;
	
	public synchronized void lv1(int t) {
		try {
			while (lv != 1) {
				this.wait();
			}
			for (int i = 0; i <= 10; i++) {
				System.out.println("主线程第" + t + "次 ， lv  1     第" + i + "次");
			}
			lv = 2;
			this.notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void lv2(int t) {
		try {
			while (lv != 2) {
				this.wait();
			}
			for (int i = 0; i <= 10; i++) {
				System.out.println("主线程第" + t + "次 ， lv  2     第" + i + "次");
			}
			lv = 3;
			this.notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void lv3(int t) {
		try {
			while (lv != 3) {
				this.wait();
			}
			for (int i = 0; i <= 10; i++) {
				System.out.println("主线程第" + t + "次 ， lv  3     第" + i + "次");
			}
			lv = 1;
			this.notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	}
}
