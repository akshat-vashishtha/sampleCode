package com.qualtech.experiankickoff.api.response;

public class ExperianGenResponsePayload {


	private ExperianGenResPayloadResult result;
	
	private String byteArray;
	private String pdfPath;
	private int status_code;
	private String request_id;
	private String status_msg;



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
	
	@Override
	public String toString() {
		return "ExperianAuthResponsePayload [resultBlock=" + result + ", byteArray=" + byteArray + ", pdfPath="
				+ pdfPath + ", status_code=" + status_code + ", request_id=" + request_id + ", status_msg=" + status_msg
				+ "]";
	}
	public ExperianGenResPayloadResult getResult() {
		return result;
	}
	public void setResult(ExperianGenResPayloadResult result) {
		this.result = result;
	}

	

}
