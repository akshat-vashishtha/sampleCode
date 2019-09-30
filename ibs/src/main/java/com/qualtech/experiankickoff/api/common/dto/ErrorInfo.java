package com.qualtech.experiankickoff.api.common.dto;

import java.io.Serializable;

public class ErrorInfo implements Serializable 
{
	private static final long serialVersionUID = -2183001514591673307L;
	String code;
	ERRORSTATUS status;
	String message;
	String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ERRORSTATUS getStatus() {
		return status;
	}

	public void setStatus(ERRORSTATUS status) {
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
		return "ErrorInfo [code=" + code + ", status=" + status + ", message=" + message + ", description="
				+ description + "]";
	}

}
