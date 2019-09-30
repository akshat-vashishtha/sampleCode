package com.qualtech.karza.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyLLPIdentificationResponse implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2824991328160710598L;
	private Header header;
	private MessageInfo msgInfo;
	private CompanyLLPIdentificationResponsePayload payload;
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
	
	public CompanyLLPIdentificationResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(CompanyLLPIdentificationResponsePayload payload) {
		this.payload = payload;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
