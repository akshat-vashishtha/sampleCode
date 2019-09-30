package com.qualtech.karza.api.response;

import com.qualtech.karza.api.request.Header;

public class KycOcrResponse {


	private Header header;
	private MessageInfo msgInfo;
	private KycOcrResponsePayload payload;
	public Header getHeader() 
	{
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
	public KycOcrResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(KycOcrResponsePayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "KycOcrResponse [header=" + header + ", msgInfo=" + msgInfo + ", payload=" + payload + "]";
	}

	

}
