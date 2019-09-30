package com.qualtech.karza.api.response;

import java.io.Serializable;

import com.qualtech.karza.api.request.Header;

public class NameSimilarityResponse implements Serializable {
	private static final long serialVersionUID = 8348785090820350364L;

	private Header header;
	private MessageInfo msgInfo;
	private NameSimilarityResponsePayload payload;
	private String byteArray;
	private String pdfPath;
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

	public NameSimilarityResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(NameSimilarityResponsePayload payload) {
		this.payload = payload;
	}
	
	
	public String getByteArray() {
		return byteArray;
	}
	public void setByteArray(String byteArray) {
		this.byteArray = byteArray;
	}
	public String getPdfPath() {
		return pdfPath;
	}
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
	@Override
	public String toString() {
		return "NameSimilarityResponse [header=" + header + ", msgInfo=" + msgInfo + ", payload=" + payload + "]";
	}
	
}
