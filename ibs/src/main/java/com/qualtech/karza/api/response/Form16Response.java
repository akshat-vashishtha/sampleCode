package com.qualtech.karza.api.response;

import java.io.Serializable;

import com.qualtech.karza.api.request.Header;

public class Form16Response implements Serializable {
	private static final long serialVersionUID = 8348785090820350364L;

	private Header header;
	private MessageInfo msgInfo;
	private Form16ResponsePayload payload;
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
	public Form16ResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(Form16ResponsePayload payload) {
		this.payload = payload;
	}
	

		
	
}
