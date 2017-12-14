package com.me.JavaWork.learn.reflect.generics;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

public class GenericTest {
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		//使用泛型,原理是限制集合中的元素类型，可以免去对象的转换。
		//而泛型只是在编译时起作用，而在编译之后，就会去掉类型信息，以提高运行效率
		//所以，如果在编译之后使用反射来添加信息到原集合中，就可以添加任何类型的数据。这样就跳过了编译器的数据类型限制
		Constructor<String> constructor = String.class.getConstructor(StringBuffer.class ) ;
		String str = constructor.newInstance(new StringBuffer().append("aabbcc") );
		
		System.out.println(str);
		//testAll();
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add("abc");
		arrayList.add("def");
		String[] strings = {"a","b","c"};
		copyArrayToCollect2(arrayList , strings);
		System.out.println(arrayList);
		copyArrayToCollect(arrayList , strings);
		System.out.println(arrayList);
		
		Object a = "abc";
		String abc = autoConvert(a);
		
		getGenericType();
	}
	
	
	public <E> void test1(){
		/**
		 *  E就代表使用了泛型
		 *  ArrayList<E> 泛型类型, E 类型变量或类型参数,这里的<>中的字母是可以随意写的，只不过为什么阅读性，我们一般会用特定的字母表示
		 *  E 表示element ，T 表示type。所以E一般用于集合中。T 一般用于方法体中的返回类型
		 *  ArrayList<String> 参数化类型,String 实际类型参数（参数类型的实例）
		 *  <>是typeof的意思 ，ArrayList是原始类型
		 *  
		 */
		ArrayList<String> listStr = new ArrayList<>();
		ArrayList<E> list = new ArrayList<>();
		ArrayList<T> list = new ArrayList<>();
		
		
		//参数化类型可以引用一个原始类型，一个原始类型也可以引用一个参数化类型
		Collection<String> c = new Vector();
		Collection c1 = new Vector<String>();
		
		//参数化类型不能有继承关系
		Collection<String> c2 = new Vector<Object>();
		Collection<Object> c3 = new Vector<String>();
		Collection<Object> c4 = new Vector<Object>();
		
		//数组的元素不能使用参数化的类型
		Vector<Integer> vectorList[] = new Vector<Integer>[10];
		Vector vectorList1[] = new Vector[10];
		
		
	}
	
	//使用 ？ 通配符，就代表任何数据类型
	public static void test2(Collection<?> collection){
		//通用参数类型不能调用add()，防止与其本身的数据类型不一致
		//collection.add("12");
		for (Object object : collection) {
			System.out.println(object);
		}
		collection.add("Stirng");//错误。
		collection = new HashSet<Date>();//这里是通配符，可以用继承关系，这里可以引用任何类型
	}
	
	//使用参数化类型
	public static void test3(Collection<Object> collection){
		//通用参数类型不能调用add放，防止与其本身的数据类型不一致
		//collection.add("12");
		for (Object object : collection) {
			System.out.println(object);
		}
		collection.add("Stirng");//这里就没错了。因为这里使用了参数化类型
		collection = new HashSet<Date>();//这里又违反了参数化类型不能有继承关系的规则。
	}
	
	//使用通配符 ? 还可以限制数据类型的范围。
	public static void test4(){
		//限定通配符的上边界
		ArrayList<? extends Number> collection = new ArrayList<Integer>();
		//限定通配符的下边界
		ArrayList<? super Integer> collection1 = new ArrayList<Number>();
		
	}
	
	
	//泛型综合测试
	public static void testAll(){
		HashMap<String, Integer> maps = new HashMap<String, Integer>();
		maps.put("a", 10);
		maps.put("b", 20);
		maps.put("c", 30);
		maps.put("d", 40);
		
		Set<Map.Entry<String, Integer>> entrySet = maps.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		
	}
	
	/**
	 * 自定义类型 T,把一个数组中的元素复制到一个集合中。使用T就规定了，a和b中的数据类型必须一致。
	 * */
	public static <T> Collection<T> copyArrayToCollect(Collection<T> a , T[] b){
		for (T t : b) {
			a.add(t);
		}
		return a;
	}

	public static <E> ArrayList<E> copyArrayToCollect2(ArrayList<E> a , E[] b){
		for (E t : b) {
			a.add(t);
		}
		return a;
	}
	
	/**
	 * 转化对象
	 * */
	public static <T> T autoConvert(Object obj){
		
		return (T)obj;
	}
	
	
	/**
	 * 泛型的参数类型，
	 * 通过反射是不能直接获取泛型的参数类型的。
	 * 只能通过方法的传递，来获取泛型的参数类型。
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * 
	 */
	public static void getGenericType() throws NoSuchMethodException, SecurityException{
		Vector<String> strings = new Vector<String>();
		Method method = GenericTest.class.getMethod("testGenericType", strings.getClass());
		Type[] types = method.getGenericParameterTypes();
		ParameterizedType parameterizedType = (ParameterizedType) types[0];
		System.out.println("没有指定了泛型的参数类型就无法获取到参数类型。");
		System.out.println(parameterizedType.getRawType());
		System.out.println(parameterizedType.getActualTypeArguments()[0]);
		
		System.out.println("指定了参数类型的泛型可以获取到参数类型。");
		method = GenericTest.class.getMethod("testGenericType2", strings.getClass());
		types = method.getGenericParameterTypes();
		parameterizedType = (ParameterizedType) types[0];
		System.out.println(parameterizedType.getRawType());
		System.out.println(parameterizedType.getActualTypeArguments()[0]);
		
	}
	
	//如果这里定义的参数时没有指定泛型的参数类型。那么是无法获取的。因为参数类型只是在编译是发挥作用。真正运行时为了保证运行效率，是把参数类型去掉了的。
	public static <T> void testGenericType(Vector<T> vector){
		System.out.println(vector.getClass().getTypeParameters()[0]);
	}
	//所以如果泛型的参数类型制定了，就能获取到参数类型
	public static void testGenericType2(Vector<String> vector){
	}
	
}
