package com.me.JavaWork.learn.thread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionModifyExceptionTest {
	public static void main(String[] args) {
//		Collection users  =  new ArrayList();//非并发的集合在迭代的时候不能修改，修改会导致抛异常。
		Collection users  =  new CopyOnWriteArrayList();//使用并发集合就不会出现在异常了。
		users.add("aaaa");
		users.add("bbbb");
		users.add("cccc");
		Iterator iterator = users.iterator();
		while(iterator.hasNext()){
			String name = (String) iterator.next();
			if("cccc".equals(name)){
				users.remove(name);
			}else{
				System.out.println(name);
			}
		}
	}

}
