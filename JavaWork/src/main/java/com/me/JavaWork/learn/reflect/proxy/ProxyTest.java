package com.me.JavaWork.learn.reflect.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;

//代理类
public class ProxyTest {
	public static void main(String[] args) throws Exception {
		
		ProxyTest proxyTest = new ProxyTest();
		
		Class clazzProxy1 = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
		System.out.println(clazzProxy1.getName());
		System.out.println("-----获取构造方法列表-----");
		
		
		//获取构造方法
		Constructor[] constructors = clazzProxy1.getConstructors();
		for (Constructor constructor : constructors) {
			StringBuilder builder = new StringBuilder();//与stringbuffer的区别是，stringbuffer是需要考虑线程安全的，而这个不需要。所以单线程下效率会高那么一点。
			builder.append(constructor.getName());
			builder.append("(");
			Class[] clazzParams = constructor.getParameterTypes();
			for (Class class1 : clazzParams) {
				builder.append(class1.getName()).append(",");
			}
			if(clazzParams != null && clazzParams.length != 0){
				builder.deleteCharAt(builder.length() - 1);
			}
			builder.append(")");
			System.out.println(builder);
		}
		
		
		System.out.println("-----获取方法列表-----");
		//获取方法列表
		Method[] methods = clazzProxy1.getMethods();
		for (Method method : methods) {
			StringBuilder builder = new StringBuilder();//与stringbuffer的区别是，stringbuffer是需要考虑线程安全的，而这个不需要。所以单线程下效率会高那么一点。
			builder.append(method.getName());
			builder.append("(");
			Class[] clazzParams = method.getParameterTypes();
			for (Class class1 : clazzParams) {
				builder.append(class1.getName()).append(",");
			}
			if(clazzParams != null && clazzParams.length != 0){
				builder.deleteCharAt(builder.length() - 1);
			}
			builder.append(")");
			System.out.println(builder);
		}
		
		System.out.println("-----创建实例对象-----");
		
		
		
		//说明，实例化构造方法的时候，（1）参数类型是一个具体的类型字节码，而不是类型的实例。
		//（2）通过构造方法进行实例化具体类的时候，参数是一个实例化的类。
		
		
		//实现方式一
		System.out.println("实现方式一：");
		
		//自定义一个类，来实现InvocationHandler类。
		class MyInvocationHandler1 implements InvocationHandler{

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return null;
			}
		}
		
		
		Constructor constructor1 = clazzProxy1.getConstructor(InvocationHandler.class);//1参数： java.lang.reflect.InvocationHandler
		
		Collection proxy1 = (Collection) constructor1.newInstance(new MyInvocationHandler1());//2.传入一个实体类，然后进行实例化
		
		System.out.println(proxy1);
		proxy1.clear();
		//proxy1.size();//这里使用带有返回值的方法报错。
		
		
		//实现方式二
		System.out.println("实现方式二：");
		Constructor constructor2 = clazzProxy1.getConstructor(InvocationHandler.class);//1参数： java.lang.reflect.InvocationHandler
		//这里直接使用匿名内部类就行实现
		Collection proxy2 = (Collection) constructor2.newInstance(new InvocationHandler(){

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return null;
			}
			
		});
		System.out.println(proxy2);
		proxy2.clear();
		//proxy2.size();//这里使用带有返回值的方法报错。因为，这里使用size()方法返回的是一个int类型的数据；然而在调用size()方法时，内部的invoke方法，返回的null。这里就无法转化，所以出错
		
		
		
		//实现方式三
		System.out.println("实现方式三：");
		Collection  proxy3 = (Collection) Proxy.newProxyInstance(
				Collection.class.getClassLoader(),
				new Class[]{Collection.class},
				new InvocationHandler()/**这里的类是一个匿名内部类*/{
					ArrayList arrayList1 = new ArrayList();//这个对象是在类中的对象。
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						//System.out.println(proxy.getClass().getName());
						//ArrayList arrayList2 = new ArrayList();//这个对象是在方法中的对象。
						Object retVal = method.invoke(arrayList1, args);//进行执行
						return retVal;
					}
				}
		);
		proxy3.add("a");
		int size = proxy3.size();
		System.out.println(size);
		System.out.println(proxy3.getClass().getName());
		System.out.println(proxy3.toString());//
		//注意:Object的方法中，只有equals、hashCode、toString这三个方法交给了InvocationHandler。其他的方法没有交给它，
		//所以执行其他的方法（如getClass）时，仍然返回object的方法的返回值，而不是Invoke的返回值。
		
		//抽象方法之后
		ArrayList target = new ArrayList();
		Collection collection = (Collection)proxyTest.getProxy(target , new MyAdvice());
		collection.add("123");
		collection.add("abc");
		System.out.println(collection.size());
		//System.out.println(collection);
	}
	
	//抽象一下方式三
	public Object getProxy(Object target , Advice advice){
		Object object =  Proxy.newProxyInstance(
				target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				new InvocationHandler()/**这里的类是一个匿名内部类*/{
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						
						advice.beforeMethod(method);
						Object retVal = method.invoke(target, args);//进行执行
						advice.afterMethod(method);
						return retVal;
					}
				}
		);
		return object;
	}

}
