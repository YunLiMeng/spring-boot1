package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.base.ServiceException;
import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.util.ReadExcelUtils;
import com.example.demo.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

     /**
      * @description：根据id进行逻辑删除
      * @author: limeng
      * @date: 2019/8/28
      * @param: id
      * @return: void
      */
     public void deletedById(Long id){
	 	baseMapper.deleteById(id);
	 }

	 /**
	  * @description：读取excel表格信息入库
	  * @author: limeng
	  * @date: 2019/8/31
	  * @param: file
	  * @return: java.lang.String
	  */
	 public String importToDb(MultipartFile file){
		String result = "success";
		List<List<Object>> readers = ReadExcelUtils.readExcelMessage(file,1);
		 for (int i = 0; i < readers.size(); i++) {
			 // 去掉表头
			 if (i>0) {
				 List<Object> data = readers.get(i);
				 // 验证每行的对应列数是否完整
				 if(data.size() < 4) {
					 throw new ServiceException(String.format("导入失败：数据不完整导入失败！请参照模板格式导入！", i + 1));
				 }
				 String colum1 = data.get(0).toString();
				 String colum2 = data.get(1).toString();
				 String colum3 = data.get(2).toString();
				 String colum4 = data.get(3).toString();
				 System.out.println("第"+(i+1)+"行表格数据为：");
				 System.out.println("colum1："+colum1+",colum2："+colum2+",colum3："+colum3+",colum4："+colum4);
			 }
		 }
		return result;
	 }
}
