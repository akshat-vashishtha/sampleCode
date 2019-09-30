package com.qualtech.multibureau.api.common.dto;

import java.io.Serializable;
import java.util.List;

public class AllBureauDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4407234233385286441L;
	private List<String> byteArray;
	private List<String> filePath;
	private String pdfPath;
	
	
	public String getPdfPath() {
		return pdfPath;
	}
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
	public List<String> getByteArray() {
		return byteArray;
	}
	public void setByteArray(List<String> byteArray) {
		this.byteArray = byteArray;
	}
	public List<String> getFilePath() {
		return filePath;
	}
	public void setFilePath(List<String> filePath) {
		this.filePath = filePath;
	}


}