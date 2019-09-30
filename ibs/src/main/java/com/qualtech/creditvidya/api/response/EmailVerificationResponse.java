package com.qualtech.creditvidya.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.creditvidya.api.request.Header;
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailVerificationResponse implements Serializable{

	private static final long serialVersionUID = 5403564038406509746L;
	private Header header;
	private MessageInfo msgInfo;
	private EmailVerificationPayload payload;
	@Override
	public String toString() {
		return "EmailFraudResponse [header=" + header + ", msgInfo=" + msgInfo + ", payload=" + payload + "]";
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
	public EmailVerificationPayload getPayload() {
		return payload;
	}
	public void setPayload(EmailVerificationPayload payload) {
		this.payload = payload;
	}
	
}
