package com.me.JavaWork.learn.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class WindowCMDTest {
//	public static void main(String[] args) throws IOException {
//		Process p = Runtime.getRuntime().exec("cmd");
//		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//		String s = null;
//		while ((s = reader.readLine()) != null) {
//			System.out.println(s);
//			
//		}
//	
//
//	}
	public static void main(String[] args) {

		try {
			Process process = Runtime.getRuntime().exec("cmd"); // cmd /c start
																// 可以打开另一个窗口
			PrintWriter writer = new PrintWriter(process.getOutputStream());
			System.out.println("开始调用。。。");
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			
			new CommandThread(writer).start();
			
			String s = null;
			while ((s = br.readLine()) != null) {
				System.out.println(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class CommandThread extends Thread {
		PrintWriter writer;
		BufferedReader br = null;

		CommandThread(PrintWriter writer) {
			this.writer = writer;
			br = new BufferedReader(new InputStreamReader(System.in));
			this.setDaemon(true);
		}

		@Override
		public void run() {
			try {
				String cmd = null;
				while ((cmd = br.readLine()) != null) {
					writer.println(cmd);
					writer.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
