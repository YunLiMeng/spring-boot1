package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ml
 * @version 创建时间：2019年3月13日
 * 类说明
 */
@RestController
@RequestMapping("/mytest")
public class HelloWorldController {
	
	@RequestMapping("/hello")
	public String hello(){
		return "Hello World.";
	}
}
