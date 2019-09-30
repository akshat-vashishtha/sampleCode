package com.qualtech.icici.api.response;

import java.io.Serializable;

public class CustOnBoardResponsePayload implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3420514467406706234L;

	private String byteArray;
	private String pdfPath;
	private CustOnBoardResponseResult result;
	public String getByteArray() {
		return byteArray;
	}
	public void setByteArray(String byteArray) {
		this.byteArray = byteArray;
	}
	public String getPdfPath() {
		return pdfPath;
	}
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
	public CustOnBoardResponseResult getResult() {
		return result;
	}
	public void setResult(CustOnBoardResponseResult result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "CustOnBoardResponsePayload [byteArray=" + byteArray + ", pdfPath=" + pdfPath + ", result=" + result
				+ "]";
	}  
	
	
}
