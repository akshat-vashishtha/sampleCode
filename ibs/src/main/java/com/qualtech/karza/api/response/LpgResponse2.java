package com.qualtech.karza.api.response;

import java.io.Serializable;

import com.qualtech.karza.api.request.Header;

public class LpgResponse2 implements Serializable{

	private static final long serialVersionUID = 1L;
	private Header header;
	private LpgResponsePayload2 payload;
	private MessageInfo msgInfo;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	
	public LpgResponsePayload2 getPayload() {
		return payload;
	}
	public void setPayload(LpgResponsePayload2 payload) {
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
