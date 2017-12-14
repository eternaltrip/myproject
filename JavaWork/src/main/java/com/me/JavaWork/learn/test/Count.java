package com.me.JavaWork.learn.test;

public class Count {
	private String ip;
	private String name;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Count [ip=" + ip + ", name=" + name + "]";
	}
	

}
