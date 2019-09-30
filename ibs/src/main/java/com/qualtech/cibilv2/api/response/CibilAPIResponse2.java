package com.qualtech.cibilv2.api.response;


import java.io.Serializable;

import com.qualtech.cibilv2.api.common.dto.ErrorInfo;

public class CibilAPIResponse2 implements Serializable
{
	
	private static final long serialVersionUID = 2947259465589839433L;
	private ApiResponseHeader2 header;
	private ErrorInfo errorInfo;
	private CibilResponsePayload2 payload;
	public ApiResponseHeader2 getHeader() {
		return header;
	}
	public void setHeader(ApiResponseHeader2 header) {
		this.header = header;
	}
	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}
	public CibilResponsePayload2 getPayload() {
		return payload;
	}
	public void setPayload(CibilResponsePayload2 payload) {
		this.payload = payload;
	}
	public CibilAPIResponse2(ApiResponseHeader2 header, ErrorInfo errorInfo, CibilResponsePayload2 payload) {
		super();
		this.header = header;
		this.errorInfo = errorInfo;
		this.payload = payload;
	}
	public CibilAPIResponse2() {
		super();
		
	}
	@Override
	public String toString() {
		return "CibilAPIResponse2 [header=" + header + ", errorInfo=" + errorInfo + ", payload=" + payload + "]";
	}

	
	
}
