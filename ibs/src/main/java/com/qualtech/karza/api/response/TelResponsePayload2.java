package com.qualtech.karza.api.response;

import java.io.Serializable;

public class TelResponsePayload2 implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TelResult2 result;
	private String byteArray;
	private String pdfPath;
	private int status_code;
	private String request_id;
	private String status_msg;


	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	public TelResult2 getResult() {
		return result;
	}

	public void setResult(TelResult2 result) {
		this.result = result;
	}

	public String getByteArray() {
		return byteArray;
	}

	public int getStatus_code() {
		return status_code;
	}

	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getStatus_msg() {
		return status_msg;
	}

	public void setStatus_msg(String status_msg) {
		this.status_msg = status_msg;
	}

	public void setByteArray(String byteArray) {
		this.byteArray = byteArray;
	}
	
	@Override
	public String toString() {
		return "TelResponsePayload [result=" + result + ", byteArray=" + byteArray + ", pdfPath=" + pdfPath
				+ ", status_code=" + status_code + ", request_id=" + request_id + ", status_msg=" + status_msg + "]";
	}


}
