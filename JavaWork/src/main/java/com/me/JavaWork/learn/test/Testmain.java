package com.me.JavaWork.learn.test;

import java.text.DecimalFormat;

public class Testmain {
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("######0.000");
		double a = -0.19299999999999998;
		int b = Integer.parseInt(5*a + ""      );
		System.out.println(b);
	}

}
