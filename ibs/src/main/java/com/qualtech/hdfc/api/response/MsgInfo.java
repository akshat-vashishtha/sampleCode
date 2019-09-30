package com.qualtech.hdfc.api.response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MsgInfo {

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
}
