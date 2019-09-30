package com.qualtech.creditvidya.api.common.dto;

import java.io.Serializable;

import com.qualtech.creditvidya.api.request.EmailVerificationReqPayload;
import com.qualtech.creditvidya.api.request.Header;

public class EmailVerificationRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Header header;
	private EmailVerificationReqPayload payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	@Override
	public String toString() {
		return "EmailFraudRequest [header=" + header + ", payload=" + payload + "]";
	}
	public EmailVerificationReqPayload getPayload() {
		return payload;
	}
	public void setPayload(EmailVerificationReqPayload payload) {
		this.payload = payload;
	}

	
}
