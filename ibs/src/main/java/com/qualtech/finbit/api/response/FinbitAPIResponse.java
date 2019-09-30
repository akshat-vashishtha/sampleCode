package com.qualtech.finbit.api.response;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FinbitAPIResponse 
{
	
	
	private ApiResponseHeader header;
	private ErrorInfo msgInfo;
	private FinbitResponsePayload payload;

	public ApiResponseHeader getHeader() {
		return header;
	}

	public void setHeader(ApiResponseHeader header) {
		this.header = header;
	}

	public ErrorInfo getErrorInfo() {
		return msgInfo;
	}

	public void setErrorInfo(ErrorInfo errorInfo) {
		this.msgInfo = errorInfo;
	}



	public ErrorInfo getMsgInfo() {
		return msgInfo;
	}

	public void setMsgInfo(ErrorInfo msgInfo) {
		this.msgInfo = msgInfo;
	}

	public FinbitResponsePayload getPayload() {
		return payload;
	}

	public void setPayload(FinbitResponsePayload payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "FinbitAPIResponse [header=" + header + ", errorInfo=" + msgInfo + ", payload=" + payload + "]";
	}
	
	
	
}
