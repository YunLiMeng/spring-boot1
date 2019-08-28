package com.example.demo.web.controller;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.common.ResponseMessage;
import com.example.demo.vo.StudentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * @description：集成mybatis-demo
     * @author: limeng
     * @date: 2019/8/28
     * @param:
     * @return: com.example.demo.common.ResponseMessage
     */
    @RequestMapping("/getAllStudent")
    public ResponseMessage getAllStudent() {
    	List<Map<String, Object>> students = studentService.getAllStudent();
    	return ResponseMessage.Success(students);
    }

    /**
     * @description：集成mybatis-plus及分页插件demo【自定义sql分页】
     * @author: limeng
     * @date: 2019/8/28
     * @param:
     * @return: com.example.demo.common.ResponseMessage
     */
    @RequestMapping("/studentList")
    public ResponseMessage studentList() {
        IPage<StudentVo> result = studentService.studentList();
    	return ResponseMessage.Success(result);
    }

    /**
     * @description：baseMapper中自带的分页
     * @author: limeng
     * @date: 2019/8/28
     * @param:
     * @return: com.example.demo.common.ResponseMessage
     */
    @RequestMapping("/studentListPage")
    public ResponseMessage studentListPage() {
        IPage<Student> result = studentService.studentListPage();
    	return ResponseMessage.Success(result);
    }

    /**
     * @description：逻辑删除对象信息
     * @author: limeng
     * @date: 2019/8/28
     * @param: studentId
     * @return: com.example.demo.common.ResponseMessage
     */
    @PostMapping("/deletedById")
    public ResponseMessage deletedById(@RequestParam("studentId") Long studentId) {
        studentService.deletedById(studentId);
    	return ResponseMessage.Success(null);
    }


}