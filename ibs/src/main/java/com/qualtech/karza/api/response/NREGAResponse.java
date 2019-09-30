package com.qualtech.karza.api.response;

import java.io.Serializable;

import com.qualtech.karza.api.request.Header;

public class NREGAResponse implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2905021982263946410L;
	private Header header;
	private MessageInfo msgInfo;
	private NREGAResponsePayload payload;
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
	public NREGAResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(NREGAResponsePayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "NREGAResponse [header=" + header + ", msgInfo=" + msgInfo
				+ ", payload=" + payload + "]";
	}
	
	
	
	
}
