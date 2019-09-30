package com.qualtech.equifax.api.entity.mcr;

public class EquifaxMcrPhoneDetails {

	private int phoneDetail_id;
	private String phoneTypecode;
	private String phoneSeq;
	private String phoneNumber;
	private String phoneReportedDate;
	
	public int getPhoneDetail_id() {
		return phoneDetail_id;
	}
	public void setPhoneDetail_id(int phoneDetail_id) {
		this.phoneDetail_id = phoneDetail_id;
	}
	public String getPhoneTypecode() {
		return phoneTypecode;
	}
	public void setPhoneTypecode(String phoneTypecode) {
		this.phoneTypecode = phoneTypecode;
	}
	public String getPhoneSeq() {
		return phoneSeq;
	}
	public void setPhoneSeq(String phoneSeq) {
		this.phoneSeq = phoneSeq;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneReportedDate() {
		return phoneReportedDate;
	}
	public void setPhoneReportedDate(String phoneReportedDate) {
		this.phoneReportedDate = phoneReportedDate;
	}
	
}
