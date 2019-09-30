package com.qualtech.equifax.api.response;

public class HomePhone {

	String PhoneNumber;
	String seq;
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	@Override
	public String toString() {
		return "HomePhone [PhoneNumber=" + PhoneNumber + ", seq=" + seq + "]";
	}
	
	
	
}
