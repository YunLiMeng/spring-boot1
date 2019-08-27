package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.StudentMapper;

/**
 * @author ml
 * @version 创建时间：2019年3月25日
 * 类说明
 */

@Service
public class StudentService extends ServiceImpl<StudentMapper, Student> {
	
	 @Autowired
	 StudentMapper studentMapper;
	 
	 public List<Map<String, Object>> getAllStudent() {
    	System.out.println("----------------");
    	List<Map<String, Object>> students = baseMapper.getAllStudent();
    	
    	return students;
     }
}
