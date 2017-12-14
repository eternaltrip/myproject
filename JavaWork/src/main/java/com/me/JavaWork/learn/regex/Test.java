package com.me.JavaWork.learn.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args) {
		String str = "100*ge";
		String str1 = "100*GE";
		String str2 = "100G";
		String str3 = "100GE";
		String str4 = "100";
		String str5 = "";
		String str6 = null;
		String str7 = "null";
		
		List<String> strs = new ArrayList<>();
		strs.add(str);
		strs.add(str1);
		strs.add(str2);
		strs.add(str3);
		strs.add(str4);
		strs.add(str5);
		strs.add(str6);
		strs.add(str7);
		
		
		String pattern = "(\\d+)(.*)";
		String repalcPpattern = "(\\D*)";
		String replaceStr = "";
		for (int i = 0; i < strs.size(); i++) {
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(strs.get(i) == null ? "":strs.get(i));
			
			if(m.find()){
				int num = m.groupCount();
				System.out.println(strs.get(i) + "    groupNO:" + num);
				System.out.println(strs.get(i) + "    groupNO:" + m.group(0));
				System.out.println(strs.get(i) + "    groupNO:" + m.group(1));
				System.out.println(strs.get(i) + "    groupNO:" + m.group(2));
				System.out.println(strs.get(i) + " start:" + m.start() );
				System.out.println(strs.get(i) + " end:" + m.end() );
			}else{
				System.out.println("not match");
			}
			
			p = Pattern.compile(repalcPpattern);
			if(m.matches()){
				
				m = p.matcher(strs.get(i) == null ? "":strs.get(i));
				System.out.println("--------------------------afterDeal:" + m.replaceAll(replaceStr) + "\n");
			}
			
			
			
			
		}
		
	
	}
	

}
