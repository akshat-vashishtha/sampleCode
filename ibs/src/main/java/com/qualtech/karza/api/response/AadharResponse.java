package com.qualtech.karza.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AadharResponse implements Serializable {
	@Override
	public String toString() {
		return "AadharResponse [header=" + header + ", msgInfo=" + msgInfo + ", payload=" + payload + "]";
	}
	private static final long serialVersionUID = 8348785090820350364L;

	private Header header;
	private MessageInfo msgInfo;
	private AadharResponsePayload payload;
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
	public AadharResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(AadharResponsePayload payload) {
		this.payload = payload;
	}
	
	
	
}
