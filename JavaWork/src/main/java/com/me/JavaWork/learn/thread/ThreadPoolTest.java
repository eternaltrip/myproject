package com.me.JavaWork.learn.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


/**
 * 线程池 的几种类型
 * 
 * **/
public class ThreadPoolTest {

	public static void main(String[] args) {
		
		new ThreadPoolTest().CompletionService();
//		
//		new ThreadPoolTest().future();
//		new ThreadPoolTest().futureTask();
	}

	
	
	/**
	 * 创建一个"固定"线程池，并制定最大线程数量为4
	 * **/
	public void  newFixedThreadPool(){
		//创建一个"固定"线程池，并制定最大线程数量为4
		ExecutorService threadPool = Executors.newFixedThreadPool(4);
		
		for (int i = 0; i < 10; i++) {
			final int taskNum = i;
			//添加新任务到线程池中,这里添加了10个任务
			threadPool.execute(new Runnable(){
				@Override
				public void run() {
					//当前线程执行的工作
					for (int i = 0; i < 3; i++) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()+" 循环第 " + i +"次" + ", 当前是第 " + taskNum + "个任务");
					}
					
				}
			});
		}
		threadPool.shutdown();
	}
	
	
	
	/**
	 * 线程池——创建一个"缓存"线程池，不能制定数量
	 * **/
	public void  newCachedThreadPool(){
		//创建一个"缓存"线程池，不能制定数量
		ExecutorService threadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int taskNum = i;
			//添加新任务到线程池中,这里添加了10个任务
			threadPool.execute(new Runnable(){
				@Override
				public void run() {
					//当前线程执行的工作
					for (int i = 0; i < 3; i++) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()+" 循环第 " + i +"次" + ", 当前是第 " + taskNum + "个任务");
					}
					
				}
			});
		}
		threadPool.shutdown();
	}
	
	
	/**定时执行，多久之后执行一次
	 * 
	 * **/
	public void schedule(){
		//定时执行，多久之后执行一次
		Executors.newScheduledThreadPool(3).schedule(new Runnable(){

			@Override
			public void run() {
				System.out.println("开始暴走...");
			}
			
		}, 2, TimeUnit.SECONDS);
	}
	
	
	
	/**定时执行，多久之后执行，并且之后，每隔多久循环执行
	 * 
	 * **/
	public void scheduleAtFixedRate(){
		
		//定时执行，多久之后执行，并且之后，每隔多久循环执行
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable(){
			@Override
			public void run() {
				System.out.println("开始暴走...");
			}
		}, 2, 1, TimeUnit.SECONDS);
	}
	
	/**
	 * 单线程池,使用future与Callable获取返回值
	 * 获取结果需要等待
	 * **/
	public void future(){
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		Future<String> future = threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(500);
				return "hello";
			}
			
		});
		
		System.out.println("等待结果");
		try {
			System.out.println("结果为：" + future.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}finally {
			threadPool.shutdown();
		}
	}
	
	/**
	 * futuretask
	 * */
	public void futureTask(){
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>(){
			@Override
			public String call() throws Exception {
				return "aaaaa";
			}
		});
		try {
			executorService.execute(futureTask);
			System.out.println(futureTask.get());
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			executorService.shutdown();
		}
	}
	
	
	/**
	 * 使用future与Callable获取返回值
	 * 使用CompletionService当结果返回时，就可以获取，不需要一直等待
	 * **/
	public void CompletionService(){
		ExecutorService threadPool2 = Executors.newFixedThreadPool(5);
		CompletionService<Integer> completionService = new ExecutorCompletionService<>(threadPool2);
		for (int i = 0; i < 5; i++) {
			final int num = i;
			completionService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					int sleepTime = new Random().nextInt(50000);
					System.out.println("睡眠："+ sleepTime +"  s");
					Thread.sleep(sleepTime);
					return num;
				}
			});
		}
		
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						System.out.println("开始执行,当前时间：" + System.currentTimeMillis());
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			}
		});
		thread.start();
		
		
		try {
			for (int i = 0; i < 5; i++) {
				System.out.println("获取数据...");
				System.out.println(completionService.take().get());
				System.out.println("获取完毕");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}finally {
			threadPool2.shutdown();
		}

		
	}
	
	
	
	
	
	
	
}
