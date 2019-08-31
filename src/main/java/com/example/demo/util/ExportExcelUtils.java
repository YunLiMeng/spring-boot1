package com.example.demo.util;

import com.example.demo.model.ExcelColumn;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

@SuppressWarnings("deprecation")
public class ExportExcelUtils {
	private String title;
	private List<ExcelColumn> rowName = new ArrayList<ExcelColumn>();;
	private Map<String, List<Object>> dataList = new HashMap<String, List<Object>>();
	HttpServletRequest request;
	HttpServletResponse response;
	
	/**
	 * @description: Excel导出类初始化
	 * @author: maojialong
	 * @date: 2017年12月27日 下午3:56:42
	 * @param title
	 * @param columnName
	 * @param data
	 * @param response
	 * @demo
	 * 	String[] columnName = {"title","标题",
							  "publishAuthor","发布人",
							  "publishDate","发布时间",
							  "description","描述",
							  "countView","浏览次数"};
		String title = "测试导出";
		data为需要导出的数据集合
	 */
	public ExportExcelUtils(String title, String[] columnName, List<Map<String, Object>> data, HttpServletRequest request,
                            HttpServletResponse response) {
		for(int i = 0;i<columnName.length;i += 2) {
			this.rowName.add(new ExcelColumn(columnName[i],columnName[i+1]));
		}
		for(Map<String,Object> map:data) {
			 for(ExcelColumn coluName:this.rowName) {
				 if(this.dataList.get(coluName.getTextName()) == null) {
					 this.dataList.put(coluName.getTextName(), new ArrayList<Object>());
				 }
				 this.dataList.get(coluName.getTextName()).add(map.get(coluName.getKeyName()));
			 }
		 }
		
		this.title = title;
		this.request = request;
		this.response = response;
	}

	public void export() throws Exception {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(this.title);

			HSSFCellStyle columnTopStyle = getColumnTopStyle(workbook);
			HSSFCellStyle style = getStyle(workbook);
			style.setWrapText(true);

			int columnNum = this.rowName.size();
			HSSFRow rowRowName = sheet.createRow(0);
			int rows = 0;
			for (int n = 0; n < columnNum; n++) {
				HSSFCell cellRowName = rowRowName.createCell(n);
				cellRowName.setCellType(1);
				HSSFRichTextString text = new HSSFRichTextString(this.rowName.get(n).getTextName());
				cellRowName.setCellValue(text);
				cellRowName.setCellStyle(columnTopStyle);
				List<Object> rowValues = dataList.get(this.rowName.get(n).getTextName());
				if (rowValues != null && rowValues.size() > rows) {
					rows = rowValues.size();
				}
			}

			for (int i = 0; i < rows; i++) {
				HSSFRow row = sheet.createRow(i + 1);
				for (int j = 0;j<rowName.size(); j++) {
					List<Object> obj = dataList.get(rowName.get(j).getTextName());
					HSSFCell cell = null;
					cell = row.createCell(j, 1);
					if ((!"".equals(obj.get(i))) && (obj.get(i) != null)) {
						cell.setCellValue(obj.get(i).toString().trim());
					} else {
						cell.setCellValue("");
					}
					cell.setCellStyle(style);
				}
			}

			for (int colNum = 0; colNum < columnNum; colNum++) {
				int columnWidth = 10;
				for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
					HSSFRow currentRow;
					if (sheet.getRow(rowNum) == null) {
						currentRow = sheet.createRow(rowNum);
					} else {
						currentRow = sheet.getRow(rowNum);
					}
					if (currentRow.getCell(colNum) != null) {
						HSSFCell currentCell = currentRow.getCell(colNum);
						if (currentCell.getCellType() == 1) {
							int length = currentCell.getStringCellValue().getBytes().length;
							if (columnWidth < length) {
								columnWidth = length;
							}
						}
					}
				}
				int columnWidthFinal = columnWidth + 4;
				if (columnWidthFinal > 255) {
					columnWidthFinal = 255;
				}
				sheet.setColumnWidth(colNum, columnWidthFinal * 256);
			}
			if (workbook != null) {
				try {
					this.response.resetBuffer();
					this.response.reset();
					response.setContentType("application/vnd.ms-excel;charset=gb2312");
					String fileName = this.title + DateUtil.getFormatStrByPatternAndDate("yyyy-MM-dd",new Date()) + ".xls";
					String encoded = URLEncoder.encode(fileName, "UTF-8");
					String UserAgent = this.request.getHeader("USER-AGENT").toLowerCase();
					if (UserAgent.indexOf("firefox") >= 0) {
						encoded = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
					}
					this.response.addHeader("Content-Disposition", "attachment;filename=" + encoded);
					OutputStream out = this.response.getOutputStream();
					workbook.write(out);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public byte[] getExeclBytes() {
		ByteArrayOutputStream os = null;
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(this.title);

			HSSFCellStyle columnTopStyle = getColumnTopStyle(workbook);
			HSSFCellStyle style = getStyle(workbook);

			int columnNum = this.rowName.size();
			HSSFRow rowRowName = sheet.createRow(0);
			int rows = 0;
			for (int n = 0; n < columnNum; n++) {
				HSSFCell cellRowName = rowRowName.createCell(n);
				cellRowName.setCellType(1);
				HSSFRichTextString text = new HSSFRichTextString(this.rowName.get(n).getTextName());
				cellRowName.setCellValue(text);
				cellRowName.setCellStyle(columnTopStyle);
				List<Object> rowValues = dataList.get(this.rowName.get(n).getTextName());
				if (rowValues != null && rowValues.size() > rows) {
					rows = rowValues.size();
				}
			}

			for (int i = 0; i < rows; i++) {
				HSSFRow row = sheet.createRow(i + 1);
				for (int j = 0;j<rowName.size(); j++) {
					List<Object> obj = dataList.get(rowName.get(j).getTextName());
					HSSFCell cell = null;
					cell = row.createCell(j, 1);
					if ((!"".equals(obj.get(i))) && (obj.get(i) != null)) {
						cell.setCellValue(obj.get(i).toString().trim());
					} else {
						cell.setCellValue("");
					}
					cell.setCellStyle(style);
				}
			}

			for (int colNum = 0; colNum < columnNum; colNum++) {
				int columnWidth = 10;
				for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
					HSSFRow currentRow;
					if (sheet.getRow(rowNum) == null) {
						currentRow = sheet.createRow(rowNum);
					} else {
						currentRow = sheet.getRow(rowNum);
					}
					if (currentRow.getCell(colNum) != null) {
						HSSFCell currentCell = currentRow.getCell(colNum);
						if (currentCell.getCellType() == 1) {
							int length = currentCell.getStringCellValue().getBytes().length;
							if (columnWidth < length) {
								columnWidth = length;
							}
						}
					}
				}
				int columnWidthFinal = columnWidth + 4;
				if (columnWidthFinal > 255) {
					columnWidthFinal = 255;
				}
				sheet.setColumnWidth(colNum, columnWidthFinal * 256);
			}
			if (workbook != null) {
				os = new ByteArrayOutputStream();
				workbook.write(os);
				return os.toByteArray();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();

		font.setFontHeightInPoints((short) 11);

		font.setBold(true);

		font.setFontName("Courier New");

		HSSFCellStyle style = workbook.createCellStyle();

		style.setBorderBottom(BorderStyle.THIN);

		style.setBottomBorderColor((short) 8);

		style.setBorderLeft(BorderStyle.THIN);

		style.setLeftBorderColor((short) 8);

		style.setBorderRight(BorderStyle.THIN);

		style.setRightBorderColor((short) 8);

		style.setBorderTop(BorderStyle.THIN);

		style.setTopBorderColor((short) 8);

		style.setFont(font);

		style.setWrapText(false);

		style.setAlignment(HorizontalAlignment.CENTER);

		style.setVerticalAlignment(VerticalAlignment.CENTER);

		return style;
	}

	public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();

		font.setFontName("Courier New");

		HSSFCellStyle style = workbook.createCellStyle();

		style.setBorderBottom(BorderStyle.THIN);

		style.setBottomBorderColor((short) 8);

		style.setBorderLeft(BorderStyle.THIN);

		style.setLeftBorderColor((short) 8);

		style.setBorderRight(BorderStyle.THIN);

		style.setRightBorderColor((short) 8);

		style.setBorderTop(BorderStyle.THIN);

		style.setTopBorderColor((short) 8);

		style.setFont(font);

		style.setWrapText(false);

		style.setAlignment(HorizontalAlignment.CENTER);

		style.setVerticalAlignment(VerticalAlignment.CENTER);

		return style;
	}
}
