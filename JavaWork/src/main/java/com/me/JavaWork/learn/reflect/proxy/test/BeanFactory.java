package com.me.JavaWork.learn.reflect.proxy.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.me.JavaWork.learn.reflect.proxy.Advice;


public class BeanFactory {
	Properties properties = new Properties();

	public BeanFactory(InputStream inputStream) {
		super();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public Object getBean(String beanName){
		Object retVal = null;
		
		try {
			String className = properties.getProperty(beanName);//��ȡbeanName��className��
			
			retVal = Class.forName(className).newInstance();//ʵ����className
			
			if(retVal instanceof ProxyFactoryBean ){
				String adciveBean = properties.getProperty(beanName + ".advice");
				String targetBean = properties.getProperty(beanName + ".target");
				Advice advice = (Advice) Class.forName(adciveBean).newInstance();
				Object target = Class.forName(targetBean).newInstance();
				
				ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
				proxyFactoryBean.setAdvice(advice);
				proxyFactoryBean.setTarget(target);
				retVal = proxyFactoryBean.getProxyBean();
			}
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		return retVal; 
	}
	
	
	

}
