package com.me.JavaWork.learn;

import java.io.Serializable;

public class Pojo implements Serializable{
	private static final long serialVersionUID = -4805852706306054093L;
	private String name;
	private String desc;
	private Integer age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * @param name
	 */
	public Pojo(String name) {
		super();
		this.name = name;
	}
	/**
	 * @param name
	 * @param desc
	 */
	public Pojo(String name, String desc) {
		super();
		this.name = name;
		this.desc = desc;
	}
	/**
	 * @param name
	 * @param desc
	 * @param age
	 */
	public Pojo(String name, String desc, Integer age) {
		super();
		this.name = name;
		this.desc = desc;
		this.age = age;
	}
	/**
	 * @param desc
	 * @param age
	 */
	public Pojo(String desc, Integer age) {
		super();
		this.desc = desc;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Pojo [name=" + name + ", desc=" + desc + ", age=" + age + "]";
	}


}
