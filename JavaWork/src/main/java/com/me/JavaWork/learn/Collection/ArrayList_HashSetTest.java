package com.me.JavaWork.learn.Collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.me.JavaWork.learn.reflect.PersonReflect;


public class ArrayList_HashSetTest {
	
	public static void main(String[] args) {
		
		Collection collection1 = new ArrayList();
		
		/**
		 * 关于hashset
		 * hashset中的值对应了hashcode。每个hashcode在内存中是唯一对应的。所以使用hashset可以快速的查找数据。假如有上千万条数据，使用hashset来查找，也就是很快的事情
		 * hashcode是对象建立的时候就存在的。如果把对象放入hashset之后，再对其中的对象就行修改，那么hashcode也就随之改变。
		 * 但hashset还是记录的原来的hashcode。所以这个时候如果进行取出操作就会失败，并且，内存中的对象还是存在，并没有被释放，这里就造成了内存泄漏
		 */
		Collection collection = new HashSet<>();
		
		PersonReflect pr1 = new PersonReflect(null, 10);
		PersonReflect pr2 = new PersonReflect(null, 10);
		PersonReflect pr3 = new PersonReflect("test2", 10);
		PersonReflect pr4 = new PersonReflect("test2", 11);
		
		collection1.add(pr1);
		collection1.add(pr2);
		collection1.add(pr3);
		collection1.add(pr4);
		System.out.println(collection1.size());
		
		collection.add(pr1);
		collection.add(pr2);
		collection.add(pr3);
		collection.add(pr4);
		System.out.println(collection.size());
		
		
		
		List<String> abs = new ArrayList<>();
		abs.add("a");
		abs.add("b");
		abs.add("c");
		abs.add("d");
		System.out.println(abs.toString());
	}
	
	
	

}
