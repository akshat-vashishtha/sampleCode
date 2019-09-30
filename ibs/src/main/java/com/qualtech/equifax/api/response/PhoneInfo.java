package com.qualtech.equifax.api.response;

public class PhoneInfo {

	String typeCode;
	String number;
	String reportedDate;
	String seq;
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getReportedDate() {
		return reportedDate;
	}
	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	@Override
	public String toString() {
		return "PhoneInfo [typeCode=" + typeCode + ", number=" + number + ", reportedDate=" + reportedDate + ", seq="
				+ seq + "]";
	}
	
	
}
