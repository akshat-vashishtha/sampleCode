package com.qualtech.icici.api.request;

import java.io.Serializable;

public class CustOnBoardResuest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9190257293319854752L;
	
	private Header header;
	private CustOnBoardResuestPayload payload;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public CustOnBoardResuestPayload getPayload() {
		return payload;
	}
	public void setPayload(CustOnBoardResuestPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "CustOnBoardResuest [header=" + header + ", payload=" + payload + "]";
	}
	
	
	
	
	

}
