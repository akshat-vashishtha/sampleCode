package com.qualtech.experiankickoff.api.response;

import com.qualtech.experiankickoff.api.common.dto.ErrorInfo;
import com.qualtech.experiankickoff.api.request.Header;

public class ExperianMaskResponse extends ExperianKickOffResponse{


	private Header header;
	private ErrorInfo errorInfo;
	private ExperianMaskResponsePayload payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	
	public ExperianMaskResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(ExperianMaskResponsePayload payload) {
		this.payload = payload;
	}
	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}
	@Override
	public String toString() {
		return "ExperianMaskResponse [header=" + header + ", errorInfo=" + errorInfo + ", payload=" + payload + "]";
	}
	
	
	
	
	
	

}
