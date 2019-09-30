package com.qualtech.kotak.api.dto;

import java.io.Serializable;

public class KotakDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1513898572117477268L;
	public String getByteArray() {
		return byteArray;
	}
	public void setByteArray(String byteArray) {
		this.byteArray = byteArray;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	private String byteArray;
	private String filePath;

}
