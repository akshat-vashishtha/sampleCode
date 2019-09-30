package com.qualtech.karza.api.response;

import java.io.Serializable;

import com.qualtech.karza.api.request.Header;

public class EmailAuthResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1244588573084485219L;
	private Header header;
	private MessageInfo msgInfo;
	private EmailAuthResponsePayload payload;
	
	
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
	public EmailAuthResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(EmailAuthResponsePayload payload) {
		this.payload = payload;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "EmailAuthResponse [header=" + header + ", msgInfo=" + msgInfo + ", payload=" + payload + "]";
	}
	
	

}
