package com.qualtech.karza.api.response;

import java.io.Serializable;

import com.qualtech.karza.api.request.Header;

public class EmployerLookupResponse implements Serializable {
	private static final long serialVersionUID = 8348785090820350364L;

	private Header header;
	private MessageInfo msgInfo;
	private EmployerLookupResponsePayload payload;
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
	public EmployerLookupResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(EmployerLookupResponsePayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "EmployerLookupResponse [header=" + header + ", msgInfo=" + msgInfo + ", payload=" + payload + "]";
	}


	
	
	
	
}
