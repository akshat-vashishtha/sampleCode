package com.qualtech.multibureau.api.common.dto;

import java.io.Serializable;

public class BureauDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7527923968215650767L;
	
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
