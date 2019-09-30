package com.qualtech.creditvidya.api.common.dto;


import java.io.Serializable;

import com.qualtech.creditvidya.api.request.Header;
import com.qualtech.creditvidya.api.request.EmailSaveReqPayload;

public class EmailSaveRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Header header;
	private EmailSaveReqPayload payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public EmailSaveReqPayload getPayload() {
		return payload;
	}
	public void setPayload(EmailSaveReqPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "EmailValidationRequest [header=" + header + ", payload=" + payload + "]";
	}
}
