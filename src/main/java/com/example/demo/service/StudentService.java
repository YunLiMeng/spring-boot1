package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

     /**
      * @description：自定义sql分页
      * @author: limeng
      * @date: 2019/8/28
      * @param:
      * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.example.demo.vo.StudentVo>
      */
	 public IPage<StudentVo> studentList() {
	 	IPage<StudentVo> page = new Page<>(1,10);
	 	IPage<StudentVo> list = baseMapper.studentList(page);
    	return list;
     }

     /**
      * @description：baseMapper中自带的分页
      * @author: limeng
      * @date: 2019/8/28
      * @param:
      * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.example.demo.entity.Student>
      */
	 public IPage<Student> studentListPage() {
	 	 IPage<Student> page = new Page<>(1,10);
		 LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
		 IPage<Student> list = baseMapper.selectPage(page, wrapper);
//	 	 IPage<StudentVo> list = baseMapper.studentList(page);
    	 return list;
     }
}
