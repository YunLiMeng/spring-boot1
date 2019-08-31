package com.example.demo.model;

/**
 * @description： excel导出列
 * @author: limeng
 * @date: 2019/8/31
 */
public class ExcelColumn {
	
	private String keyName;
	
	private String textName;

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getTextName() {
		return textName;
	}

	public void setTextName(String textName) {
		this.textName = textName;
	}

	public ExcelColumn(String keyName, String textName) {
		super();
		this.keyName = keyName;
		this.textName = textName;
	}
	
}
