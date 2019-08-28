package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    	List<Map<String, Object>> students = baseMapper.getAllStudent();
    	return students;
     }

	 public IPage<StudentVo> studentList() {
	 	IPage<StudentVo> page = new Page<>(1,10);
		 IPage<StudentVo> list = baseMapper.studentList(page);
    	return list;
     }
}
