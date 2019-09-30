package com.qualtech.experiankickoff.api.common.dto;

import java.io.Serializable;

public class ExperianDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4519246613219248784L;
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
