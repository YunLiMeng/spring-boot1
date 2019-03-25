package com.example.demo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.StudentProperties;

/**
 * @author ml
 * @version 创建时间：2019年3月13日
 * 类说明
 */
@RestController
@RequestMapping("/mytest")
public class HelloWorldController {
	
	private static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	
	@Autowired
	private StudentProperties student;
	
	@RequestMapping("/hello")
	public String hello(){
		logger.info("姓名：{}，年龄：{}",student.getName(),student.getAge());
		return "Hello World.";
	}
}
