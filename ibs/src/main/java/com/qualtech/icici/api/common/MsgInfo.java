package com.qualtech.icici.api.common;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true) 
public class MsgInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	private String code;
	private String description;
	private String message;
	private String status;

	public MsgInfo() {
	}

	public MsgInfo(String code, String description, String message, String status) {
		this.code = code;
		this.description = description;
		this.message = message;
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ClassPojo [code = " + code + ", description = " + description + ", message = " + message + ", status = "
				+ status + "]";
	}

}
