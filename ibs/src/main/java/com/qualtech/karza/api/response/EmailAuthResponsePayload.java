package com.qualtech.karza.api.response;

import java.io.Serializable;

public class EmailAuthResponsePayload implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private EmailAuthResult result;
	private String byteArray;
	private String pdfPath;
	private int status;
	private int status_code;
	private String request_id;
	private String status_msg;
	


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getStatus_code() {
		return status_code;
	}
	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}
	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}


	public String getByteArray() {
		return byteArray;
	}

	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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

	/**
	 * @return the result
	 */
	public EmailAuthResult getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(EmailAuthResult result) {
		this.result = result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmailAuthResponsePayload [result=" + result + ", byteArray=" + byteArray + ", pdfPath=" + pdfPath
				+ ", status_code=" + status_code + ", request_id=" + request_id + ", status_msg=" + status_msg + "]";
	}
	

	
	
}
