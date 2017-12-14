package com.me.JavaWork.learn.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TextInteger {
	
	
	public static void main(String[] args) {
		int num = 50000;
		double  doublenum= 6;
		System.out.println(Math.ceil( 6) );
		
		
		int a = 1;
		System.out.println(1+a++);
		System.out.println(a);
		
		int b = 2;
		System.out.println(1+ ++b);
		System.out.println(b);
		System.out.println(Double.parseDouble(0.5632 + "") * 80.0
				);
		BigDecimal bd2 = new BigDecimal(80 + ""); 
		BigDecimal bd1 = new BigDecimal( 0.5632 +""); 
		System.out.println(bd1.multiply(bd2).doubleValue() + bd1.multiply(bd2).doubleValue());
		
		TextInteger integer= new TextInteger();
		System.out.println(integer.div(15.69, 0, 3));
		
//		int[] a ={1,2,3,4,7,9,13,14,15,16,18,19,21};
//		int b = a[0];
//		StringBuffer c = new StringBuffer();
//		c.append(a[0]);
//		for (int i = 0; i < a.length; i++) {
//			if(a[i] != b){
//				if(a[i-1] == (a[i-2] + 1)){
//					c.append("-"+a[i-1]);
//				}
//				c.append(","+a[i]);
//				b = a[i];
//			}
//			if(a[a.length-1] == b && a[a.length - 1] == (a[a.length - 2] + 1)){
//				c.append("-"+a[a.length-1]);
//			}
//			b++;
//		}
//		System.out.println(c);
//		
	}
	
	
	
	public double div(double d1, double d2, int scale) {
		

		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
