package com.qualtech.karza.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.karza.api.request.Header;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyLLPCINLookUpResponse implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2824991328160710598L;
	private Header header;
	private MessageInfo msgInfo;
	private CompanyLLPCINLookUpResponsePayload payload;
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
	public CompanyLLPCINLookUpResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(CompanyLLPCINLookUpResponsePayload payload) {
		this.payload = payload;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
