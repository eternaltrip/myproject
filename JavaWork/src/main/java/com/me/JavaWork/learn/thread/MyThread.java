package com.me.JavaWork.learn.thread;


public class MyThread implements Runnable {

	public static void main(String args[]){
		
		/**
		 * 当前类实现了Runnable接口，然后把当前类作为对象传递给了多个线程。
		 * 这也说明了，实现runnable和继承thread的区别。
		 * */
		MyThread mt=new MyThread();
		Thread t1=new Thread(mt,"t01");
		Thread t2=new Thread(mt,"t02");
		Thread t3=new Thread(mt,"t03");
		Thread t4=new Thread(mt,"t04");
		Thread t5=new Thread(mt,"t05");
		Thread t6=new Thread(mt,"t06");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		}


	public void run(){
		//这里的this代表当前类,当前类实现了runnable接口，然后作为对象传递给多个线程，供线程使用。
		synchronized(this){
			try {
				Thread.sleep(1000);
				System.out.println();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println();
		}
		}
}