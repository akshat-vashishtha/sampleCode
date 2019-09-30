package com.qualtech.equifax.api.response;

import java.io.Serializable;

import com.qualtech.equifax.api.common.dto.ErrorInfo;

public class EquifaxApiResponse implements Serializable
{
	private static final long serialVersionUID = -4059495639692773036L;
    private EquifaxApiResponseHeader header;
    private ErrorInfo errorInfo;
    private EquifaxCriffResponsePayload payload;
	
	public EquifaxApiResponse() {
		super();
	}
	public EquifaxApiResponse(ErrorInfo errorInfo,EquifaxApiResponseHeader header, EquifaxCriffResponsePayload payload) {
		super();
		this.errorInfo = errorInfo;
		this.header = header;
		this.payload = payload;
	}
	
	public EquifaxApiResponseHeader getHeader() {
		return header;
	}
	public void setHeader(EquifaxApiResponseHeader header) {
		this.header = header;
	}
	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}
	public EquifaxCriffResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(EquifaxCriffResponsePayload payload) {
		this.payload = payload;
	}
	
	@Override
	public String toString() {
		return "EquifaxApiResponse [header=" + header + ", errorInfo=" + errorInfo + ", payload=" + payload + "]";
	}
	

	
}
