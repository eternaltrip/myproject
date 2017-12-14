package com.me.JavaWork.learn.reflect.proxy.springframework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.me.JavaWork.learn.reflect.proxy.Advice;



public class BeanFactory {
	Properties properties = new Properties();
	
	public BeanFactory(InputStream inputStream) {
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public Object getBean(String name){
		String className = properties.getProperty(name);
		Object bean = null;
		try {
			Class clazz = Class.forName(className);
			bean = clazz.newInstance();
			
			if(bean instanceof ProxyFactoryBean){
				String beanAdvice = properties.getProperty(name + ".advice");
				String beanTarget = properties.getProperty(name + ".target");
				
				Advice advice = (Advice) Class.forName(beanAdvice).newInstance();
				Object target = Class.forName(beanTarget).newInstance();
				
				ProxyFactoryBean proxyBean = new ProxyFactoryBean();
				proxyBean.setAdvice(advice);
				proxyBean.setTarget(target);
				bean = proxyBean.getProxy();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

}
