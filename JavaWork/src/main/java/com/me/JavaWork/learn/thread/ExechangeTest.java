package com.me.JavaWork.learn.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;


/****
 * exechange 是为了在两个线程中交换数据。但只能在两个线程中交换。
 * 线程1 输入数据，
 * 线程2 读取数据，在读取的过程中是处于一直等待状态，直到获取到数据
 */
public class ExechangeTest {
	public static void main(String[] args) {
		Exchanger<List<Integer>> exchanger = new Exchanger<>();
		new Consumer(exchanger).start();
		new Producer(exchanger).start();
	}
		
}

class Producer extends Thread {
	List<Integer> list = new ArrayList<>();
	Exchanger<List<Integer>> exchanger = null;
	public Producer(Exchanger<List<Integer>> exchanger) {
		super();
		this.exchanger = exchanger;
	}
	@Override
	public void run() {
		Random rand = new Random();
		for(int i=0; i<10; i++) {
			try {
				Thread.sleep((long) (Math.random() * 10000));
				System.out.println(Thread.currentThread().getName() + "开始输入数据....");
				list.clear();
				list.add(rand.nextInt(10000));
				list.add(rand.nextInt(10000));
				list.add(rand.nextInt(10000));
				list.add(rand.nextInt(10000));
				list.add(rand.nextInt(10000));
				list = exchanger.exchange(list);
				System.out.println(Thread.currentThread().getName() + "数据输入完成。");
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}

class Consumer extends Thread {
	List<Integer> list = new ArrayList<>();
	Exchanger<List<Integer>> exchanger = null;
	public Consumer(Exchanger<List<Integer>> exchanger) {
		super();
		this.exchanger = exchanger;
	}
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + "正在获取数据...");
				list = exchanger.exchange(list);
				System.out.println(Thread.currentThread().getName() + "已得到数据。");
				System.out.print(list.get(0)+", ");
				System.out.print(list.get(1)+", ");
				System.out.print(list.get(2)+", ");
				System.out.print(list.get(3)+", ");
				System.out.println(list.get(4)+", ");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}