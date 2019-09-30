package com.qualtech.equifax.api.response;

public class EvdrErrorInfo {

	String code=null;
	String status=null;
	String message=null;
	String description=null;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "EvdrErrorInfo [code=" + code + ", status=" + status + ", message=" + message + ", description="
				+ description + "]";
	}
	
	
}
