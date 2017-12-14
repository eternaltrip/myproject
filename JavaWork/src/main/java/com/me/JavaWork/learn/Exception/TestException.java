package com.me.JavaWork.learn.Exception;

public class TestException {
	public static void main(String[] args) {
		TestException exception = new TestException();
		exception.test();
		System.out.println("over");
	}
	
	
	public int test(){
		try {
			int a = 1;
			if(a == 2){
				System.out.println("a:2");
			}else{
				throw new Exception("a is not 2");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return 222222;
		}
		
		return 111111;
	}

}
