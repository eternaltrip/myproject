package com.me.JavaWork.learn.reflect.proxy.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

public class SpringAOPTest {
	public static void main(String[] args) {
		
		InputStream inputStream = SpringAOPTest.class.getResourceAsStream("config.properties");//��ȡ�����ļ�
		
		BeanFactory beanFactory = new BeanFactory(inputStream);
		
//		ArrayList arrayList = (ArrayList) beanFactory.getBean("javalist");
//		arrayList.add("nihao");
		
		Collection arrayList = (Collection) beanFactory.getBean("proxylist");
		arrayList.add("Mercedes Benz s500");
		
		System.out.println(arrayList.size());
		System.out.println(arrayList);
		
	}

}
