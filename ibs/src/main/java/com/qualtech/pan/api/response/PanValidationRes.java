package com.qualtech.pan.api.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class PanValidationRes {

	private PanValidationResHeader header;
	private PanValidationResMsgInfo msgInfo;
	private List<PanValidationResPayload> payload;
	public PanValidationResHeader getHeader() {
		return header;
	}
	public void setHeader(PanValidationResHeader header) {
		this.header = header;
	}
	public PanValidationResMsgInfo getMsgInfo() {
		return msgInfo;
	}
	public void setMsgInfo(PanValidationResMsgInfo msgInfo) {
		this.msgInfo = msgInfo;
	}
	public List<PanValidationResPayload> getPayload() {
		return payload;
	}
	public void setPayload(List<PanValidationResPayload> payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "PanValidationRes [header=" + header + ", msgInfo=" + msgInfo + ", payload=" + payload + "]";
	}
	
}
