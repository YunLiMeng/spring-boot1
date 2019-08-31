package com.example.demo.util;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @description： excel导入表格数据读取
 * @author: limeng
 * @date: 2019/8/31
 */
public class ReadExcelUtils {
	//excel shell个数
	private int shellNums=0;
    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;
    //错误信息接收器
    private static String errorMsg;
    //构造方法
    public ReadExcelUtils(){}
    //获取excel shell个数
    public int getShellNums(){ return shellNums;}
    //获取总行数  
    public int getTotalRows()  { return totalRows;}   
    //获取总列数  
    public int getTotalCells() {  return totalCells;}   
    //获取错误信息  
    public String getErrorInfo() { return errorMsg; }    
      
  /**
   * @description：读EXCEL文件，获取表格信息集合
   * @author: limeng
   * @date: 2019/8/31
   * @param: mFile excel文件
   * @param: sheetIndex 当前第几个表格（sheet）
   * @return: java.util.List<java.util.List<java.lang.Object>>
   */
    public static List<List<Object>> readExcelMessage(MultipartFile mFile,int sheetIndex) {
        String fileName = mFile.getOriginalFilename();//获取文件名
        List<List<Object>> readers = null;
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格  
                return null;  
            }  
            InputStream is = mFile.getInputStream();
            // 读取表格
            ExcelReader excelReader = ExcelUtil.getReader(is,sheetIndex);
            readers = excelReader.read();
        } catch (Exception e) {
            e.printStackTrace();  
        }
        return readers;
    }
    
    /**
     * @description：验证EXCEL文件
     * @author: limeng
     * @date: 2019/8/31
     * @param: filePath
     * @return: boolean
     */
    public static boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {  
            errorMsg = "文件名不是excel格式";  
            return false;  
        }  
        return true;  
    }  
      
    /**
     * @description：是否是2003的excel，返回true是2003
     * @author: limeng
     * @date: 2019/8/31
     * @param: filePath
     * @return: boolean
     */
    public static boolean isExcel2003(String filePath)  {    
         return filePath.matches("^.+\\.(?i)(xls)$");    
     }    

     /**
      * @description：是否是2007的excel，返回true是2007
      * @author: limeng
      * @date: 2019/8/31
      * @param: filePath
      * @return: boolean
      */
    public static boolean isExcel2007(String filePath)  {
         return filePath.matches("^.+\\.(?i)(xlsx)$");    
     }    
}  