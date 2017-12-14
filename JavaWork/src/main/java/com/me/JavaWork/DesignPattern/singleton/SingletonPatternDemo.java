package com.me.JavaWork.DesignPattern.singleton;

public class SingletonPatternDemo {
	public static void main(String[] args) {
		//只能使用规定的方法来获取对象，不能使用new的形式获取
		SingleObject.getInstance().showMess();
	}

}
