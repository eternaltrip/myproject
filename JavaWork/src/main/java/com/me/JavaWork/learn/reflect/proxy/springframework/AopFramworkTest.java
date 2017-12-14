package com.me.JavaWork.learn.reflect.proxy.springframework;

import java.io.InputStream;
import java.util.Collection;

public class AopFramworkTest {
	public static void main(String[] args) {
		
		InputStream inStream = AopFramworkTest.class.getResourceAsStream("config.properties");
		BeanFactory beanFactory = new BeanFactory(inStream);

		Collection collection = (Collection) beanFactory.getBean("xxx");
		
		collection.add("adv");
		System.out.println(collection.size());
	}
	

}
