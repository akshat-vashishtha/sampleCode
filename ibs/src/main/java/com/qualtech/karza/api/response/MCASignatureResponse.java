package com.qualtech.karza.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
@JsonIgnoreProperties(ignoreUnknown = true)
public class MCASignatureResponse implements Serializable {
	private static final long serialVersionUID = 8348785090820350364L;

	private Header header;
	private MessageInfo msgInfo;
	private MCASignatureResponsePayload payload;
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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setPayload(MCASignatureResponsePayload payload) {
		this.payload = payload;
	}
	public MCASignatureResponsePayload getPayload() {
		return payload;
	}
	
	
	
	
	
}
