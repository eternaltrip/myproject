package com.me.JavaWork.learn.net.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CMDTest {
	
	
	
	/**
	 * 模拟cmd命令，进行各种操作。1.控制台进行输入，2.控制台显示返回的结果
	 * 1.通过查询资料获取cmd打开方式，得到信息（Process process = Runtime.getRuntime().exec("cmd");//进行调用）
	 * 2.java语言在这其中相当于一个中间件。cmd是目标。那么java调用cmd肯定需要一个输出类来输出命令。同时需要一个接受类来处理返回信息。而这些都在Process中。
	 * 2.1 process.getOutputStream() //java对cmd的输出（java的输出流）
	 * 2.2 process.getInputStream() //cmd对java的输出（java这个中间件的数据来源--输入流）
	 * 3.掌握了输入和输出，那么就可以通过两个线程来分别处理，达到交互的作用。
	 * 4.注意：因为是本机测试，一般来说返回的数据都很快。但是如果相应的返回数据很多，而这个时候又输出了命令。那么就返回的信息很可能就会中断，所以从长远的角度以及未来方法拓展性考虑，这里应该做一个互斥。
	 * 5.线程的互斥技术，目前有几种方法可以实现。
	 * 5.1  synchronized 关键字(不是特别推荐，因为这个是作用域方法或者变量。那么对同一个方法或者变量的所有作用域都会加锁。)
	 * 5.2  BlockingQueue(ArrayBlockingQueue 推荐)
	 * 5.3  Lock(Lock lock =  new ReentrantLock(); Condition condition = lock.newCondition();  推荐)
	 * 5.4  wait 和 notify
	 * 
	 * 如果你看到这里，那么恭喜你，上面说的很多都是不正确或者不完整的思考。
	 * 经过实际论证，正常的代码执行顺序给了我一个猛击（学艺不精）
	 * 1.首先，在读取返回信息的时候。用到了 readLine()方法，这个方法会一直在那里等待返回信息，有就读取一行，没有就一直等待，所以后面的代码不会被执行。
	 * 所以当在前面调用这个cmdRead和cmdWrite的时候。一定要为其中的一个使用线程来处理，保证两个方法之间是可以都执行的。不然得不到预期的执行效果。
	 * 2.但是通过这个事故（说的严重点儿），可以发现，因为是控制台既要显示返回的信息，也要显示输入的信息。而这里处理的方式是，让输入和输出都一直等待信息的返回。
	 * 所以，必须使用两个线程来同时处理输入和输出。又因为readLine()方法是一个阻塞方法，所以就不需要用其他额外的方式来实现阻塞。
	 * 因为上面之前分析得到5全部都不需要。当然，如果不用readLine()，那么还是需要的。
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		CMDTest cmdProcess = new CMDTest();
//		cmdProcess.doCmd();
//		cmdProcess.doCmd2();
		cmdProcess.doCmd3();
	}
	
	/**
	 * 方法1
	 * @param cmdTest
	 */
	public void doCmd(){
		String command = "cmd";
		try {
			Process process = Runtime.getRuntime().exec(command);
			new Thread (new Runnable(){
				@Override
				public void run() {
					while(true){
						cmdWrite(process);
					}
				}
			}).start();
			
			new Thread (new Runnable(){
				@Override
				public void run() {
					while(true){
						cmdRead(process);
					}
				}
			}).start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
		
	//读信息方法
	public void cmdRead(Process process){
		System.out.println("开始读信息...");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String retInfo;
			while((retInfo = reader.readLine()) != null){
				System.out.println(retInfo);
			}
			System.out.println("结束读信息");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//写命令方法
	public void cmdWrite(Process process){
		System.out.println("开始写命令...");
		try {
			//获取要写的命令(控制台输入)
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			//获取写命令对象
			PrintWriter writer = new PrintWriter(process.getOutputStream());
			String cmd;
			while( ( cmd = reader.readLine()) != null ){
				writer.println(cmd);
				writer.flush();
			}
			System.out.println("结束写命令");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	
	
	/**
	 * 方法2
	 * @param cmdTest
	 */
	public void doCmd2(){
		String command = "cmd";
		try {
			Process process = Runtime.getRuntime().exec(command);
			cmdWrite2(process);
			cmdRead2(process);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
		
	//读信息方法
	public void cmdRead2(Process process){
		System.out.println("开始读信息...");
		new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
					String retInfo;
					while((retInfo = reader.readLine()) != null){
						System.out.println(retInfo);
					}
					System.out.println("结束读信息");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
	}
	
	//写命令方法
	public void cmdWrite2(Process process){
		System.out.println("开始写命令...");
		new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					//获取要写的命令(控制台输入)
					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					
					//获取写命令对象
					PrintWriter writer = new PrintWriter(process.getOutputStream());
					String cmd;
					while( ( cmd = reader.readLine()) != null ){
						writer.println(cmd);
						writer.flush();
					}
					System.out.println("结束写命令");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
	}
		
	//第三种方式，不实用readLine()
	
	public void doCmd3(){
		String command = "cmd";
		try {
			Process process = Runtime.getRuntime().exec(command);
			
			cmdRead3(process);
			cmdWrite3(process);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//读信息方法
		public void cmdRead3(Process process){
			System.out.println("开始读信息...");
			new Thread(new Runnable(){
				@Override
				public void run() {
					try {
						BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
						InputStream inputStream = process.getInputStream();
						
						System.out.println(inputStream.available());
						Thread.currentThread().sleep(10);
						System.out.println(inputStream.available());
						Thread.currentThread().sleep(10);
						System.out.println(inputStream.available());
						Thread.currentThread().sleep(10);
						System.out.println(inputStream.available());
						InputStreamReader isreader = new InputStreamReader(process.getInputStream());
						
						int count = isreader.read();
						while(count != -1){
							System.out.print(count + " " );
							//BufferedReader breader = new BufferedReader(isreader);
							//System.out.println(breader.read());
							
							count = isreader.read();
						}
						
						
						
						System.out.println(isreader.read());
						
						
						
						StringBuffer retInfo = new StringBuffer();
						byte[] b = new byte[1024];
						while (reader.read() != -1) {
							retInfo.append(reader);
						}
						System.out.println(retInfo);
						System.out.println("结束读信息");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
			
		}
		
		//写命令方法
		public void cmdWrite3(Process process){
			System.out.println("开始写命令...");
			new Thread(new Runnable(){
				@Override
				public void run() {
					try {
						//获取要写的命令(控制台输入)
						BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
						
						//获取写命令对象
						PrintWriter writer = new PrintWriter(process.getOutputStream());
						String cmd;
						while( ( cmd = reader.readLine()) != null ){
							writer.println(cmd);
							writer.flush();
						}
						System.out.println("结束写命令");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
			
		}
}
