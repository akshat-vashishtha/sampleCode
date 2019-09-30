package com.qualtech.karza.api.response;

import java.io.Serializable;

import com.qualtech.karza.api.request.Header;

public class UdyogAadharResponse implements Serializable {
	private static final long serialVersionUID = 8348785090820350364L;

	private Header header;
	private MessageInfo msgInfo;
	private UdyogAadharResponsePayload payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public MessageInfo getMsgInfo() {
		return msgInfo;
	}
	public void setMsgInfo(MessageInfo msgInfo) {
		this.msgInfo = msgInfo;
	}
	public UdyogAadharResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(UdyogAadharResponsePayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "UdyogAadharResponse [header=" + header + ", msgInfo=" + msgInfo + ", payload=" + payload + "]";
	}
	
	
	
	
}
