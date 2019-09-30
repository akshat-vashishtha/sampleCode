package com.qualtech.equifax.api.utils;

public class HttpCallDetails {

	private String response;
	private int responseCode;
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	@Override
	public String toString() {
		return "HttpCallDetails [response=" + response + ", responseCode=" + responseCode + "]";
	}
	
}
