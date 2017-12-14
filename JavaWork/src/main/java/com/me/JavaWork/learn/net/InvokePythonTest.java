package com.me.JavaWork.learn.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InvokePythonTest {
	public static void main(String[] args) throws Exception {
		String outputFile = "d:\\python\\213.html";
		String file1 = "d:\\python\\221.182.42.1(1).txt";
		String file2 = "d:\\python\\221.182.42.1.txt";
		String command = "C:\\WorkFiles\\python3.6\\python.exe D:\\python\\compare.py " + file1 + " " + file2 + " "+ outputFile;
		System.out.println("start");
		Process pr = Runtime.getRuntime().exec(command);

		BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String line;
		Boolean status = false;
		while (status == false && (line = in.readLine()) != null) {
			if(line.equals("ok")){
				status = true;
			}
			System.out.println(line);
		}
		in.close();
		pr.waitFor();
		System.out.println("end");

	}

}
