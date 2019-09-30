package com.qualtech.equifax.api.response;

public class AddressInfo {

	String address;
	String reportedDate;
	String state;
	String postal;
	String type;
	String seq;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getReportedDate() {
		return reportedDate;
	}
	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	@Override
	public String toString() {
		return "AddressInfo [address=" + address + ", reportedDate=" + reportedDate + ", state=" + state + ", postal="
				+ postal + ", type=" + type + ", seq=" + seq + "]";
	}
	
	
}
