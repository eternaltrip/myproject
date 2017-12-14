package com.me.JavaWork.learn.Enum;

public abstract class WeekDay1 {
	private WeekDay1(){};
	
	public final static WeekDay1 SUN = new WeekDay1(){

		@Override
		public WeekDay1 nextDay() {
			return MON;
		}};
	
	public final static WeekDay1 MON = new WeekDay1(){

		@Override
		public WeekDay1 nextDay() {
			return TUE;
		}};
	
	public final static WeekDay1 TUE = new WeekDay1(){

		@Override
		public WeekDay1 nextDay() {
			return WED;
		}};
	
	public final static WeekDay1 WED = new WeekDay1(){

		@Override
		public WeekDay1 nextDay() {
			return THU;
		}};
	
	public final static WeekDay1 THU = new WeekDay1(){

		@Override
		public WeekDay1 nextDay() {
			return FRI;
		}};
	
	public final static WeekDay1 FRI = new WeekDay1(){

		@Override
		public WeekDay1 nextDay() {
			return SAT;
		}};
	
	public final static WeekDay1 SAT = new WeekDay1(){

		@Override
		public WeekDay1 nextDay() {
			return SUN;
		}};
	
	public abstract WeekDay1 nextDay();
	
	public String toString(){
		WeekDay1 day = this;
		if(day == MON){
			return "MON";
		}else if(day == TUE){
			return "TUE";
		}else if(day == WED){
			return "WED";
		}else if(day == THU){
			return "THU";
		}else if(day == FRI){
			return "FRI";
		}else if(day == SAT){
			return "SAT";
		}else{
			return "SUN";
		}
	}
	
}




