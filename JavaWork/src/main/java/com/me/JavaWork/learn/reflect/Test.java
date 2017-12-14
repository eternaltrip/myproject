package com.me.JavaWork.learn.reflect;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

public class Test {
	public static void main(String[] args) {
		
		
		//利用反射调用方法
		try {
			String sbc = (String)Class.forName("java.lang.String").newInstance();
			sbc = "abc";
			System.out.println(sbc);
			
			Method method = sbc.getClass().getMethod("charAt", int.class);
			System.out.println("methodReflect:"+method.invoke(sbc, 2));
			  
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//利用反射
		try {
			Constructor constructor = /*String.class*/Class.forName("java.lang.String").getConstructor(StringBuffer.class ) ;
			String str = (String) constructor.newInstance(new StringBuffer().append("aabbcc") );
			
			System.out.println(str);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//反射成员变量
		try {
			PersonReflect personReflect = new PersonReflect("alex", 19);
			
			Field field1 = personReflect.getClass().getField("name");//获取成员变量属性
			String name = field1.get(personReflect).toString();//获取对象“personReflect”中属性的值
			System.out.println(name); 
			
			Field field = personReflect.getClass().getDeclaredField("age");//获取成员变量的私有属性
			if(!field.isAccessible()){
				System.out.println(field.getName()+"为私有属性，设置暴力获取");//设置暴力获取
				field.setAccessible(true);
			}
			int age = field.getInt(personReflect);//获取对象“personReflect”中属性的值
			
			
			
			
			System.out.println(age); 
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//利用反射替换所有string中的某个字符
		try {
			
			PersonReflect personReflect = new PersonReflect("alex", 19);
			Field[] fields = personReflect.getClass().getFields();
			
			for (Field field : fields) {
				if(field.getType() == String.class){
					String oldValue = field.get(personReflect).toString();
					String newValue = oldValue.replaceAll("i", "I").replaceAll("\\d", "-");
					System.out.println(oldValue);
					
					//field.set(personReflect, newValue);//重新赋值
					System.out.println(newValue);
				}
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//利用反射调用对象方法
		try {
			Constructor constructor = Class.forName("com.reflect.PersonReflect").getConstructor(String.class,int.class);
			PersonReflect personReflect = (PersonReflect) constructor.newInstance("test",27);
			Method method = personReflect.getClass().getMethod("main",String[].class);
			//static方法不需要传递对象，所以为null
			method.invoke(null,(Object)new String[] {"yangjin","test","good"});
			
			//调用get方法，获取属性值
			String getName = "getName";
			method = personReflect.getClass().getMethod(getName);
			Object nameValue = method.invoke(personReflect);
			System.out.println(nameValue);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//利用反射调用配置文件。
		
		try {
			
			InputStream inputStream = new FileInputStream("src/com/regex/className.properties");//1
			
			inputStream = Test.class.getClassLoader().getResourceAsStream("com/regex/className.properties");//2 
			
			inputStream = Test.class.getResourceAsStream("/com/regex/className.properties");//3 
			
			Properties properties = new Properties();
			properties.load(inputStream);
			inputStream.close();
			String arrayList = properties.getProperty("arrayList");
			String hashSet = properties.getProperty("hashSet");
			
			Collection collectionList = (Collection)Class.forName(arrayList).newInstance();
			Collection collectionSet = (Collection)Class.forName(hashSet).newInstance();
			
			PersonReflect pr1 = new PersonReflect(null, 10);
			PersonReflect pr2 = new PersonReflect(null, 10);
			PersonReflect pr3 = new PersonReflect("test2", 10);
			PersonReflect pr4 = new PersonReflect("test2", 11);
			
			collectionList.add(pr1);
			collectionList.add(pr2);
			collectionList.add(pr3);
			collectionList.add(pr4);
			System.out.println(collectionList.size());
			
			collectionSet.add(pr1);
			collectionSet.add(pr2);
			collectionSet.add(pr3);
			collectionSet.add(pr4);
			System.out.println(collectionSet.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
}
