package com.qualtech.karza.api.response;

import java.io.Serializable;

import com.qualtech.karza.api.request.Header;

public class ElectricityResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7412978211379341882L;
	private Header header;
	private MessageInfo msgInfo;
	private ElectricityResponsePayload payload;
	
	
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
	public ElectricityResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(ElectricityResponsePayload payload) {
		this.payload = payload;
	}
	public MessageInfo getMsgInfo() {
		return msgInfo;
	}
	public void setMsgInfo(MessageInfo msgInfo) {
		this.msgInfo = msgInfo;
	}

}
