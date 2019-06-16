package com.example.demo.entity;
/**
 * @author ml
 * @version 创建时间：2019年3月25日
 * 类说明
 */
public class Student {
	
	private Long id;
	
	private String name;
	
	private String sex;
	
	private String age;
	
	public Student() {
		
	}

	public Student(Long id, String name, String sex, String age) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
