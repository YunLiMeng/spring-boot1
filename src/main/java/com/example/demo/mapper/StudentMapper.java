package com.example.demo.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.vo.StudentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;

/**
 * @author ml
 * @version 创建时间：2019年3月25日
 * 类说明
 */

@Repository
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    @Select("SELECT * FROM student")
    List<Student> findAll();
    
    List<Map<String,Object>> getAllStudent();

    IPage<StudentVo> studentList(IPage<StudentVo> page);
}