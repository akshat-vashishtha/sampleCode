package com.qualtech.equifax.api.response;

public class InquiryAdd {

	String state;
	String addressLine;
	String postal;
	String seq;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	@Override
	public String toString() {
		return "InquiryAdd [state=" + state + ", addressLine=" + addressLine + ", postal=" + postal + ", seq=" + seq
				+ "]";
	}
	
	
}
