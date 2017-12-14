package com.me.JavaWork.learn.thread.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;

/****
 * 现有程序同时启动了4条线程去调用TestDo.doSome(key,value) 。犹豫TestDo.doSome(key,value)方法内的代码是先暂停1s，然后再输出以秒为单位的当前时间值，所以会打印出4个相同的时间值，如下：
 * 4:4：12581996115
 * 1:1：12581996115
 * 3:3：12581996115
 * 1:2：12581996115
 *修改代码，如果又几个线程调用TestDo.doSome(key,value)方法时，传递进入的key相等）equals比较为true（，则这几个线程应互斥排队输出结果。
 *即，当有两个线程的key都是“1”的时候，他们中的一个要比另一个玩输出一秒。
 * 4:4：12581996115
 * 1:1：12581996115
 * 3:3：12581996115
 * 1:2：12581996116
 */
public class Test3 extends Thread {
	private String key;
	private String value;
	private TestDo1 testDo;
	public Test3(String key,String key2,String value){
		this.testDo = TestDo1.getInstance();
		this.key = key + key2;
		this.value = value;
	}
	
	public static void main(String[] args) {
		Test3 test1 = new Test3("1","","1");
		Test3 test2 = new Test3("2","","2");
		Test3 test3 = new Test3("3","","3");
		Test3 test4 = new Test3("1","","4");
		System.out.println("begin:" + System.currentTimeMillis()/1000);
		test1.start();
		test2.start();
		test3.start();
		test4.start();
	}
	public void run (){
		testDo.doSome(key, value);
	}
}
class TestDo1{
	private TestDo1(){};
	
	//开始该代码
	//private ArrayList arrayList = new ArrayList<>();//arrayList不是线程安全的，当多线程来访问的时候，如果进行迭代，会抛异常
	private CopyOnWriteArrayList arrayList = new CopyOnWriteArrayList();
	//结束修改
	
	private  static TestDo1 new_testDoInstance =  new TestDo1();
	public static TestDo1 getInstance(){
		return new_testDoInstance;
	}
	
	public void doSome(Object key,String value){
		
		//开始该代码
		Object o =  key;
		if(arrayList.contains(o)){
			for (Iterator iterator = arrayList.iterator();iterator.hasNext();) {
				Object oo = iterator.next();
				if (oo.equals(o)) {
					o = oo;
				}
			}
		}else{
			arrayList.add(o);
		}
		//修改结束
		
		synchronized(o)
		//大括号里的同步代码块不能修改
		{
			try {
				Thread.sleep(1000);
				System.out.println(key+":"+value+":"+System.currentTimeMillis()/1000);
			} catch (Exception e) {
			e.printStackTrace();	
			}
		}
	}
}
