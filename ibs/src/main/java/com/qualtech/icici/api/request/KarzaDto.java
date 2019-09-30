package com.qualtech.icici.api.request;

import java.io.Serializable;

public class KarzaDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7619688591598381856L;
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
