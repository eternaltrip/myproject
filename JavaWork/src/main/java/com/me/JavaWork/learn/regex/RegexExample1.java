package com.me.JavaWork.learn.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample1 {
	public static void main(String[] args) {
		String str = "yangjin is my name.";
		String pattern = ".*is.*";
		boolean isMatch = Pattern.matches(pattern, str);
		System.out.println(str +" 中是否包换 is ? 答案：" + isMatch);
		
		
		
		
		 // 创建 Pattern 对象
		Pattern r = Pattern.compile(pattern);
		 // 现在创建 matcher 对象
		Matcher m = r.matcher(str);
		
		int groupNO = m.groupCount();
		System.out.println(m.find());
		System.out.println(str + "被分成" + groupNO + "组");
		System.out.println(m.group(0));
	}

}
