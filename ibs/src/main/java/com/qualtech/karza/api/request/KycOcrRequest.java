package com.qualtech.karza.api.request;

public class KycOcrRequest {

	
	private Header header;
	private KycOcrReqPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public KycOcrReqPayload getPayload() {
		return payload;
	}
	public void setPayload(KycOcrReqPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "KycOcrRequest [header=" + header + ", payload=" + payload + "]";
	}
	

}
