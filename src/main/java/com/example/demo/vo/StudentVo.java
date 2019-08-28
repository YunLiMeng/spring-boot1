package com.example.demo.vo;

import lombok.Data;

/**
 * @ author: limeng
 * @ date: Created in 2019/8/28 14:00
 * @ description：返回学生分页列表vo
 */
@Data
public class StudentVo {
    private Long id;

    private String name;

    private String sex;

    private String age;
}
