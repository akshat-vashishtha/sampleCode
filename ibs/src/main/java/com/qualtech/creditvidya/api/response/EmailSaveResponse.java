package com.qualtech.creditvidya.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.creditvidya.api.request.Header;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailSaveResponse implements Serializable{
	private static final long serialVersionUID = 5403564038406509746L;
	private Header header;
	private MessageInfo msgInfo;
	private EmailSaveResponsePayload payload;
	
	public EmailSaveResponse() {
		super();
	}
	public EmailSaveResponse(Header header, MessageInfo msgInfo, EmailSaveResponsePayload payload) {
		super();
		this.header = header;
		this.msgInfo = msgInfo;
		this.payload = payload;
	}
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
	public EmailSaveResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(EmailSaveResponsePayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "EmailValidationResponse [header=" + header + ", msgInfo=" + msgInfo + ", payload=" + payload + "]";
	}

}
