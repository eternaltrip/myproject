package com.me.JavaWork.learn.test;

import java.util.ArrayList;
import java.util.List;

public class xunhuan {
	public static void main(String[] args) {
		List<String> aa = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			aa.add("avb" + i);
		}
		
		for (int i = 0; i < aa.size(); i++) {
			aa.add("123");
			System.out.println(i);
		}
		int i = 0;
		while (i != 10) {
			i ++;
			if(i==5){
				break;
			}
		}
		
		System.out.println(i);
		
	}
}
