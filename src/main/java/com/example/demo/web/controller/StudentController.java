package com.example.demo.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.common.ResponseMessage;
import com.example.demo.vo.StudentVo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    /**
     * @description：excel分页导出
     * @author: limeng
     * @date: 2019/8/29
     * @param: request
     * @param: response
     * @return: void
     */
    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response){
        List<StudentVo> studentList = studentService.studentList().getRecords();
        // total--一共有多少条数据
        int total=studentList.size();
        // page--一个excel一共有几页表格
        int page=total%5000>0?(total/5000+1):(total/5000);

        HSSFWorkbook wb = new HSSFWorkbook();
        for (int k = 0; k < page; k++) { // k--一个excel中的一页一页的表格--当前页
            HSSFSheet sheet=wb.createSheet("学生信息"+k+"");
            sheet.setDefaultColumnWidth(15);
            HSSFRow row0 = sheet.createRow(0);
            row0.createCell(0).setCellValue("编号");
            row0.createCell(1).setCellValue("姓名");
            row0.createCell(2).setCellValue("年龄");
            row0.createCell(3).setCellValue("性别");
            int count=0;
            if (k == (page - 1)) {
                count = total;
            }else {
                if(total>5000){
                    count=5000*(k+1);
                }else{
                    count=total;
                }
            }

            //k=0;page=1;count=100;
            for (int i = 5000 * k; i < count; i++) {
                StudentVo student = studentList.get(i);
                HSSFRow row2 = sheet.createRow(1 + (i % 5000));
                row2.createCell(0).setCellValue(student.getId());
                row2.createCell(1).setCellValue(student.getName());
                row2.createCell(2).setCellValue(student.getAge());
                row2.createCell(3).setCellValue(student.getSex());
            }
        }

        /*
         * 不弹出下载框
         *
         * FileOutputStream out = null; try { out = new
         * FileOutputStream("E:/per.xls"); wb.write(out); out.close(); } catch
         * (FileNotFoundException e1) { e1.printStackTrace(); } catch
         * (IOException e) { e.printStackTrace(); } finally { try { out.close();
         * } catch (IOException e) { e.printStackTrace(); } }
         */

        OutputStream output = null;
        try {
            output = response.getOutputStream();
            response.reset();
            String filename = "用户信息";
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(filename.getBytes("gb2312"), "ISO8859-1")
                    + ".xls");
            response.setContentType("application/msexcel;charset=utf-8");
            wb.write(output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}