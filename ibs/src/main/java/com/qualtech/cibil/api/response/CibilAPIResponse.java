package com.qualtech.cibil.api.response;


import com.qualtech.cibil.api.common.dto.ErrorInfo;

public class CibilAPIResponse 
{
	private ApiResponseHeader header;
	private ErrorInfo errorInfo;
	private CibilResponsePayload payload;

	
	public CibilAPIResponse() {
		super();
	}

	public CibilAPIResponse(ErrorInfo errorInfo, ApiResponseHeader header,
			CibilResponsePayload payload) {
		super();
		this.errorInfo = errorInfo;
		this.header = header;
		this.payload = payload;
	}

	public ApiResponseHeader getHeader() {
		return header;
	}

	public void setHeader(ApiResponseHeader header) {
		this.header = header;
	}

	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}

	public CibilResponsePayload getPayload() {
		return payload;
	}

	public void setPayload(CibilResponsePayload payload) {
		this.payload = payload;
	}
}
