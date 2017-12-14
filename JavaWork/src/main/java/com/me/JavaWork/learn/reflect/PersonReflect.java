package com.me.JavaWork.learn.reflect;

import java.util.Iterator;

public class PersonReflect {
	public String name;
	private int age;
	public String add1 = "tian3f5uda1dao";
	public String add2 = "tian57f8ue43rjie";
	/**
	 * @param name
	 * @param age
	 */
	public PersonReflect(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public static void main(String[] args){
		for (String string : args) {
			System.out.println(string);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonReflect other = (PersonReflect) obj;
		if (age != other.age)
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
}
