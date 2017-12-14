package com.me.JavaWork.learn.Class;

public class InstanceOfTest {
	
	
	public static void main(String[] args) {
		A a = null;
				
		B b = null;
		
		a = new B();
		b = new B();
		System.out.println(a instanceof B);
		System.out.println(b instanceof B);
		System.out.println(a instanceof A);
		System.out.println(b instanceof A);
	}
	

}


interface A {}
class B implements A{}