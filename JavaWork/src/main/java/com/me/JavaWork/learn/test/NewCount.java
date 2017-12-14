package com.me.JavaWork.learn.test;

public class NewCount extends Count{
	
	private Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "NewCount [count=" + count + "]";
	}
	
}
