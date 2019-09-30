package com.qualtech.kotak.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.kotak.api.request.Header;



@JsonIgnoreProperties(ignoreUnknown = true)
public class KotakResponse implements Serializable {
	private static final long serialVersionUID = 8348785090820350364L;

	private Header header;
	private MessageInfo msgInfo;
	private KotakResponsePayload payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	
	public KotakResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(KotakResponsePayload payload) {
		this.payload = payload;
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
	@Override
	public String toString() {
		return "KotakResponse [header=" + header + ", msgInfo=" + msgInfo + ", payload=" + payload + "]";
	}

	
	
	
	
	
}
