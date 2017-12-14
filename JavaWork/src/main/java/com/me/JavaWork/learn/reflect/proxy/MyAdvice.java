package com.me.JavaWork.learn.reflect.proxy;

import java.lang.reflect.Method;

public class MyAdvice implements Advice {
	
	public MyAdvice() {
		// TODO Auto-generated constructor stub
	}
	
	long startTime = 0;
	@Override
	public void beforeMethod(Method method) {
		// TODO Auto-generated method stub
		System.out.println("ִ��ǰ�÷���...");
		startTime = System.currentTimeMillis();
	}

	@Override
	public void afterMethod(Method method) {
		// TODO Auto-generated method stub
		System.out.println("ִ�к��ô���...");
		long endTime = System.currentTimeMillis();
		System.out.println(method.getName() + "��������ʱ�䣺" + ( endTime - startTime ));
	}

}
