package com.qualtech.karza.api.response;

import java.io.Serializable;

import com.qualtech.karza.api.request.Header;

public class TelephoneResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Header header;
	private TelResponsePayload payload;
	private MessageInfo msgInfo;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public TelResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(TelResponsePayload payload) {
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
		return "TelephoneResponse [header=" + header + ", payload=" + payload + ", msgInfo=" + msgInfo + "]";
	}
	
	
	
}
