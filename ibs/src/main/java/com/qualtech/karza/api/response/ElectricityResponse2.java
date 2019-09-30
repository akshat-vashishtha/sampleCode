package com.qualtech.karza.api.response;

import java.io.Serializable;

import com.qualtech.karza.api.request.Header;

public class ElectricityResponse2 implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2592824246515332365L;
	private Header header;
	private MessageInfo msgInfo;
	private ElectricityResponsePayload2 payload;
	
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	
	
	@Override
	public String toString() {
		return "ElectricityResponse [header=" + header + ", msgInfo=" + msgInfo + ", payload=" + payload + "]";
	}
	
	public ElectricityResponsePayload2 getPayload() {
		return payload;
	}
	public void setPayload(ElectricityResponsePayload2 payload) {
		this.payload = payload;
	}
	public MessageInfo getMsgInfo() {
		return msgInfo;
	}
	public void setMsgInfo(MessageInfo msgInfo) {
		this.msgInfo = msgInfo;
	}

}
