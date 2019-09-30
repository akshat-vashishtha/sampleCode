package com.qualtech.icici.api.response;

import java.io.Serializable;

public class PreCalcResponsePayload implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8375377018896680182L;

	private PreCalcResPaylaodResult result;
	private String byteArray;
	private String pdfPath;
	public PreCalcResPaylaodResult getResult() {
		return result;
	}
	public void setResult(PreCalcResPaylaodResult result) {
		this.result = result;
	}
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
	@Override
	public String toString() {
		return "PreCalcResponsePayload [result=" + result + ", byteArray=" + byteArray + ", pdfPath=" + pdfPath + "]";
	}
	
	
}
