package com.qualtech.icici.api.response;

import java.io.Serializable;


public class MessageInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String status;
	private String message;
	private String description;
	
//	private String internalCode;
	
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
		return "MessageInfo [code=" + code + ", status=" + status + ", message=" + message + ", description="
				+ description + "]";
	}

}
