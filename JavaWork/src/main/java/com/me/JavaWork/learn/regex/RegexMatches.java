package com.me.JavaWork.learn.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {
	 public static void main( String args[] ){
		 RegexMatches matches = new RegexMatches();
		 matches.replace1();
	
	   }
	
	/**
	 * matches 和 lookingAt 方法都用来尝试匹配一个输入序列模式。它们的不同是 matcher 要求整个序列都匹配，而lookingAt 不要求。
	 */
	public void matches_lookingAt(){
		    String REGEX = "foo";
	       String INPUT = "fooooooooooooooooo";
	      Pattern pattern;
	      Matcher matcher;
		 
	       pattern = Pattern.compile(REGEX);
	       matcher = pattern.matcher(INPUT);
	 
	       System.out.println("Current REGEX is: "+REGEX);
	       System.out.println("Current INPUT is: "+INPUT);
	 
	       System.out.println("lookingAt(): "+matcher.lookingAt());
	       System.out.println("matches(): "+matcher.matches());
		
		
	}
	
	/**
	 * 正则匹配出现和结束的位置
	 */
	public void start_end(){
	      // 按指定模式在字符串查找
	      String line = "3000";
	      String pattern = "(\\D*)(\\d+)(.*)";
	 
	      // 创建 Pattern 对象
	      Pattern r = Pattern.compile(pattern);
	 
	      // 现在创建 matcher 对象
	      Matcher m = r.matcher(line);
	      if (m.find( )) {
	         System.out.println("Found value: " + m.group(0) );
	         System.out.println("Found value: " + m.group(1) );
	         System.out.println("Found value: " + m.group(2) );
	         System.out.println("Found value: " + m.group(3) ); 
	      } else {
	         System.out.println("NO MATCH");
	      }
		
	}
	
	/**
	 * replaceFirst 和 replaceAll 方法用来替换匹配正则表达式的文本。不同的是，replaceFirst 替换首次匹配，replaceAll 替换所有匹配。
	 */
	public void replace(){
		  String REGEX = "dog";
		     String INPUT = "The dog says meow. " +
		                                    "All dogs say meow.";
		    String REPLACE = "cat";
		 
		
		       Pattern p = Pattern.compile(REGEX);
		       // get a matcher object
		       Matcher m = p.matcher(INPUT); 
		       INPUT = m.replaceAll(REPLACE);
		       System.out.println(INPUT);
		  
	}
	
	public void replace1(){
		  String REGEX = "(\\D*)";
		  String str1 = "100*GE";
		  String REPLACE = "";
		 
		
		       Pattern p = Pattern.compile(REGEX);
		       // get a matcher object
		       Matcher m = p.matcher(str1); 
		       str1 = m.replaceAll(REPLACE);
		       System.out.println(str1);
		  
	}
	
	/**
	 * Matcher 类也提供了appendReplacement 和 appendTail 方法用于文本替换：
	 */
	public void appendReplacement_appendTail(){
		 String REGEX = "a*b";
		 String INPUT = "aabfooaabfooabfoob";
		  String REPLACE = "-";
		      Pattern p = Pattern.compile(REGEX);
		      // 获取 matcher 对象
		      Matcher m = p.matcher(INPUT);
		      StringBuffer sb = new StringBuffer();
		      while(m.find()){
		         m.appendReplacement(sb,REPLACE);
		      }
		      m.appendTail(sb);
		      System.out.println(sb.toString());
	}
	
	
	
}
