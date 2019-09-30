package com.qualtech.hdfc.api.response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Errors {

	private String errorCode;
	private String errorMessage;
	private String jsonPath;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getJsonPath() {
		return jsonPath;
	}
	public void setJsonPath(String jsonPath) {
		this.jsonPath = jsonPath;
	}
	@Override
	public String toString() {
		return "Errors [errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", jsonPath=" + jsonPath + "]";
	}
}
