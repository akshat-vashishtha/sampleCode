package com.qualtech.icici.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse implements Serializable
{
	private static final long serialVersionUID = -6415185131557298252L;
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ErrorResponse [result=" + result + "]";
	}
}
