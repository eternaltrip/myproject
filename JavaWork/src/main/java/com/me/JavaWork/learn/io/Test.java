package com.me.JavaWork.learn.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Test {
	
	public static void main(String[] args) {
		
		Test test = new Test();
		String str = test.nextString();
		System.out.println(str);
		
		int b = test.getInt();
		System.out.println(b);
		
		
	}

	
	
	public int getInt(){
		return Integer.parseInt(nextString())  + 2;
	}


	private String nextString() {
		InputStreamReader inputStream = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStream);
		
		String str = null;
		try {
			str = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}
