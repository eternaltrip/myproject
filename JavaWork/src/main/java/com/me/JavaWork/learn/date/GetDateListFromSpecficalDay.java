package com.me.JavaWork.learn.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GetDateListFromSpecficalDay {
	long oneDay = 86400000l;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	public static void main(String[] args) {
		Date today =  new Date();
		GetDateListFromSpecficalDay dateListFromSpecficalDay = new GetDateListFromSpecficalDay();
		Map<String,Integer> map = dateListFromSpecficalDay.getDateListFromAppointDate(today, 12);
		System.out.println(map);
	}
	
	
	
	
	public Map<String, Integer> getDateListFromAppointDate(Date appointDay , Integer dayLength ){
		Map<String, Integer> map = new HashMap<>();
		
		int length = Math.abs(dayLength);
		
		for (int i = 0; i < length; i++) {
			String date = "";
			if(dayLength > 0){
				date =format.format(new Date(appointDay.getTime() + oneDay * i));
			}else{
				date =format.format(new Date(appointDay.getTime() - oneDay * i));
			}
			map.put(date, null);
		}
		return map;
	}

}
