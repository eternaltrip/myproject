package com.me.JavaWork.learn.Collection;

import java.util.HashMap;
import java.util.Hashtable;

public class HashTableTest {
	public static void main(String[] args) {
		
		
		HashTableAdviceBean adviceBean = new HashTableAdviceBean();
		
		//
		/**
		 * HashTable和HashMap 在使用自定义类作为key的时候，自定义类必须重写queals方法和hashCode方法。
		 * HashMap中，如果要比较key是否相等，要同时使用这两个函数！
		 * 因为自定义的类的hashcode()方法继承于Object类，其hashcode码为默认的内存地 址，这样即便有相同含义的两个对象，比较也是不相等的，例如，
			Student st1 = new Student("wei","man");
			Student st2 = new Student("wei","man");
			st1 st2 是创建了两个对象存入在不同的内存地址中。内容虽然相等，但不是同一个对象。
			正常理解这两个对象再存入到hashMap中应该是相等的，但如果你不重写 hashcode（）方法的话，比较是其地址，不相等！
			HashMap中的比较key是这样的，先求出key的hashcode(),比较其值是否相等，若相等再比较equals(),若相等则认为他们是相等 的。
			若equals()不相等则认为他们不相等。
			如果只重写hashcode()不重写equals()方法，当比较equals()时只是看他们是否为 同一对象（即进行内存地址的比较）,
			所以必定要两个方法一起重写。
			HashMap用来判断key是否相等的方法，其实是调用了HashSet判断加入元素 是否相等
		 * */

		Hashtable<Object, Integer> hashtable = new Hashtable<>();
		
		
		hashtable.put("A", 123);
		hashtable.put("b", 123);
		hashtable.put("c", 123);
		hashtable.put("d", 123);
		hashtable.put(adviceBean, 123);
		
		
		HashMap<Object, Integer> hashMap = new HashMap<>();
		hashMap.put(adviceBean, 1);
		
		System.out.println(hashtable.contains("A"));
		System.out.println(hashtable.containsKey("A"));
		System.out.println(hashtable.containsValue(123));
		System.out.println(hashtable.entrySet());
		
	}
	
	
	

}
