package com.me.JavaWork.learn.reflect;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 内省操作
 * @author yangin
 *
 */
public class IntrospectorTest {

	public static void main(String[] args) {
		//内省操作
		
		try {
			PersonReflect personReflect = new PersonReflect("alex", 19);
			String properptyName = "name";
			String properptyAge = "age";

			
			Object returnAgeVal = getProperty(personReflect, properptyAge);
			System.out.println( returnAgeVal);
			
			Object returnNameVal = getProperty(personReflect, properptyName);
			System.out.println( returnNameVal);
			
		
			
			System.out.println( "修改值之后的属性");
			setProperty(personReflect, properptyName, "john");
			System.out.println( personReflect.getName());
			
			//使用beanutils来处理bean的属性
			BeanUtils beanUtils = new BeanUtils();
			beanUtils.setProperty(personReflect,properptyName,"wishle");
			Object obj = beanUtils.getProperty(personReflect, properptyName);
			System.out.println("使用beanutils获取属性" + obj);
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static Object getProperty(Object obj, String properpty) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		PropertyDescriptor descriptor = new PropertyDescriptor(properpty, obj.getClass());
		Method methodGet = descriptor.getReadMethod();
		Object returnVal = methodGet.invoke(obj);
		return returnVal;
	}
	
	private static Object setProperty(Object obj, String properpty, Object val) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		PropertyDescriptor descriptor = new PropertyDescriptor(properpty, obj.getClass());
		Method methodGet = descriptor.getWriteMethod();
		Object returnVal = methodGet.invoke(obj,val);
		return returnVal;
	}
}
