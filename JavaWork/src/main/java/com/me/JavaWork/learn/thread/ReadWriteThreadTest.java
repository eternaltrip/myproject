package com.me.JavaWork.learn.thread;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/****
 *   读写锁 
 * 1.都是读的时候的，不互斥
 * 2.读、写都存在的时候，互斥
 * 3.写、写的时候，互斥
 * @author trip
 *
 */
public class ReadWriteThreadTest {
	public static void main(String[] args) {
		final Queue3 q3 = new Queue3();
		for (int i = 0; i < 3; i++) {
			new Thread(){
				public void run (){
					while(true){
						q3.get();
					}
				}
			}.start();
			
			new Thread(){
				public void run (){
					while(true){
						q3.put(new Random().nextInt(10000));
					}
				}
			}.start();
		}
	}
}

class Queue3{
	private Object data = null;
	ReadWriteLock rwl = new ReentrantReadWriteLock();
	/**
	 * 读数据的时候，加锁，是为了解决在读的时候，防止数据被别人修改。
	 * 如果只是读数据，不存在写，那么不需要用锁，也就是基本的一个变量，多个地方使用而已
	 * */
	public void get(){
		rwl.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "开始读数据..");
			Thread.sleep((long) (Math.random()*1000));
			System.out.println(Thread.currentThread().getName() + "读到数据为" + data);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			rwl.readLock().unlock();
		}
	}
	public void put(Object data){
		rwl.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "开始放数据..");
			Thread.sleep((long) (Math.random()*1000));
			this.data = data;
			System.out.println(Thread.currentThread().getName() + "放的数据为" + data);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			rwl.writeLock().unlock();
		}
	}
}
