package com.me.JavaWork.learn.Collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SetTest {
	public static void main(String[] args) {
		
//		Set<Person> strset = new HashSet<>();
//		List<String> strs = new ArrayList<>();
//		Person person = new Person();
//		person.setAge(1);
//		person.setName("aa");
//		
//		Person person1 = new Person();
//		person1.setAge(1);
//		person1.setName("aa");
//		strset.add(person1);
//		strset.add(person);
//		
//		System.out.println(strset.size());
//		System.out.println(strs.size());
		
		Set<String> strset = new HashSet<>();
		strset.add("aaaaa");
		strset.add("bbbbb");
		Iterator<String> iterator = strset.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}

}
class Person{
	String name;
	int age;
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
