package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

/**
 * @author ml
 * @version 创建时间：2019年3月25日
 * 类说明
 */
@Data
public class Student {
	
	private Long id;
	
	private String name;
	
	private String sex;
	
	private String age;

	@TableLogic
	private byte deleted;

	public Student() {
	}

	public Student(Long id, String name, String sex, String age) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

}
