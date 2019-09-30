package com.qualtech.icici.api.response;

import java.io.Serializable;

public class IciciApiResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7512403897428169057L;
	
	
	private Header header;
	private MessageInfo msginfo;
	private PreCalcResponsePayload payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public PreCalcResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(PreCalcResponsePayload payload) {
		this.payload = payload;
	}
	public MessageInfo getMsginfo() {
		return msginfo;
	}
	public void setMsginfo(MessageInfo msginfo) {
		this.msginfo = msginfo;
	}
	@Override
	public String toString() {
		return "IciciApiResponse [header=" + header + ", msginfo=" + msginfo + ", payload=" + payload + "]";
	}
	
	

}
