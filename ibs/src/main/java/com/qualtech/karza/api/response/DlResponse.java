package com.qualtech.karza.api.response;

import java.io.Serializable;

import com.qualtech.karza.api.request.Header;


public class DlResponse implements Serializable
{

	private static final long serialVersionUID = 5403564038406509746L;
	private Header header;
	private MessageInfo msgInfo;
	private DlResponsePayload payload;
	public Header getHeader() 
	{
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
	public DlResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(DlResponsePayload payload) {
		this.payload = payload;
	}
	
	
}
