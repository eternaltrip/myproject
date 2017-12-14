package com.me.JavaWork.learn.string;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class StringTest {
	private static  String a ;
	private static  String b ;
	
	public static void main(String[] args) {
		String type =null;
		String  aa = "123";
		String bb = "123";
		System.out.println(aa==bb);
		String ab = new String("hello");
		String ba = new String("hello");
		System.out.println(ab==ba);
		type = aa;
		System.out.println(type);
		
		String ipIndex = "10.41.";
		String ip = "25.10.41.25.36";
		System.out.println("ip:"+ip.indexOf(ipIndex));
		
		
		
		
		String completeTree  = "ear顾问化工网融合温瑞塘河如同雨后日";
		String[] list = completeTree.split("��");
		System.out.println(list[0]);
		
		
		 ArrayList arrayList = new ArrayList<>();
		 StringTest stringTest = new StringTest();
		
//		String key = "1";
//		String key2 = "";
//		String key3 = "1";
//		String key4 = "";
//		a = key + key2;
//		String c = key3 + key4;
//		arrayList.add(a);
//		arrayList.add(c);
//		b = "1" + "";
//		System.out.println(a==b);
//		System.out.println(a.equals(c));
		 double a = Math.random();
		 System.out.println(a);
		 Random random = new Random();
		 
		 System.out.println(random.nextInt(2));
		 
		 StringBuffer a1 = new StringBuffer();
		 for (int i = 0; i < 6; i++) {
			 int rom = random.nextInt(26);
			 if(rom < 13){
				 rom += 97;
			 }else{
				 rom += 65; 
			 }
			 a1.append((char)rom);
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssms");
		
		
		int rom = random.nextInt(10);
		System.out.println( Long.parseLong(dateFormat.format(new Date()) + rom));
		
		String adc =  null;
	//	System.out.println(adc.toString() + "");
		
		String filepath = "file:/D:/eclipseWorkSpace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/IMAS/WEB-INF/classes/page/brasInfo/218.200.238.248_2017-05-27_2017-05-26.html";
		System.out.println(filepath.substring(filepath.indexOf("file:/")));
	}

}
