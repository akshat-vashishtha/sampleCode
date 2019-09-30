package com.qualtech.creditvidya.api.request;

import java.io.Serializable;

public class HttpCallDetails implements Serializable 
{
	private static final long serialVersionUID = 3812865261706201592L;
	private String response;
	private int responseCode;
	public String getResponse() 
	{
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
		return "KarzaInfo [response=" + response + ", responseCode=" + responseCode + "]";
	}
}
