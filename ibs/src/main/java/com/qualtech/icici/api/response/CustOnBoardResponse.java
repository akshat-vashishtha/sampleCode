package com.qualtech.icici.api.response;

import java.io.Serializable;

public class CustOnBoardResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5996434269642394111L;
	
	private Header header;
	private MessageInfo msginfo;
	private CustOnBoardResponsePayload paylaod;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public MessageInfo getMsginfo() {
		return msginfo;
	}
	public void setMsginfo(MessageInfo msginfo) {
		this.msginfo = msginfo;
	}
	public CustOnBoardResponsePayload getPaylaod() {
		return paylaod;
	}
	public void setPaylaod(CustOnBoardResponsePayload paylaod) {
		this.paylaod = paylaod;
	}
	@Override
	public String toString() {
		return "CustOnBoardResponse [header=" + header + ", msginfo=" + msginfo + "]";
	}
	
	
	
	

}
