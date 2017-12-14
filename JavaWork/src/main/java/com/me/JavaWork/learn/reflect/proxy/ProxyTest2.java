package com.me.JavaWork.learn.reflect.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;


/**
 * 自实现代理类
 * **/
public class ProxyTest2 {
	public static void main(String[] args) throws Exception {
		/**
		 * 对collection的代理
		 * 1.通过代理类生成collection的代理对象proxyClass
		 * 2.使用代理对象proxyClass获取构造方法(如果不知道构造方法的具体参数形式，就必须先查看其构造方法的参数形式)
		 * 3.使用构造方法进行实例化（实例化的时候，要注意参数的传入形式，以及一些接口类的实现）
		 */
		
		Class proxyClass = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
		Constructor[] constructors= proxyClass.getConstructors();
		for (Constructor constructor2 : constructors) {
			System.out.println(constructor2);
		}
		
		Constructor constructor = proxyClass.getConstructor(InvocationHandler.class);
		
		class MyInvocationHandler implements InvocationHandler{
			ArrayList arrayList = new ArrayList();
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return method.invoke(arrayList, args);
			}
		}; 
		
		Collection collection = (Collection) constructor.newInstance(new MyInvocationHandler());
		
		collection.add("ad");
		
		System.out.println(collection);
		
		ArrayList arrayList = new ArrayList();
		Advice advice = new MyAdvice();
		Collection collection2 = (Collection) getProxy(arrayList,advice);
		collection2.add("ad");
		System.out.println(collection2.size());
		
	}

	private static Object getProxy(Object target,Advice advice) {
		Object object = Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), 
				new InvocationHandler(){
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				advice.beforeMethod(method);
				
				Object retVal = method.invoke(target, args);
				
				advice.afterMethod(method);
				return retVal;
			}
		}
		);
		return object;
	}
	
}
