package com.me.JavaWork.learn.Enum;


public class TestWeekDay {
	public static void main(String[] args) {
//		WeekDay1 day = WeekDay1.FRI;
//		System.out.println("day.nextDay():"+day.nextDay());
//		WeekDay day2 = WeekDay.FRI;
//		System.out.println("day2:"+day2);
//		System.out.println("day2.name():"+day2.name());
//		System.out.println("day2.ordinal():"+day2.ordinal());
//		System.out.println("day2.valueOf(\"SUN\").toString():"+day2.valueOf("SUN").toString());
//		System.out.println("day2.values().length:"+day2.values().length);
//		
		TrafficeLamp lamp = TrafficeLamp.RED;
		
		System.out.println(lamp + "灯" + lamp.lampTime() + "秒");
		System.out.println(lamp + "灯的下一个灯" + lamp.nextLamp() + "灯，"+lamp.nextLamp().lampTime()+"秒");
		
	}
	public enum WeekDay{
		//枚举中的每一个实例都会在类初始化的时候全部实例化。也就是说，有多少个枚举，就会调用多少次构造函数
		SUN(1),MON(),TUE,WED,THI,FRI,STA;
		
		//这个构造方法是针对没有参数的枚举
		private WeekDay(){
			System.out.println("first");
		}
		//这个构造方法是针对有参数的枚举
		private WeekDay(int day){
			System.out.println("second:"+day);
		}
	}
	
	public enum TrafficeLamp{
		//每个元素都是TrafficeLamp的子类。既然是子类，那么就要实现父类的抽象方法。
		RED(35) {
			@Override
			public TrafficeLamp nextLamp() {
				return GREEN;
			}

			@Override
			public int lampTime() {
				return this.getTime();
			}
		},GREEN(30) {
			@Override
			public TrafficeLamp nextLamp() {
				return YELLOW;
			}

			@Override
			public int lampTime() {
				return this.getTime();
			}
		},YELLOW(5) {
			@Override
			public TrafficeLamp nextLamp() {
				return RED;
			}

			@Override
			public int lampTime() {
				return this.getTime();
			}
		};
		
		//定义下一个灯的抽象方法，返回类型为TrafficeLamp。
		public abstract TrafficeLamp nextLamp();
		//定义灯的时间，
		public abstract int lampTime();
		private int time;
		private TrafficeLamp(int time){
			this.time = time;
		}
		public int getTime() {
			return time;
		}
		public void setTime(int time) {
			this.time = time;
		};
		
		
	}
}
