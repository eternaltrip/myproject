package com.me.JavaWork.learn.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCalendar {
	public static void main(String[] args) throws ParseException{
		
		
	List<String> dates = new ArrayList<>();
	dates.add("2016-1-5");
	dates.add("2016-01-6");
	dates.add("2016-1-8");
	dates.add("2016-1-9");
	dates.add("2016-01-10");
	dates.add("2016-1-11");
	dates.add("2016-1-07");
	dates.add("2015-12-31");
	dates.add("2015-12-30");
	dates.add("2015-12-29");
	dates.add("2015-12-28");
	dates.add("2015-12-27");
	dates.add("2015-12-26");
	TestCalendar calendar = new TestCalendar();
	System.out.println(calendar.checkIfIPOfflineLastedManyDay(dates, 3));
	 
	}
	

public int checkIfIPOfflineLastedManyDay(List times ,int offlineDays ) throws ParseException{
		
	/**
	 * 将列表进行排序，然后倒序循环，每次取出三个日期day1，day2，day3，判断这三个日期是否连续。如果连续就继续下一次循环。
	 * 第二次循环开始，把上一次循环的day1作为本次循环的day3。然后判断是否连续，如果连续就继续，直到循环结束，如果最后循环的时候，只有一个日期，那么就直接比较这个日期是否和上一次循环中day3连续。
	 * 每次循环中如果出现不连续的，就把状态码（默认为true）设置为false。
	 * 注：如果当天的日期在日期列中未出现，就直接返回false。
	 * 
	 * */
	

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	List<Date> timeList = new ArrayList<>();
	for (int i = 0; i < times.size(); i++) {
		timeList.add(dateFormat.parse(times.get(i).toString()));
	}
	
	Collections.sort(timeList);
	
	int count = 0;//离线天数统计、
	String lastDate = dateFormat.format(timeList.get(timeList.size()-1));
	String today = dateFormat.format(new Date());
	if(lastDate.equals(today)){
		Date day1 = null;//前一天日期
		Date day2 = null;//中间日期，这里初始化为今天
		Date day3= null;//后一天日期
		long oneDay = 86400000l;//一天的时间（豪秒数）
		for (int i = timeList.size() - 2; i >= 0; i--) {
			
			try {
				if(i == 0){
					//当day2的取值索引为0时，就不存在day1.
					day1 = timeList.get(i);//
				}else{
					day1 = timeList.get(i - 1);//获取前一天日期
				}
				day2 = timeList.get(i);//获取中间日期
				day3 = timeList.get(i + 1);//获取后一天日期
				
				long time1 = (day3.getTime() - day2.getTime()) / oneDay;
				long time2 = (day2.getTime() - day1.getTime()) / oneDay;
				
				if(time1 == 1){
					//第一次循环的时候，是三个日期第一次出现，所以这里count+1
					if(i == timeList.size() - 2){
						count += 1;
					}
					count += 1;
					
				}else{
					break;
				}
				
				if(time2 == 1){
					count += 1;
				}else{
					break;
				}
				
				if(time1 == 1 && time2 == 1){
					i -= 1;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(count - offlineDays >= 0){
			return count;
		}else{
			return -1;
		}
	}else{
		return -1;
	}
}
}

