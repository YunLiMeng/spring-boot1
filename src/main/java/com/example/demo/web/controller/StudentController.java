package com.example.demo.web.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.service.StudentService;

/**
 * @author ml
 * @version 创建时间：2019年3月25日
 * 类说明
 */
@RestController
@RequestMapping("/student")
public class StudentController {
	
	private static Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;
    
    @Autowired
	StudentMapper studentMapper;

    @RequestMapping("/listStudent")
    public String listStudent() {
        List<Student> students = studentMapper.findAll();
        logger.info("学生信息：{}",students.get(0).toString());
        
        return "第一个学生信息"+students.get(0).toString();
    }
    
    @RequestMapping("/getAllStudent")
    public List<Map<String, Object>> getAllStudent() {
    	System.out.println("----------------");
    	List<Map<String, Object>> students = studentService.getAllStudent();
    	
    	return students;
    }
    
    
}