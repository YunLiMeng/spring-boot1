package com.example.demo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.StudentProperties;

/**
 * @author ml
 * @version 创建时间：2019年3月25日
 * 类说明
 */

@Controller
@RequestMapping("/mytest2")
public class HelloJspController {
	
private static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	
	@Autowired
	private StudentProperties student;
	
	@RequestMapping("/hello")
	public String hello(){
		logger.info("姓名：{}，年龄：{}",student.getName(),student.getAge());
		return "hello";
	}

}
