package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author ml
 * @version 创建时间：2019年3月25日
 * 类说明
 */

@Repository
@Mapper
public interface StudentMapper {

    @Select("SELECT * FROM student")
    List<Student> findAll();
    
    List<Map<String,Object>> getAllStudent();

    IPage<Student> studentList(IPage<Student> page);
}