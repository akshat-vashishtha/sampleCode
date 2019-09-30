package com.qualtech.karza.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ITRAuthResponse implements Serializable {
	private static final long serialVersionUID = 8348785090820350364L;

	private Header header;
	private MessageInfo msgInfo;
	private ITRAuthResponsePayload payload;
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
	public ITRAuthResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(ITRAuthResponsePayload payload) {
		this.payload = payload;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
