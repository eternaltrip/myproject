package com.me.JavaWork.jdk1_8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lambdas {

	static List<String> names = Arrays.asList("peter", "anna", "mike", "xenia", "aeli");

	public static void main(String[] args) {
		Lambdas lambdas = new Lambdas();
		// lambdas.defaultFun();
		// lambdas.lambdasFun();
		lambdas.testCompareTo();

	}

	// 传统方法
	public void defaultFun() {
		System.out.println("传统方式：");
		Collections.sort(names, new Comparator<String>() {// java以前老版本的写法
			@Override
			public int compare(String a, String b) {
				return a.compareTo(b);
			}
		});
		for (String name : names) {
			System.out.println(name);
		}
		System.out.println("\n");
	}

	// 使用lambdas
	public void lambdasFun() {
		System.out.println("使用labmdas：");
		Collections.sort(names, (String a, String b) -> {
			return a.compareTo(b);
		});
		for (String name : names) {
			System.out.println(name);
		}
		System.out.println("\n");

	}

	public void testCompareTo() {
		String a = "2";
		String b = "11";
		Integer aa = 1;
		Integer bb = 1;
		Integer cc = 0;
		Integer dd = 4;
		System.out.println(aa.compareTo(bb));
		System.out.println(aa.compareTo(cc));
		System.out.println(aa.compareTo(dd));

	}

}
