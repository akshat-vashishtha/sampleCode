package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.multibureau.api.request.Header;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BureauResponse implements Serializable
{

	private static final long serialVersionUID = -3058256086000561269L;
	private Header header;
	private MessageInfo msgInfo;
	private BureauResponsePayload payload;
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
	public BureauResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(BureauResponsePayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "BureauResponse [header=" + header + ", msgInfo=" + msgInfo + ", payload=" + payload + "]";
	}

}
