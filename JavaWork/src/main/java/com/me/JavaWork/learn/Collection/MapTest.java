package com.me.JavaWork.learn.Collection;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<>();
		map.put("1", "22222222");
		System.out.println(map.get("2")!=null?"notnull":"is null");
	}

}
