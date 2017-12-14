package com.me.JavaWork.learn.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/***
 * 
 * 读写锁 实现的缓存
 * 1.都是读的时候的，不互斥
 * 2.读、写都存在的时候，互斥
 * 3.写、写的时候，互斥
 * @author trip
 *
 */
public class CacheTest {
	private Map<String,Object> cache = new HashMap<>();
	
	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	public Object getData(String key){
//		Lock lock =  new ReentrantLock();
//		lock.lock();
//		lock.unlock();
		Object value = null;
		rwl.readLock().lock();//上读锁
		try {
			value = cache.get(key);
			/**
			 * 如果读取的数据为null，这里需要进行具体的查询操作（写操作）。假如有多个读取数据的线程。这里就有多个写操作，所有需要加入写锁。
			假如这里有3个线程。3个都要读，但是这里没有数据，所以，3个线程就要写数据，但只有排在第一位置的线程才能有锁，才可以写。
			第一个写完之后，才能让第二和第三来写。但是如果第一个写了之后，把结果写入了缓存，那么之后的线程就不需要再写数据了，而只需要读，所以再赋值（写操作）的时候，需要再次判断是否已有值，
			 * **/
			rwl.readLock().unlock();//释放读锁
			rwl.writeLock().lock();//加写锁
			try {
				if(value == null){
					value = "赋值";//这里是具体的操作，这里的赋值/写之后，应该把数据写入缓存中。
				}
			} catch (Exception e) {
				
			}finally{
				rwl.writeLock().unlock();//释放写锁
			}
			rwl.readLock().lock();//重新上读锁
		} catch (Exception e) {
			
		}finally{
			rwl.readLock().unlock();//最后释放读锁
		}
		return value;
	} 
}
