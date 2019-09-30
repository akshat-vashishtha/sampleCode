package com.qualtech.equifax.api.response;

public class EquifaxEVDRResponse {

	private EvdrResHeader header;
	private EvdrErrorInfo errorInfo;
	private EvdrResPayload payload;
	
	public EvdrResHeader getHeader() {
		return header;
	}
	public void setHeader(EvdrResHeader header) {
		this.header = header;
	}
	public EvdrErrorInfo getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(EvdrErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}
	public EvdrResPayload getPayload() {
		return payload;
	}
	public void setPayload(EvdrResPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "EquifaxEVDRResponse [header=" + header + ", errorInfo=" + errorInfo + ", payload=" + payload + "]";
	}
	
	
}
