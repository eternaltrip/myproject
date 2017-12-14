package com.me.JavaWork.learn.string;

public class StringCharTest {
	public static void main(String[] args) {
		String info = "asfgaegalkjlgbmklgmalskmf";
		char temp = 'a';
		char tempb = info.charAt(3);
		if(temp - tempb > 0){
			//这里说明temp是在tempb之后，char是可以直接进行加减运算的，通过结果来判断是在前还是在后，或者相等
		}else if(temp - tempb == 0){
			
		}else{
			
		}
		temp = info.charAt(3);
		System.out.println('A'-'a');
		
		
	}

}
