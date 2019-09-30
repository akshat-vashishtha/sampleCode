package com.qualtech.equifax.api.response;

public class EquifaxVerifyIdInquiryRequestInfo {

	private String inquirypurpose;
	private String panid;
	private String firstname;
	private String lastname;
	public String getInquirypurpose() {
		return inquirypurpose;
	}
	public void setInquirypurpose(String inquirypurpose) {
		this.inquirypurpose = inquirypurpose;
	}
	public String getPanid() {
		return panid;
	}
	public void setPanid(String panid) {
		this.panid = panid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@Override
	public String toString() {
		return "EquifaxVerifyIdInquiryRequestInfo [inquirypurpose=" + inquirypurpose + ", panid=" + panid
				+ ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}
	
	
}
