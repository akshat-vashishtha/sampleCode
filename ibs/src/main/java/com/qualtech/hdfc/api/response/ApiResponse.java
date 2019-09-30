package com.qualtech.hdfc.api.response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ApiResponse {

	private ApiResponseHeader header;
	private MsgInfo msgInfo;
	private ApiResponseHdfcPayload payload;
	public ApiResponseHeader getHeader() {
		return header;
	}
	public void setHeader(ApiResponseHeader header) {
		this.header = header;
	}
	public MsgInfo getMsgInfo() {
		return msgInfo;
	}
	public void setMsgInfo(MsgInfo msgInfo) {
		this.msgInfo = msgInfo;
	}
	public ApiResponseHdfcPayload getPayload() {
		return payload;
	}
	public void setPayload(ApiResponseHdfcPayload payload) {
		this.payload = payload;
	}
	
	
	
}
