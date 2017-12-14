package com.me.JavaWork.learn.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTimes {
	public static void main(String[] args) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date date1 = dateFormat.parse("2016-06-06 09:12");
			Date date2 = new Date();
			System.out.println((date2.getTime() - date1.getTime()) / 60000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Date time = null;
		try {
			time = new Date();
			time = dateFormat.parse("2012-02-23 16:45");
			System.out.println(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
