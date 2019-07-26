package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.entity.Student;

/**
 * 在项目中打印日志
 * 新建一个配置类LogConfig，注入一个Bean，并在方法中打印日志
 * @author DELL
 *
 */
@Configuration
public class LogConfig {
	private static final Logger logger = LoggerFactory.getLogger(LogConfig.class);
	
	@Bean
	public Student getStudent() {
		logger.info("日志打印，进入了方法中，并且返回一个student对象。");
		return new Student();
	}
}
