package com.qualtech.multibureau.api.util;

public class ByteBean {

	private String encodingReq;
	private byte[] byteArray;
	public String getEncodingReq() {
		return encodingReq;
	}
	public void setEncodingReq(String encodingReq) {
		this.encodingReq = encodingReq;
	}
	public byte[] getByteArray() {
		return byteArray;
	}
	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}
	@Override
	public String toString() {
		return "ByteBean [encodingReq=" + encodingReq + ", byteArray=" + byteArray + "]";
	}
	
}
