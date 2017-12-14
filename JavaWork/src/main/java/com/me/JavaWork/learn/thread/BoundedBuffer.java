package com.me.JavaWork.learn.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/***
 * 缓存队列test
 * 队列，先进先出。
 * 1.当队列存满了之后，就无法再存入，只能先等取出至少一个的时候，再能存入。
 * 2.当队列中没有时，就无法取出，只能先等到，至少存入一个的时候，才能取出。
 * **/
public class BoundedBuffer {
	final Lock lock = new ReentrantLock();
	   final Condition notFull  = lock.newCondition(); 
	   final Condition notEmpty = lock.newCondition(); 

	   final Object[] items = new Object[3];
	   int putptr, takeptr, count;

	   public void put(Object x) throws InterruptedException {
	     lock.lock();
	     try {
	       while (count == items.length){
	         notFull.await();
	       }
	       items[putptr] = x;
	       if (++putptr == items.length){
	    	   putptr = 0;
	       }
	       ++count;
	       notEmpty.signal();
	     } finally {
	       lock.unlock();
	     }
	   }

	   public Object take() throws InterruptedException {
	     lock.lock();
	     try {
	       while (count == 0){
	         notEmpty.await();
	       }
	       Object x = items[takeptr];
	       if (++takeptr == items.length){
	    	   takeptr = 0;
	       }
	       --count;
	       notFull.signal();
	       return x;
	     } finally {
	       lock.unlock();
	     }
	   }
}
