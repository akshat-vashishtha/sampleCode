package com.qualtech.crif.api.response;

import java.io.Serializable;

import com.qualtech.crif.api.dto.ErrorInfo;

public class CriffApiResponse implements Serializable
{
	private static final long serialVersionUID = 5403564038406509746L;
	private ApiResponseHeader header;
	private ErrorInfo errorInfo;
	private CriffResponsePayload payload;

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

	public CriffResponsePayload getPayload() {
		return payload;
	}

	public void setPayload(CriffResponsePayload payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "ApiResponse [header=" + header + ", errorInfo=" + errorInfo + ", payload=" + payload + "]";
	}
}
