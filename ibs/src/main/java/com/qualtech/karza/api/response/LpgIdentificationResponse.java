package com.qualtech.karza.api.response;

import java.io.Serializable;

import com.qualtech.karza.api.request.Header;

public class LpgIdentificationResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private LpgIdentificationResponsePayload payload;
	private MessageInfo msgInfo;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	
	public LpgIdentificationResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(LpgIdentificationResponsePayload payload) {
		this.payload = payload;
	}
	public MessageInfo getMsgInfo() {
		return msgInfo;
	}
	public void setMsgInfo(MessageInfo msgInfo) {
		this.msgInfo = msgInfo;
	}
	@Override
	public String toString() {
		return "LpgResponse [header=" + header + ", payload=" + payload + ", msgInfo=" + msgInfo + "]";
	}

}
