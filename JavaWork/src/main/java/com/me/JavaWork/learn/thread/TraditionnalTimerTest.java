package com.me.JavaWork.learn.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/***
 * 传统定时器
 * 在线程中，使用executorsService 也可以实现定时器操作。并且还能实现循环操作
 * 详情请看@ThreadPoolTest.schedule
 * */
public class TraditionnalTimerTest {
	static int count = 0;
	public static void main(String[] args) {
		
		//需要继承TimerTask，并重写run方法。run方法就是定时执行的程序
		class MyTimerTask extends TimerTask{
			@Override
			public void run() {
				System.out.println("bombing");
				new Timer().schedule(new MyTimerTask(), 2000);
			}
		}
		
		//这里调用定时执行的程序，不会立即执行，会在2s钟后执行。
		new Timer().schedule(new MyTimerTask(), 2000);
		
		//循环执行，每隔1s，只是为了打印一个时间标签
		while(true){
			System.out.println(new Date().getSeconds());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
